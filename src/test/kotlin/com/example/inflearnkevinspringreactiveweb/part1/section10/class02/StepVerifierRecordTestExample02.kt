package com.example.inflearnkevinspringreactiveweb.part1.section10.class02

import com.example.inflearnkevinspringreactiveweb.part1.section10.class02.RecordExample.getCountry
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.test.StepVerifier

/**
 * emit 되는 모든 데이터들을 캡쳐하여 컬렉션에 기록한 후, 기록된 데이터들을 검증하는 예제
 * - recordWith()를 사용하여 emit 된 데이터를 기록하는 세션을 시작한다.
 * - thenConsumeWhile()을 사용하여 조건에 맞는 데이터만 소비한다. 여기서 조건에 맞는 데이터들이 ArrayList 에 추가(기록)된다.
 * - expectRecordedMatches()를 사용하여 기록된 데이터의 컬렉션을 검증한다.
 */
class StepVerifierRecordTestExample02 {
    @Test
    fun getCountryTest() {
        StepVerifier
            .create(getCountry(Flux.just("france", "russia", "greece", "poland")))
            .expectSubscription()
            .recordWith { ArrayList() }
            .thenConsumeWhile { country -> country.isNotEmpty() }
            .expectRecordedMatches { countries ->
                countries
                    .stream()
                    .allMatch { country: String -> Character.isUpperCase(country[0]) }
            }
            .expectComplete()
            .verify()
    }
}
