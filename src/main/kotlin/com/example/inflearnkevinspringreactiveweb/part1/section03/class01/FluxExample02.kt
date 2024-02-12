package com.example.inflearnkevinspringreactiveweb.part1.section03.class01

import org.slf4j.LoggerFactory
import reactor.core.publisher.Flux

/**
 * Flux 에서의 Operator 체인 사용 예제
 */
object FluxExample02 {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @JvmStatic
    fun main(args: Array<String>) {
        Flux.fromArray(arrayOf(3, 6, 7, 9))
            .filter { num: Int -> num > 6 }
            .map { num: Int -> num * 2 }
            .subscribe { multiply: Int -> log.info("### multiply: {}", multiply) }
    }
}
