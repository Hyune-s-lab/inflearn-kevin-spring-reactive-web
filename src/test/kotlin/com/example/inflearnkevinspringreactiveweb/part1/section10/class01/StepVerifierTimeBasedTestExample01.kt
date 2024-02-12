package com.example.inflearnkevinspringreactiveweb.part1.section10.class01

import com.example.inflearnkevinspringreactiveweb.part1.section10.class01.TimeBasedExample.getCOVID19Count
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.test.StepVerifier
import reactor.test.scheduler.VirtualTimeScheduler
import java.time.Duration

/**
 * 실제 시간을 가상 시간으로 대체하는 테스트 예제
 * - 특정 시간만큼 시간을 앞당긴다.
 */
class StepVerifierTimeBasedTestExample01 {
    @Test
    fun getCOVID19CountTest() {
        StepVerifier
            .withVirtualTime {
                getCOVID19Count(
                    Flux.interval(Duration.ofHours(12)).take(1)
                )
            }
            .expectSubscription()
            .then { VirtualTimeScheduler.get().advanceTimeBy(Duration.ofHours(12)) }
            .expectNextCount(11)
            .expectComplete()
            .verify()
    }
}
