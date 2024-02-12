package com.example.inflearnkevinspringreactiveweb.part1.section04.class01

import org.slf4j.LoggerFactory
import reactor.core.publisher.Flux
import java.util.*

object ColdSequenceExample {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @JvmStatic
    fun main(args: Array<String>) {
        val coldFlux = Flux.fromIterable(mutableListOf("RED", "YELLOW", "PINK"))
            .map { obj -> obj.lowercase(Locale.getDefault()) }

        coldFlux.subscribe { country -> log.info("### Subscriber1: {}", country) }
        log.info("###-------------------------")
        coldFlux.subscribe { country -> log.info("### Subscriber2: {}", country) }
    }
}
