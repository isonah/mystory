package com.isonah.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author ielksseyer
 */
@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    DataSource dataSource;

    // TODO externalize token related data to configuration, store clients in DB
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                .withClient("webapp").authorizedGrantTypes("implicit", "refresh_token", "password", "authorization_code")
                .secret("secret")
                .authorities("ROLE_TRUSTED").resourceIds("/user").scopes("read", "write")
                .autoApprove(false)
                .accessTokenValiditySeconds(6000)
                .refreshTokenValiditySeconds(6000)
                .and()
                .withClient("server")
                .secret("secret").authorizedGrantTypes("refresh_token", "authorization_code")
                .authorities("ROLE_TRUSTED").resourceIds("app/admin").scopes("read", "write")
                .autoApprove(false);
    }

    /*
     * The endpoints can only be accessed by a not logged in user or a user with
     * the specified role
     */
    // TODO externalise configuration
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("isAnonymous() || hasAuthority('ROLE_TRUSTED')")
                   .checkTokenAccess("hasAuthority('ROLE_TRUSTED')");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore()).tokenEnhancer(jwtAccessTokenConverter())
                 .authenticationManager(authenticationManager);
    }

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);

    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

    // TODO encrypt password
    @Bean
    protected JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(
                new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "password".toCharArray()).getKeyPair("jwt"));
        return converter;
    }

    /*
     * Add custom user principal information to the JWT token
     */
    // TODO additional information fields should be get from configuration
    protected static class CustomTokenEnhancer extends JwtAccessTokenConverter {

        @Override
        public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
            User user = (User) authentication.getPrincipal();
            Map<String, Object> info = new LinkedHashMap<String, Object>(accessToken.getAdditionalInformation());
            DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);
            // Get the authorities from the user
            Set<GrantedAuthority> authoritiesSet = new HashSet<>(authentication.getAuthorities());
            // Generate String array
            String[] authorities = new String[authoritiesSet.size()];
            int i = 0;
            for (GrantedAuthority authority : authoritiesSet) {
                authorities[i++] = authority.getAuthority();
            }
            info.put("authorities", authorities);
            customAccessToken.setAdditionalInformation(info);
            return super.enhance(customAccessToken, authentication);
        }
    }
}

