package com.vibetrip.vibetripserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class VibeTripServerApplication

fun main(args: Array<String>) {
    runApplication<VibeTripServerApplication>(*args)
}
