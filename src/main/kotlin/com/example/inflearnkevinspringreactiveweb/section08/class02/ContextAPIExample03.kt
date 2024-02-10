package com.example.inflearnkevinspringreactiveweb.section08.class02

import com.example.inflearnkevinspringreactiveweb.util.Logger
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import reactor.util.context.Context

/**
 * ContextView API 예제 코드
 *
 */
object ContextAPIExample03 {
    @JvmStatic
    fun main(args: Array<String>) {
        val key1 = "id"
        val key2 = "name"

        Mono.deferContextual { ctx ->
            Mono.just<String>(
                "ID: " + " " + ctx.get<Any>(key1) + ", "
                    + "Name: " + ctx.get<Any>(key2) + ", "
                    + "Job: " + ctx.getOrDefault<String>("job", "Software Engineer")
            )
        }
            .publishOn(Schedulers.parallel())
            .contextWrite(Context.of(key1, "itVillage", key2, "Kevin"))
            .subscribe(Logger::onNext)

        Thread.sleep(100L)
    }
}
