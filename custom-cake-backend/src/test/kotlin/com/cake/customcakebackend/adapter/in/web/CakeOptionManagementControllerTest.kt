package com.cake.customcakebackend.adapter.`in`.web

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers


@SpringBootTest
@AutoConfigureMockMvc
class CakeOptionManagementControllerTest(
    @Autowired
    private val mockMvc: MockMvc
) {
    @Test
    fun cakeOptionListTest() {
        mockMvc.perform(MockMvcRequestBuilders.get("/operator/cake-option?storeId=1"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.view().name("cake-option-management"))
            .andExpect(MockMvcResultMatchers.model().attributeExists("storeId"))
//            .andExpect(MockMvcResultMatchers.model().)
    }
}