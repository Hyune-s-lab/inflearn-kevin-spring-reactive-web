package com.example.inflearnkevinspringreactiveweb.section07.class01

import com.example.inflearnkevinspringreactiveweb.util.Logger
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers

/**
 * subscribeOn( )과 publishOn( )이 같이 있다면, publishOn( )을 만나기 전 까지의 Upstream Operator 체인은
 * subscribeOn( )에서 지정한 쓰레드에서 실행되고, publishOn( )을 만날때마다
 * publishOn( ) 아래의 Operator 체인 downstream은 publishOn( )에서 지정한 쓰레드에서 실행된다.
 */
object SchedulerOperatorExample06 {
    @JvmStatic
    fun main(args: Array<String>) {
        Flux.fromArray(arrayOf(1, 3, 5, 7))
            .doOnNext { data -> Logger.doOnNext("fromArray", data) }
            .publishOn(Schedulers.parallel())
            .filter { data -> data > 3 }
            .doOnNext { data -> Logger.doOnNext("filter", data) }
            .subscribeOn(Schedulers.boundedElastic())
            .map { data -> data * 10 }
            .doOnNext { data -> Logger.doOnNext("map", data) }
            .subscribe(Logger::onNext)

        Thread.sleep(500L)
    }
}
