package com.example.inflearnkevinspringreactiveweb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import reactor.core.publisher.Mono

@SpringBootApplication
class InflearnKevinSpringReactiveWebApplication

fun main(args: Array<String>) {
    runApplication<InflearnKevinSpringReactiveWebApplication>(*args)

    Mono.just("Hello, World!")
        .subscribe { println(it) }
}
