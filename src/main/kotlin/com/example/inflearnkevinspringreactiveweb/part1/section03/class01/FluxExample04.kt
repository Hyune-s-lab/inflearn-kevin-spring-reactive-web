package com.example.inflearnkevinspringreactiveweb.part1.section03.class01

import org.slf4j.LoggerFactory
import reactor.core.publisher.Flux

/**
 * 여러개의 Flux를 연결해서 하나의 Flux로 결합하는 예제
 */
object FluxExample04 {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @JvmStatic
    fun main(args: Array<String>) {
        Flux.concat(
            Flux.just<String>("Venus"),
            Flux.just<String>("Earth"),
            Flux.just<String>("Mars")
        )
            .collectList()
            .subscribe { planetList -> log.info("### Solar System: {}", planetList) }
    }
}
