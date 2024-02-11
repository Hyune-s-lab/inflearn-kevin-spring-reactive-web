package com.example.inflearnkevinspringreactiveweb.section10.class01

import com.example.inflearnkevinspringreactiveweb.section10.class01.TimeBasedExample.getVoteCount
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.test.StepVerifier
import reactor.util.function.Tuples
import java.time.Duration

/**
 * expectNoEvent(Duration)으로 지정된 대기 시간동안 이벤트가 없을을 확인하는 예제
 */
class StepVerifierTimeBasedTestExample04 {
    @Test
    fun getCOVID19CountTest() {
        StepVerifier
            .withVirtualTime {
                getVoteCount(
                    Flux.interval(Duration.ofMinutes(1))
                )
            }
            .expectSubscription()
            .expectNoEvent(Duration.ofMinutes(1))
            .expectNext(Tuples.of("중구", 15400))
            .expectNoEvent(Duration.ofMinutes(1))
            .expectNoEvent(Duration.ofMinutes(1))
            .expectNoEvent(Duration.ofMinutes(1))
            .expectNoEvent(Duration.ofMinutes(1))
            .expectNextCount(4)
            .expectComplete()
            .verify()
    }
}
