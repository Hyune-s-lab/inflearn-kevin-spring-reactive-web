package com.example.inflearnkevinspringreactiveweb.section06.class01

import com.example.inflearnkevinspringreactiveweb.util.Logger
import reactor.core.publisher.Sinks
import reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST

/**
 * Sinks.Many 예제
 * - unicast()를 사용해서 단 하나의 Subscriber에게만 데이터를 emit하는 예제
 */
object SinkManyExample01 {
    @JvmStatic
    fun main(args: Array<String>) {
        // 단 하나의 Subscriber에게만 데이터를 emit할 수 있다.
        val unicastSink = Sinks.many().unicast().onBackpressureBuffer<Int>()
        val fluxView = unicastSink.asFlux()

        unicastSink.emitNext(1, FAIL_FAST)
        unicastSink.emitNext(2, FAIL_FAST)


        fluxView.subscribe { data -> Logger.onNext("Subscriber1", data) }

        unicastSink.emitNext(3, FAIL_FAST)

        // TODO 주석 전, 후 비교해서 보여 줄 것.
        fluxView.subscribe { data -> Logger.onNext("Subscriber2", data) }
    }
}
