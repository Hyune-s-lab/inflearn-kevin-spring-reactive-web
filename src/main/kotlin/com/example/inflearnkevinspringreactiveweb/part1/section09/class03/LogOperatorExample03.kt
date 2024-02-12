package com.example.inflearnkevinspringreactiveweb.part1.section09.class03

import com.example.inflearnkevinspringreactiveweb.util.Logger
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers
import java.util.*

/**
 * log() operator Custom Category 를 사용하는 예제
 */
object LogOperatorExample03 {
    private var fruits: MutableMap<String, String> = HashMap()

    init {
        fruits["banana"] = "바나나"
        fruits["apple"] = "사과"
        fruits["pear"] = "배"
        fruits["grape"] = "포도"
    }

    @JvmStatic
    fun main(args: Array<String>) {
        Flux.fromArray(arrayOf("BANANAS", "APPLES", "PEARS", "MELONS"))
            .subscribeOn(Schedulers.boundedElastic())
            .log("Fruit.Source")
            .publishOn(Schedulers.parallel())
            .map { obj -> obj.lowercase(Locale.getDefault()) }
            .log("Fruit.Lower")
            .map { fruit -> fruit.substring(0, fruit.length - 1) }
            .log("Fruit.Substring")
            .map<String> { key -> fruits[key] }
            .log("Fruit.Name")
            .subscribe(Logger::onNext, Logger::onError)

        Thread.sleep(100L)
    }
}
