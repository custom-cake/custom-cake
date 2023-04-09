package com.cake.customcakebackend.application.service

import com.cake.customcakebackend.adapter.`in`.web.dto.request.CakeOptionRequest
import com.cake.customcakebackend.application.port.`in`.CakeOptionManagementUseCase
import com.cake.customcakebackend.application.port.out.CakeOptionPort
import com.cake.customcakebackend.domain.CakeOption
import org.springframework.stereotype.Service

@Service
class CakeOptionService(
    private val cakeOptionPort: CakeOptionPort
) : CakeOptionManagementUseCase {
    override fun loadCakeOptionInfo(cakeOptionType: Int, cakeOptionId: Long): CakeOption {
        TODO("Not yet implemented")
    }

    override fun loadAllCakeOptionList(storeId: Long): Map<Int, List<CakeOption>> {
        return cakeOptionPort.loadAllCakeOptionList(storeId)
    }

    override fun loadCakeOptionList(storeId: Long, cakeOptionType: Int): List<CakeOption> {
        TODO("Not yet implemented")
    }

    override fun saveCakeOption(storeId: Long, cakeOptionType: Int, cakeOptionRequest: CakeOptionRequest): Pair<Long, Long> {
        TODO("Not yet implemented")
    }

    override fun modifyCakeOption(): Pair<Long, Long> {
        TODO("Not yet implemented")
    }

    override fun deleteCakeOption() {
        TODO("Not yet implemented")
    }
}