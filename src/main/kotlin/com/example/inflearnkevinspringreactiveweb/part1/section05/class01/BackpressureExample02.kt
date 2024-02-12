package com.example.inflearnkevinspringreactiveweb.part1.section05.class01

import com.example.inflearnkevinspringreactiveweb.util.Logger
import org.reactivestreams.Subscription
import reactor.core.publisher.BaseSubscriber
import reactor.core.publisher.Flux

/**
 * Subscriber가 처리 가능한 만큼의 request 갯수를 조절하는 Backpressure 예제
 */
object BackpressureExample02 {
    var count: Int = 0

    @Throws(InterruptedException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        Flux.range(1, 5)
            .doOnNext(Logger::doOnNext)
            .doOnRequest(Logger::doOnRequest)
            .subscribe(object: BaseSubscriber<Int>() {
                override fun hookOnSubscribe(subscription: Subscription) {
                    request(2)
                }

                override fun hookOnNext(value: Int) {
                    count++
                    Logger.onNext(value)
                    if (count == 2) {
                        Thread.sleep(2000L)
                        request(2)
                        count = 0
                    }
                }
            })
    }
}
