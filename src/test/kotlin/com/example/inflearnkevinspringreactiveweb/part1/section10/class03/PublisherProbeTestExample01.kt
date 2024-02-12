package com.example.inflearnkevinspringreactiveweb.part1.section10.class03

import com.example.inflearnkevinspringreactiveweb.part1.section10.class03.PublisherProbeExample.processWith
import com.example.inflearnkevinspringreactiveweb.part1.section10.class03.PublisherProbeExample.useMainPower
import com.example.inflearnkevinspringreactiveweb.part1.section10.class03.PublisherProbeExample.useStandbyPower
import org.junit.jupiter.api.Test
import reactor.test.StepVerifier
import reactor.test.publisher.PublisherProbe

class PublisherProbeTestExample01 {
    @Test
    fun publisherProbeTest() {
        val probe: PublisherProbe<String> = PublisherProbe.of(useStandbyPower())

        StepVerifier
            .create(processWith(useMainPower(), probe.mono()))
            .expectNextCount(1)
            .verifyComplete()

        probe.assertWasSubscribed()
        probe.assertWasRequested()
        probe.assertWasNotCancelled()
    }
}
