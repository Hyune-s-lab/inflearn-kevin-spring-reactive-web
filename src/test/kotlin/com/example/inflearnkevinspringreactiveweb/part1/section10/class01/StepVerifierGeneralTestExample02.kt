package com.example.inflearnkevinspringreactiveweb.part1.section10.class01

import com.example.inflearnkevinspringreactiveweb.part1.section10.class01.GeneralExample.sayHelloReactor
import org.junit.jupiter.api.Test
import reactor.test.StepVerifier

/***
 * expectNext()를 사용하여 emit 된 n 개의 데이터를 검증하는 예제
 */
class StepVerifierGeneralTestExample02 {
    @Test
    fun sayHelloReactorTest() {
        StepVerifier
            .create(sayHelloReactor())
            .expectSubscription()
            .expectNext("Hello")
            .expectNext("Reactor")
            .expectComplete()
            .verify()
    }
}
