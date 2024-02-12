package com.example.inflearnkevinspringreactiveweb.part1.section07.class02

import com.example.inflearnkevinspringreactiveweb.util.Logger
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers

/**
 * Schedulers.single()을 적용 후,
 * 첫번째 Schedulers.single()에서 할당 된 쓰레드를 재사용 한다.
 */
object SchedulersSingleExample02 {
    @JvmStatic
    fun main(args: Array<String>) {
        doTask("task1").subscribe(Logger::onNext)
        doTask("task2").subscribe(Logger::onNext)

        Thread.sleep(200L)
    }

    private fun doTask(taskName: String): Flux<Int> {
        return Flux.fromArray(arrayOf(1, 3, 5, 7))
            .doOnNext { data -> Logger.doOnNext(taskName, "fromArray", data) }
            .publishOn(Schedulers.newSingle("new-single", true))
            .filter { data -> data > 3 }
            .doOnNext { data -> Logger.doOnNext(taskName, "filter", data) }
            .map { data -> data * 10 }
            .doOnNext { data -> Logger.doOnNext(taskName, "map", data) }
    }
}
