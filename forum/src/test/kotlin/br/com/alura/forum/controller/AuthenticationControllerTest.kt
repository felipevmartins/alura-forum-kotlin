package br.com.alura.forum.controller

import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.net.URI

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AuthenticationControllerTest(
    @Autowired var mockMvc: MockMvc
) {

    @Test
    fun authenticationTestBadRequest(){
        val uri = URI("/auth")

        mockMvc
            .perform(MockMvcRequestBuilders
                .post(uri)
                .param("email", "aluno@email.com")
                .param("pass", "123456")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers
                .status()
                .isBadRequest)
    }

}