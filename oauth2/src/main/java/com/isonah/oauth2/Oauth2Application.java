package com.isonah.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

import javax.sql.DataSource;

@SpringBootApplication
@EnableEurekaClient
@EnableJdbcHttpSession
public class Oauth2Application {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2Application.class, args);
    }

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                                .url("jdbc:mysql://localhost:3306/mystory?useSSL=false&useUnicode=true" +
                                     "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC")
                                .username("root")
                                .password("secret")
                                .driverClassName("com.mysql.cj.jdbc.Driver")
                                .build();
    }
}
