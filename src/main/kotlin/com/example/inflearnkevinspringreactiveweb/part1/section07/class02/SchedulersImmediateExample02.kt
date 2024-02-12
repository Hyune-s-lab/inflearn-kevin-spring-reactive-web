package com.example.inflearnkevinspringreactiveweb.part1.section07.class02

import com.example.inflearnkevinspringreactiveweb.util.Logger
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers

/**
 * Schedulers.immediate()을 적용 후,
 * 현재 쓰레드가 할당된다.
 */
object SchedulersImmediateExample02 {
    @JvmStatic
    fun main(args: Array<String>) {
        Flux.fromArray(arrayOf(1, 3, 5, 7))
            .publishOn(Schedulers.parallel())
            .filter { data -> data > 3 }
            .doOnNext { data -> Logger.doOnNext("filter", data) }
            .publishOn(Schedulers.immediate())
            .map { data -> data * 10 }
            .doOnNext { data -> Logger.doOnNext("map", data) }
            .subscribe(Logger::onNext)

        Thread.sleep(200L)
    }
}
