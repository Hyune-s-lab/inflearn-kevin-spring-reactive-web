package com.example.inflearnkevinspringreactiveweb.section09.class01

import com.example.inflearnkevinspringreactiveweb.util.Logger
import reactor.core.publisher.Flux

/**
 * Non-Debug mode
 */
object DebugModeExample01 {
    @JvmStatic
    fun main(args: Array<String>) {
        Flux.just(2, 4, 6, 8)
            .zipWith(Flux.just(1, 2, 3, 0)) { x, y -> x / y }
            .subscribe(Logger::onNext, Logger::onError)
    }
}
