package com.isonah.mystory

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@EnableEurekaClient
@SpringBootApplication
open class MystoryApplication

fun main(args: Array<String>) {
    SpringApplication.run(MystoryApplication::class.java, *args)
}
