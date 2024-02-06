package com.example.inflearnkevinspringreactiveweb.section03.class01

import org.slf4j.LoggerFactory
import reactor.core.publisher.Mono

/**
 * 2개의 Mono를 연결해서 Flux로 변환하는 예제
 */
object FluxExample03 {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @JvmStatic
    fun main(args: Array<String>) {
        val flux = Mono.justOrEmpty<Any>(null)
            .concatWith(Mono.justOrEmpty("Jobs"))
        flux.subscribe { data -> log.info("### result: {}", data) }
    }
}
