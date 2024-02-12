package com.example.inflearnkevinspringreactiveweb.section10.class03

import com.example.inflearnkevinspringreactiveweb.section10.class01.GeneralExample
import org.junit.jupiter.api.Test
import reactor.test.StepVerifier
import reactor.test.publisher.TestPublisher

/**
 * TestPublisher 를 사용해서 서비스 로직의 메서드에 대한 Unit Test 를 실시하는 예제
 * - 정상 동작하는 TestPublisher
 * - next() 사용
 * - 에러 발생 여부 검증
 */
class TestPublisherTestExample02 {
    @Test
    fun divideByTwoTest() {
        val source = TestPublisher.create<Int>()

        StepVerifier
            .create(GeneralExample.occurError(source.flux()))
            .expectSubscription()
            .then { source.next(2, 4, 6, 8, 10) }
            .expectNext(1)
            .expectNext(2)
            .expectNext(3)
            .expectNext(4)
            .expectError()
            .verify()
    }
}
