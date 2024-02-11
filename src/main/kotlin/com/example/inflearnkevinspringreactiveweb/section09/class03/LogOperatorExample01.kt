package com.example.inflearnkevinspringreactiveweb.section09.class03

import com.example.inflearnkevinspringreactiveweb.util.Logger
import reactor.core.publisher.Flux
import java.util.*

/**
 * log() operator를 사용한 예제
 */
object LogOperatorExample01 {
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
            .log()
            .map { obj -> obj.lowercase(Locale.getDefault()) }
            .map { fruit -> fruit.substring(0, fruit.length - 1) }
            .map<String> { key -> fruits[key] }
            .subscribe(Logger::onNext, Logger::onError)
    }
}
