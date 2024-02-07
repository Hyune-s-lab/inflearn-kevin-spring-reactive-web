package com.example.inflearnkevinspringreactiveweb.section06.class01

import com.example.inflearnkevinspringreactiveweb.util.Logger
import reactor.core.publisher.Sinks
import reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST

object SinkManyExample05 {
    @JvmStatic
    fun main(args: Array<String>) {
        // 구독 시점과 상관없이 emit된 모든 데이터를 replay 한다.
        val replaySink = Sinks.many().replay().all<Int>()
        val fluxView = replaySink.asFlux()

        replaySink.emitNext(1, FAIL_FAST)
        replaySink.emitNext(2, FAIL_FAST)
        replaySink.emitNext(3, FAIL_FAST)

        fluxView.subscribe { data -> Logger.onNext("Subscriber1", data) }
        fluxView.subscribe { data -> Logger.onNext("Subscriber2", data) }
    }
}
