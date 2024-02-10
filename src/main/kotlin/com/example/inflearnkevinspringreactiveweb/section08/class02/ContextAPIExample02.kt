package com.example.inflearnkevinspringreactiveweb.section08.class02

import com.example.inflearnkevinspringreactiveweb.util.Logger
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import reactor.util.context.Context

/**
 * Context API 예제 코드
 * - pullAll(ContextView) API 사용
 */
object ContextAPIExample02 {
    @JvmStatic
    fun main(args: Array<String>) {
        val key1 = "id"
        val key2 = "name"
        val key3 = "country"

        Mono.deferContextual { ctx ->
            Mono.just<String>(
                "ID: " + " " + ctx.get<Any>(key1) + ", " + "Name: " + ctx.get<Any>(key2) + ", " + "Country: " + ctx.get<Any>(
                    key3
                )
            )
        }
            .publishOn(Schedulers.parallel())
            .contextWrite { context -> context.putAll(Context.of(key2, "Kevin", key3, "Korea").readOnly()) }
            .contextWrite { context -> context.put(key1, "itVillage") }
            .subscribe(Logger::onNext)

        Thread.sleep(100L)
    }
}
