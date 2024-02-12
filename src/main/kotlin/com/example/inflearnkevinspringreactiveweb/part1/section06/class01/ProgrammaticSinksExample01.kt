package com.example.inflearnkevinspringreactiveweb.part1.section06.class01

import com.example.inflearnkevinspringreactiveweb.util.Logger
import reactor.core.publisher.Sinks
import java.util.stream.IntStream

/**
 * Sinks를 사용하는 예제
 * - Publisher의 데이터 생성을 멀티 쓰레드에서 진행해도 Thread safe 하다.
 */
object ProgrammaticSinksExample01 {
    @Throws(InterruptedException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val tasks = 6

        val unicastSink = Sinks.many().unicast().onBackpressureBuffer<String>()
        val fluxView = unicastSink.asFlux()

        IntStream
            .range(1, tasks)
            .forEach { n ->
                try {
                    Thread {
                        unicastSink.emitNext(doTask(n), Sinks.EmitFailureHandler.FAIL_FAST)
                        Logger.info("### emitted: {}", n)
                    }.start()
                    Thread.sleep(100L)
                } catch (_: InterruptedException) {
                }
            }

        fluxView
            //                .publishOn(Schedulers.parallel())
            //                .map(result -> result + " success!")
            //                .doOnNext(n -> log.info("### map(): {}", n))
            //                .publishOn(Schedulers.parallel())
            .subscribe { data -> Logger.info("### onNext: {}", data) }

        Thread.sleep(200L)
    }

    private fun doTask(taskNumber: Int): String {
        // now tasking.
        // complete to task.
        return "task $taskNumber result"
    }
}
