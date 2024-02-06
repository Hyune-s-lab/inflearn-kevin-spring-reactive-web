package com.example.inflearnkevinspringreactiveweb.section03.class01

import org.slf4j.LoggerFactory
import reactor.core.publisher.Flux

/**
 * Flux 기본 예제
 */
object FluxExample01 {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @JvmStatic
    fun main(args: Array<String>) {
        Flux.just(6, 9, 13)
            .map { num: Int -> num % 2 }
            .subscribe { remainder: Int -> log.info("### remainder: {}", remainder) }
    }
}
