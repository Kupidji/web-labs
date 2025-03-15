package ru.kupidji.moonlight

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MoonlightApplication

fun main(args: Array<String>) {
    runApplication<MoonlightApplication>(*args)
}