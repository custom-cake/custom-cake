package com.cake.customcake

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.web.servlet.config.annotation.EnableWebMvc

//@ConfigurationPropertiesScan
@EnableJpaAuditing
@EnableWebMvc
@SpringBootApplication
class CustomCakeApplication

fun main(args: Array<String>) {
	runApplication<CustomCakeApplication>(*args)
}
