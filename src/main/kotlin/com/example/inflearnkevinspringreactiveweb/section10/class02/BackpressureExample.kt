package com.example.inflearnkevinspringreactiveweb.section10.class02

import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink

object BackpressureExample {
    fun generateNumberByErrorStrategy(): Flux<Int> {
        return Flux
            .create({ emitter ->
                for (i in 1..100) {
                    emitter.next(i)
                }
                emitter.complete()
            }, FluxSink.OverflowStrategy.ERROR)
    }

    fun generateNumberByDropStrategy(): Flux<Int> {
        return Flux
            .create({ emitter ->
                for (i in 1..100) {
                    emitter.next(i)
                }
                emitter.complete()
            }, FluxSink.OverflowStrategy.DROP)
    }
}
