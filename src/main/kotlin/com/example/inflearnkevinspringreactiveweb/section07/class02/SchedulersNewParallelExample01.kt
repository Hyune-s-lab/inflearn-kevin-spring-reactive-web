package com.example.inflearnkevinspringreactiveweb.section07.class02

import com.example.inflearnkevinspringreactiveweb.util.Logger
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

/**
 * Schedulers.newParallel()을 적용
 */
object SchedulersNewParallelExample01 {
    @JvmStatic
    fun main(args: Array<String>) {
        val flux = Mono
                .just(1)
                .publishOn(Schedulers.newParallel("Parallel Thread", 4, true))


        flux.subscribe { data ->
            Thread.sleep(5000L)
            Logger.onNext("subscribe 1", data)
        }

        flux.subscribe { data ->
            Thread.sleep(4000L)
            Logger.onNext("subscribe 2", data)
        }

        flux.subscribe { data ->
            Thread.sleep(3000L)
            Logger.onNext("subscribe 3", data)
        }

        flux.subscribe { data ->
            Thread.sleep(2000L)
            Logger.onNext("subscribe 4", data)
        }

        Thread.sleep(6000L)
    }
}
