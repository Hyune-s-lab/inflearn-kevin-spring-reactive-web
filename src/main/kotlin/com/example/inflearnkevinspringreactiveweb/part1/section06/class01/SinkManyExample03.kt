package com.example.inflearnkevinspringreactiveweb.part1.section06.class01

import com.example.inflearnkevinspringreactiveweb.util.Logger
import reactor.core.publisher.Sinks
import reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST

/**
 * Sinks.Many 예제
 * - replay()를 사용하여 이미 emit된 데이터 중에서 특정 개수의 최신 데이터만 전달하는 예제
 */
object SinkManyExample03 {
    @JvmStatic
    fun main(args: Array<String>) {
        // 구독 이후, emit 된 데이터 중에서 최신 데이터 2개만 replay 한다.
        val replaySink = Sinks.many().replay().limit<Int>(2)
        val fluxView = replaySink.asFlux()

        replaySink.emitNext(1, FAIL_FAST)
        replaySink.emitNext(2, FAIL_FAST)
        replaySink.emitNext(3, FAIL_FAST)

        fluxView.subscribe { data -> Logger.onNext("Subscriber1", data) }
        fluxView.subscribe { data -> Logger.onNext("Subscriber2", data) }
    }
}
