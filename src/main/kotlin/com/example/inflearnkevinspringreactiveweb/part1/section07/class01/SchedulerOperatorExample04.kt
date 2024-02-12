package com.example.inflearnkevinspringreactiveweb.part1.section07.class01

import com.example.inflearnkevinspringreactiveweb.util.Logger
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers

/**
 * subscribeOn()은 구독 직후에 실행 될 쓰레드를 지정한다.
 * 즉, 원본 Publisher의 실행 쓰레드를 subscribeOn()에서 지정한 쓰레드로 바꾼다.
 */
object SchedulerOperatorExample04 {
    @JvmStatic
    fun main(args: Array<String>) {
        Flux.fromArray(arrayOf(1, 3, 5, 7))
            .subscribeOn(Schedulers.boundedElastic())
            .doOnNext { data -> Logger.doOnNext("fromArray", data) }
            .filter { data -> data > 3 }
            .doOnNext { data -> Logger.doOnNext("filter", data) }
            .map { data -> data * 10 }
            .doOnNext { data -> Logger.doOnNext("map", data) }
            .subscribe(Logger::onNext)

        Thread.sleep(500L)
    }
}
