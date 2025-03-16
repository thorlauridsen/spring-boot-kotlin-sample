package com.github.thorlauridsen

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

/**
 * This is the BaseMockMvc class which allows you to send and test HTTP requests.
 * @param mockMvc The MockMvc instance to use for testing.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class BaseMockMvc(private val mockMvc: MockMvc) {

    /**
     * Mock an HTTP GET request.
     * @param getUrl The URL to send an HTTP GET request to.
     * @return [MockHttpServletResponse] response.
     */
    fun mockGet(getUrl: String): MockHttpServletResponse {
        return mockMvc.perform(
            MockMvcRequestBuilders
                .get(getUrl)
                .contentType(MediaType.APPLICATION_JSON),

            ).andReturn().response
    }

    /**
     * Mock an HTTP POST request.
     * @param jsonBody JSON body as a string.
     * @param postUrl The URL to send an HTTP POST request to.
     * @return [MockHttpServletResponse] response.
     */
    fun mockPost(jsonBody: String, postUrl: String): MockHttpServletResponse {
        return mockMvc.perform(
            MockMvcRequestBuilders
                .post(postUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody),

            ).andReturn().response
    }
}
