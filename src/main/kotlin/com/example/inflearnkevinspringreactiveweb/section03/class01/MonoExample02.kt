package com.example.inflearnkevinspringreactiveweb.section03.class01

import org.slf4j.LoggerFactory
import reactor.core.publisher.Mono

/**
 * Mono 기본 개념 예제
 * - 원본 데이터의 emit 없이 onComplete signal 만 emit 한다.
 */
object MonoExample02 {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @JvmStatic
    fun main(args: Array<String>) {
        Mono.empty<Any>()
            .subscribe(
                { data -> log.info("### emitted data: {}", data) },
                { error -> },
                { log.info("### emitted onComplete signal") }
            )
    }
}
