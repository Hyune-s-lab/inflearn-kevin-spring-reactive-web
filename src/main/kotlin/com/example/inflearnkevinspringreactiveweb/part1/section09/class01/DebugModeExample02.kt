package com.example.inflearnkevinspringreactiveweb.part1.section09.class01

import com.example.inflearnkevinspringreactiveweb.util.Logger
import reactor.core.publisher.Flux
import reactor.core.publisher.Hooks

/**
 * onOperatorDebug() Hook 메서드를 이용한 Debug mode
 * - 애플리케이션 전체에서 global 하게 동작한다.
 */
object DebugModeExample02 {
    @JvmStatic
    fun main(args: Array<String>) {
        Hooks.onOperatorDebug()

        Flux.just(2, 4, 6, 8)
            .zipWith(Flux.just(1, 2, 3, 0)) { x, y -> x / y }
            .subscribe(Logger::onNext, Logger::onError)
    }
}
