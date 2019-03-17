package com.isonah.reverseproxy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.RestController;

@EnableZuulProxy
@SpringBootApplication
@RestController
public class ReverseProxyApplication {

    @Value("${message:not working}")
    String value;

    public static void main(String[] args) {
        SpringApplication.run(ReverseProxyApplication.class, args);
    }
}
