package ru.kupidji.web_labs

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebLabsApplication

fun main(args: Array<String>) {
	runApplication<WebLabsApplication>(*args)
}
