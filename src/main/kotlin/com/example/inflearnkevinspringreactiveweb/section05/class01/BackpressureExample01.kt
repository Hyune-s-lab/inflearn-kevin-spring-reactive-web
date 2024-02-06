package com.example.inflearnkevinspringreactiveweb.section05.class01

import com.example.inflearnkevinspringreactiveweb.util.Logger
import org.reactivestreams.Subscription
import reactor.core.publisher.BaseSubscriber
import reactor.core.publisher.Flux

/**
 * Subscriber가 처리 가능한 만큼의 request 개수를 조절하는 Backpressure 예제
 */
object BackpressureExample01 {
    @JvmStatic
    fun main(args: Array<String>) {
        Flux.range(1, 5)
            .doOnNext(Logger::doOnNext)
            .doOnRequest(Logger::doOnRequest)
            .subscribe(object: BaseSubscriber<Int>() {
                override fun hookOnSubscribe(subscription: Subscription) {
                    request(1)
                }

                override fun hookOnNext(value: Int) {
                    Thread.sleep(2000L)
                    Logger.onNext(value)
                    request(1)
                }
            })
    }
}
