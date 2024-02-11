package com.example.inflearnkevinspringreactiveweb.section09.class02

import com.example.inflearnkevinspringreactiveweb.util.Logger
import reactor.core.publisher.Flux

/**
 * checkpoint() Operator 를 이용한 예제
 * - 에러가 예상되는 assembly 지점에 checkpoint()를 사용해서 에러 발생 지점을 확인할 수 있다.
 * - checkpoint()는 에러 발생 시, traceback 이 추가된다.
 */
object CheckpointExample01 {
    @JvmStatic
    fun main(args: Array<String>) {
        Flux.just(2, 4, 6, 8)
            .zipWith(Flux.just(1, 2, 3, 0)) { x, y -> x / y }
            .checkpoint()
            .map { num -> num + 2 }
            .subscribe(Logger::onNext, Logger::onError)
    }
}
