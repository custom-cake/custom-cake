package com.cake.customcakebackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

//@ConfigurationPropertiesScan
@EnableJpaAuditing
@SpringBootApplication
class CustomCakeBackendApplication

fun main(args: Array<String>) {
	runApplication<CustomCakeBackendApplication>(*args)
}
