package com.example.inflearnkevinspringreactiveweb.part1.section06.class01

import com.example.inflearnkevinspringreactiveweb.util.Logger
import reactor.core.publisher.Sinks
import reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST

/**
 * Sinks.Many 예제
 * - multicast()를 사용해서 하나 이상의 Subscriber에게 데이터를 emit하는 예제
 */
object SinkManyExample02 {
    @JvmStatic
    fun main(args: Array<String>) {
        // 하나 이상의 Subscriber에게 데이터를 emit할 수 있다.
        val multicastSink = Sinks.many().multicast().onBackpressureBuffer<Int>()
        val fluxView = multicastSink.asFlux()

        multicastSink.emitNext(1, FAIL_FAST)
        multicastSink.emitNext(2, FAIL_FAST)

        fluxView.subscribe { data -> Logger.onNext("Subscriber1", data) }

        fluxView.subscribe { data -> Logger.onNext("Subscriber2", data) }

        multicastSink.emitNext(3, FAIL_FAST)
    }
}
