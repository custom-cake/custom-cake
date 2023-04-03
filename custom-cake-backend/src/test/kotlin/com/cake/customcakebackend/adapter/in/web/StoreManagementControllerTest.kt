package com.cake.customcakebackend.adapter.`in`.web

import com.cake.customcakebackend.adapter.`in`.web.dto.response.StoreInfoResponse
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@AutoConfigureMockMvc
class StoreManagementControllerTest(
    @Autowired
    private val mockMvc: MockMvc
) {
    @Test
    fun storeInfoTest() {
        mockMvc.perform(get("/operator/store/1"))
            .andExpect(status().isOk)
            .andExpect(view().name("store-management"))
            .andExpect(model().attributeExists("storeInfo"))
    }

}