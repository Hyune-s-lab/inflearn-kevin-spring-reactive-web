package com.example.inflearnkevinspringreactiveweb.part1.section08.class03

import com.example.inflearnkevinspringreactiveweb.util.Logger
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

/**
 * Context의 특징
 * - Context는 각각의 구독을 통해 Reactor Sequence에 연결 되며 체인의 각 연산자가 연결된 Context에 접근할 수 있어야 한다.
 *
 */
object ContextFeatureExample01 {
    @JvmStatic
    fun main(args: Array<String>) {
        val key1 = "id"

        val mono = Mono.deferContextual { ctx -> Mono.just("ID: " + " " + ctx.get(key1)) }
            .publishOn(Schedulers.parallel())

        mono.contextWrite { context -> context.put(key1, "itVillage") }
            .subscribe { data -> Logger.onNext("subscriber 1", data) }

        mono.contextWrite { context -> context.put(key1, "itWorld") }
            .subscribe { data -> Logger.onNext("subscriber 2", data) }


        Thread.sleep(100L)
    }
}
