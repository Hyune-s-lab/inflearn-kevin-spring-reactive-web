package com.example.inflearnkevinspringreactiveweb.section03.class01

import com.jayway.jsonpath.JsonPath
import org.slf4j.LoggerFactory
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Mono

/**
 * Mono 활용 예제
 * - worldtimeapi.org Open API를 이용해서 서울의 현재 시간을 조회한다.
 */
object MonoExample03 {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @JvmStatic
    fun main(args: Array<String>) {
        val worldTimeUri = UriComponentsBuilder.newInstance().scheme("http")
            .host("worldtimeapi.org")
            .port(80)
            .path("/api/timezone/Asia/Seoul")
            .build()
            .encode()
            .toUri()

        val restTemplate = RestTemplate()
        val headers = HttpHeaders()
        headers.accept = listOf(MediaType.APPLICATION_JSON)


        Mono.just<ResponseEntity<String>>(
            restTemplate.exchange(
                worldTimeUri,
                HttpMethod.GET,
                HttpEntity<String>(headers),
                String::class.java
            )
        )
            .map { response: ResponseEntity<String> ->
                val jsonContext = JsonPath.parse(response.body)
                val dateTime = jsonContext.read<String>("$.datetime")
                dateTime
            }
            .subscribe(
                { data: String -> log.info("### emitted data: $data") },
                { error: Throwable -> log.error("### error:", error) },
                { log.info("### emitted onComplete signal") }
            )
    }
}
