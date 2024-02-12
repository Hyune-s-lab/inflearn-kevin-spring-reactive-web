package com.example.inflearnkevinspringreactiveweb.part1.section06.class01

import com.example.inflearnkevinspringreactiveweb.util.Logger
import reactor.core.publisher.Sinks
import reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST

/**
 * Sinks.One 예제
 * - 한 건의 데이터만 emit 하는 예제
 */
object SinkOneExample01 {
    @JvmStatic
    fun main(args: Array<String>) {
        // emit 된 데이터 중에서 단 하나의 데이터만 Subscriber에게 전달한다. 나머지 데이터는 Drop 됨.
        val sinkOne = Sinks.one<String>()
        val mono = sinkOne.asMono()

        sinkOne.emitValue("Hello Reactor", FAIL_FAST)

        mono.subscribe { data -> Logger.onNext("Subscriber1 ", data) }
        mono.subscribe { data -> Logger.onNext("Subscriber2 ", data) }
    }
}
