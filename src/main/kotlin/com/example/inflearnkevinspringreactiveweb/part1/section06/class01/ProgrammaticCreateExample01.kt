package com.example.inflearnkevinspringreactiveweb.part1.section06.class01

import com.example.inflearnkevinspringreactiveweb.util.Logger
import reactor.core.publisher.Flux
import java.util.stream.IntStream

/**
 * create() Operator를 사용하는 예제
 * - 일반적으로 Publisher의 데이터 생성을 단일 쓰레드에서 진행한다. 멀티 쓰레드에서도 가능
 * - 데이터 emit은 create Operator 내부에서 가능.
 * - Backpressure 적용 가능
 */
object ProgrammaticCreateExample01 {
    @Throws(InterruptedException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val tasks = 6
        Flux
            .create<String> { sink ->
                IntStream
                    .range(1, tasks)
                    .forEach { n -> sink.next(doTask(n)) }
            } //                .subscribeOn(Schedulers.boundedElastic())
            //                .doOnNext(n -> log.info("# create(): {}", n))
            //                .publishOn(Schedulers.parallel())
            //                .map(result -> result + " success!")
            //                .doOnNext(n -> log.info("# map(): {}", n))
            //                .publishOn(Schedulers.parallel())
            .subscribe { data -> Logger.info("### onNext: {}", data) }

        Thread.sleep(500L)
    }

    private fun doTask(taskNumber: Int): String {
        return "task $taskNumber result"
    }
}
