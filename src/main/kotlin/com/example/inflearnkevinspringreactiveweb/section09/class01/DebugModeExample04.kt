package com.example.inflearnkevinspringreactiveweb.section09.class01

import com.example.inflearnkevinspringreactiveweb.util.Logger
import reactor.core.publisher.Flux
import reactor.core.publisher.Hooks
import java.util.*

/**
 * onOperatorDebug() Hook 메서드를 이용한 Debug mode 예제
 */
object DebugModeExample04 {
    var fruits: MutableMap<String, String> = HashMap()

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
            .map { obj -> obj.lowercase(Locale.getDefault()) }
            .map { fruit -> fruit.substring(0, fruit.length - 1) }
            .map<String> { key -> fruits[key] }
            .map { translated -> "맛있는 $translated" }
            .subscribe(Logger::onNext, Logger::onError)
    }
}
