package com.github.thorlauridsen

import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.client.RestTestClient

/**
 * This is the BaseControllerTest class that allows you to send and test HTTP requests.
 */
@AutoConfigureRestTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BaseControllerTest(private val restTestClient: RestTestClient) {

    /**
     * Test an HTTP GET request.
     *
     * @param getUrl the URL to send an HTTP GET request to.
     * @return [RestTestClient.ResponseSpec] response.
     */
    fun get(getUrl: String): RestTestClient.ResponseSpec {
        return restTestClient.get()
            .uri(getUrl)
            .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .exchange()
    }

    /**
     * Test an HTTP POST request.
     *
     * @param postUrl  the URL to send an HTTP POST request to.
     * @param jsonBody the JSON body to send with the request.
     * @return [RestTestClient.ResponseSpec] response.
     */
    fun post(postUrl: String, jsonBody: String): RestTestClient.ResponseSpec {
        return restTestClient.post()
            .uri(postUrl)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .body(jsonBody)
            .exchange()
    }
}
