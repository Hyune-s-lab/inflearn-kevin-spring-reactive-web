package com.example.inflearnkevinspringreactiveweb.part1.section10.class01

import reactor.core.publisher.Flux

object GeneralExample {
    fun sayHelloReactor(): Flux<String> {
        return Flux
            .just("Hello", "Reactor")
    }

    fun divideByTwo(source: Flux<Int>): Flux<Int> {
        return source
            .zipWith(Flux.just(2, 2, 2, 2, 2)) { x, y -> x / y }
    }

    fun occurError(source: Flux<Int>): Flux<Int> {
        return source
            .zipWith(Flux.just(2, 2, 2, 2, 0)) { x, y -> x / y }
    }

    fun takeNumber(source: Flux<Int>, n: Long): Flux<Int> {
        return source.take(n)
    }
}
