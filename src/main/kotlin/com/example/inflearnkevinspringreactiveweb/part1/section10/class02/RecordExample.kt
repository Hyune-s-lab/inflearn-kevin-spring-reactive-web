package com.example.inflearnkevinspringreactiveweb.part1.section10.class02

import reactor.core.publisher.Flux
import java.util.*

object RecordExample {
    fun getCountry(source: Flux<String>): Flux<String> {
        return source
            .map { country -> country.substring(0, 1).uppercase(Locale.getDefault()) + country.substring(1) }
    }
}
