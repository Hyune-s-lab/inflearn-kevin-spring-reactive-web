package com.example.inflearnkevinspringreactiveweb.part1.section09.class02

import com.example.inflearnkevinspringreactiveweb.util.Logger
import reactor.core.publisher.Flux

/**
 * 분리된 method 에서 checkpoint() Operator 를 이용한 예제
 */
object CheckpointExample07 {
    @JvmStatic
    fun main(args: Array<String>) {
        val source = Flux.just(2, 4, 6, 8)
        val other = Flux.just(1, 2, 3, 0)

        val multiplySource = divide(source, other).checkpoint()
        val plusSource = plus(multiplySource).checkpoint()

        plusSource.subscribe(Logger::onNext, Logger::onError)
    }

    private fun divide(source: Flux<Int>, other: Flux<Int>): Flux<Int> {
        return source.zipWith(other) { x, y -> x / y }
    }

    private fun plus(source: Flux<Int>): Flux<Int> {
        return source.map { num -> num + 2 }
    }
}
