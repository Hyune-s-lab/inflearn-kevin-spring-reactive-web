package com.example.inflearnkevinspringreactiveweb.section08.class03

import com.example.inflearnkevinspringreactiveweb.util.Logger
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

/**
 * Context의 특징
 * - 동일한 키에 대해서 write 할 경우, 해당 키에 대한 값을 덮어 쓴다.
 */
object ContextFeatureExample03 {
    @JvmStatic
    fun main(args: Array<String>) {
        val key1 = "id"

        Mono.deferContextual { ctx -> Mono.just<String>("ID: " + " " + ctx.get<Any>(key1)) }
            .publishOn(Schedulers.parallel())
            .contextWrite { context -> context.put(key1, "itWorld") }
            .contextWrite { context -> context.put(key1, "itVillage") }
            .subscribe(Logger::onNext)

        Thread.sleep(100L)
    }
}
