package com.example.inflearnkevinspringreactiveweb.section10.class02

import reactor.core.publisher.Mono

object ContextExample {
    fun helloMessage(source: Mono<String>, key: String): Mono<String> {
        return source
            .zipWith(Mono.deferContextual { ctx ->
                Mono.just(ctx.get<Any>(key))
            })
            .map { tuple -> tuple.t1 + ", " + tuple.t2 }
    }
}
