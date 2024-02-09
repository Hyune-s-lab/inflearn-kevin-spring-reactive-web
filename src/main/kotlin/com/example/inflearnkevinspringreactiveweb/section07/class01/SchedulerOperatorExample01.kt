package com.example.inflearnkevinspringreactiveweb.section07.class01

import com.example.inflearnkevinspringreactiveweb.util.Logger
import reactor.core.publisher.Flux

/**
 * Sequence의 Operator 체인에서 최초의 쓰레드는 subscribe()가
 * 호출되는 scope에 있는 쓰레드이다.
 */
object SchedulerOperatorExample01 {
    @JvmStatic
    fun main(args: Array<String>) {
        Flux.fromArray(arrayOf(1, 3, 5, 7))
            .filter { data -> data > 3 }
            .map { data -> data * 10 }
            .subscribe(Logger::onNext)
    }
}
