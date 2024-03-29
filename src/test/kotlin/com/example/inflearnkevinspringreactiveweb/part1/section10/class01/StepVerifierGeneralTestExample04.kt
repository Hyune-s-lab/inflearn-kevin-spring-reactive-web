package com.example.inflearnkevinspringreactiveweb.part1.section10.class01

import com.example.inflearnkevinspringreactiveweb.part1.section10.class01.GeneralExample.occurError
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.test.StepVerifier

/**
 * onError signal 발생 여부를 검증
 */
class StepVerifierGeneralTestExample04 {
    @Test
    fun occurErrorTest() {
        val source = Flux.just(2, 4, 6, 8, 10)
        StepVerifier
            .create(occurError(source))
            .expectSubscription()
            .expectNext(1)
            .expectNext(2)
            .expectNext(3)
            .expectNext(4)
            .expectError()
            .verify()
    }
}
