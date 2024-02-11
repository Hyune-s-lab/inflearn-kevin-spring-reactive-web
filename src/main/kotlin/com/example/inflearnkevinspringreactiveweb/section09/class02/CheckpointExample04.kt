package com.example.inflearnkevinspringreactiveweb.section09.class02

import com.example.inflearnkevinspringreactiveweb.util.Logger
import reactor.core.publisher.Flux

/**
 * checkpoint(description) Operator 를 이용한 예제
 * - description 을 추가해서 에러가 발생한 지점을 구분할 수 있다.
 * - description 을 지정할 경우에 에러가 발생한 assembly 지점의 traceback 을 추가하지 않는다.
 */
object CheckpointExample04 {
    @JvmStatic
    fun main(args: Array<String>) {
        Flux.just(2, 4, 6, 8)
            .zipWith(Flux.just(1, 2, 3, 0)) { x, y -> x / y }
            .checkpoint("CheckpointExample02.zipWith.checkpoint")
            .map { num -> num + 2 }
            .subscribe(Logger::onNext, Logger::onError)
    }
}
