package com.example.inflearnkevinspringreactiveweb.part1.section08.class03

import com.example.inflearnkevinspringreactiveweb.util.Logger
import org.reactivestreams.Publisher
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

/**
 * Context의 특징
 * - inner Sequence 내부에서는 외부 Context에 저장된 데이터를 읽을 수 있다.
 * - inner Sequence 내부에서 Context에 저장된 데이터는 inner Sequence 외부에서 읽을 수 없다.
 */
object ContextFeatureExample04 {
    @JvmStatic
    fun main(args: Array<String>) {
        val key1 = "id"
        Mono.just<String>("Kevin")
            .transformDeferredContextual { _, contextView -> contextView.get<Publisher<Any>>("job") }
            .flatMap { name ->
                Mono.deferContextual { ctx ->
                    Mono.just<String>(ctx.get<Any>(key1).toString() + ", " + name)
                        .transformDeferredContextual { mono, innerCtx ->
                            mono.map { data -> "$data, " + innerCtx.get<Any>("job") }
                        }
                        .contextWrite { context -> context.put("job", "Software Engineer") }
                }
            }
            .publishOn(Schedulers.parallel())
            .contextWrite { context -> context.put(key1, "itVillage") }
            .subscribe(Logger::onNext)

        Thread.sleep(100L)
    }
}
