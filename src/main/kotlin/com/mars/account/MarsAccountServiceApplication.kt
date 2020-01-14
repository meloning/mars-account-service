package com.mars.account

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MarsAccountServiceApplication

fun main(args: Array<String>) {
    runApplication<MarsAccountServiceApplication>(*args)
}
