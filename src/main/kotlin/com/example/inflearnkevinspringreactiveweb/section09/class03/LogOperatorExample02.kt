package com.example.inflearnkevinspringreactiveweb.section09.class03

import com.example.inflearnkevinspringreactiveweb.util.Logger
import reactor.core.publisher.Flux
import reactor.core.publisher.Hooks
import java.util.*

/**
 * log() operator와 Debug mode 를 같이 사용한 예제
 * - log()는 에러 발생 시, stacktrace와 함께 traceback도 같이 출력한다.
 */
object LogOperatorExample02 {
    private var fruits: MutableMap<String, String> = HashMap()

    init {
        fruits["banana"] = "바나나"
        fruits["apple"] = "사과"
        fruits["pear"] = "배"
        fruits["grape"] = "포도"
    }

    @JvmStatic
    fun main(args: Array<String>) {
        Hooks.onOperatorDebug()

        Flux.fromArray(arrayOf("BANANAS", "APPLES", "PEARS", "MELONS"))
            .log()
            .map { obj -> obj.lowercase(Locale.getDefault()) }
            .log()
            .map { fruit -> fruit.substring(0, fruit.length - 1) }
            .log()
            .map<String> { key -> fruits[key] }
            .log()
            .subscribe(Logger::onNext, Logger::onError)
    }
}
