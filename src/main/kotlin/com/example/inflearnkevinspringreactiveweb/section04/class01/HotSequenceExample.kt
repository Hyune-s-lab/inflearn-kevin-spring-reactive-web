package com.example.inflearnkevinspringreactiveweb.section04.class01

import org.slf4j.LoggerFactory
import reactor.core.publisher.Flux
import java.time.Duration
import java.util.stream.Stream

object HotSequenceExample {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @JvmStatic
    fun main(args: Array<String>) {
        val concertFlux = Flux.fromStream(Stream.of("Singer A", "Singer B", "Singer C", "Singer D", "Singer E"))
            .delayElements(Duration.ofSeconds(1)).share() //  share() 원본 Flux를 여러 Subscriber가 공유한다.

        concertFlux.subscribe { singer -> log.info("### Subscriber1 is watching {}'s song.", singer) }

        Thread.sleep(2500)

        concertFlux.subscribe { singer -> log.info("### Subscriber2 is watching {}'s song.", singer) }

        Thread.sleep(3000)
    }
}
