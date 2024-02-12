package com.example.inflearnkevinspringreactiveweb.part1.section08.class03

import com.example.inflearnkevinspringreactiveweb.util.Logger
import reactor.core.publisher.Mono
import reactor.util.context.Context

/**
 * Context 활용 예제
 * - 직교성을 가지는 정보를 표현할 때 주로 사용된다.
 */
object ContextRealExample01 {
    private const val HEADER_NAME_AUTH_TOKEN: String = "authToken"

    @JvmStatic
    fun main(args: Array<String>) {
        val mono =
            postBook(Mono.just(Book("abcd-1111-3533-2809", "Reactor's Bible", "Kevin")))
                .contextWrite(Context.of(HEADER_NAME_AUTH_TOKEN, "eyJhbGciOiJIUzUxMiJ9.eyJzdWI"))

        mono.subscribe(Logger::onNext)
    }

    private fun postBook(book: Mono<Book>): Mono<String> {
        return Mono.zip(
            book,
            Mono.deferContextual { ctx -> Mono.just(ctx.get<Any>(HEADER_NAME_AUTH_TOKEN)) }
        )
            .flatMap { tuple -> Mono.just(tuple) } // 외부 API 서버로 HTTP POST request를 전송한다고 가정
            .flatMap { tuple ->
                val response =
                    "POST the book(" + tuple.t1.bookName + "," + tuple.t1.author + ") with token: " + tuple.t2
                Mono.just(response) // HTTP response를 수신했다고 가정
            }
    }
}
