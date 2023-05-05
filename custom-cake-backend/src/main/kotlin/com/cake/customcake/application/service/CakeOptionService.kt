package com.cake.customcake.application.service

import com.cake.customcake.adapter.`in`.web.dto.request.CakeOptionRequest
import com.cake.customcake.application.port.`in`.CakeOptionManagementUseCase
import com.cake.customcake.application.port.out.CakeOptionPort
import com.cake.customcake.domain.CakeOption
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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

    @Transactional
    override fun saveCakeOption(storeId: Long, cakeOptionType: Int, cakeOptionRequest: CakeOptionRequest): Pair<Int, Long> {
        val cakeOptionDomain = cakeOptionRequest.toDomain(storeId)
        return cakeOptionPort.save(cakeOptionType, cakeOptionDomain)
    }

    @Transactional
    override fun modifyCakeOption(): Pair<Long, Long> {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun deleteCakeOption(cakeOptionType: Int, optionId: Long) =
        cakeOptionPort.delete(cakeOptionType, optionId)
}