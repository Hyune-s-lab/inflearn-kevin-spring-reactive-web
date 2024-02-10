package com.example.inflearnkevinspringreactiveweb.section08.class02

import com.example.inflearnkevinspringreactiveweb.util.Logger
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import reactor.util.context.Context

/**
 * Context API 중에서 write API 예제 코드
 * - Context.of(...) 사용
 */
object ContextAPIExample01 {
    @JvmStatic
    fun main(args: Array<String>) {
        val key1 = "id"
        val key2 = "name"
        val mono = Mono.deferContextual { ctx ->
            Mono.just("ID: " + " " + ctx.get(key1) + ", " + "Name: " + ctx.get(key2))
        }
            .publishOn(Schedulers.parallel())
            .contextWrite(Context.of(key1, "itVillage", key2, "Kevin"))

        mono.subscribe { data -> Logger.onNext(data) }

        Thread.sleep(100L)
    }
}
