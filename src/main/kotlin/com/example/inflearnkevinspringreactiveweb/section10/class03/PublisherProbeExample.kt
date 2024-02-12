package com.example.inflearnkevinspringreactiveweb.section10.class03

import reactor.core.publisher.Mono

object PublisherProbeExample {
    fun processWith(main: Mono<String>, standby: Mono<String>): Mono<String> {
        return main
            .flatMap { massage -> Mono.just(massage) }
            .switchIfEmpty(standby)
    }

    fun useMainPower(): Mono<String> {
        return Mono.empty()
    }

    fun useStandbyPower(): Mono<String> {
        return Mono.just("# use Standby Power")
    }
}
