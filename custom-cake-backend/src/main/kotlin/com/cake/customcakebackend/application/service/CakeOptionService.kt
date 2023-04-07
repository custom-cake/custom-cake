package com.cake.customcakebackend.application.service

import com.cake.customcakebackend.application.port.`in`.CakeOptionManagementUseCase
import com.cake.customcakebackend.domain.CakeOption

class CakeOptionService : CakeOptionManagementUseCase {
    override fun loadCakeOptionInfo(cakeOptionType: Long, cakeOptionId: Long): CakeOption {
        TODO("Not yet implemented")
    }

    override fun loadCakeOptionList(storeId: Long): List<CakeOption> {
        TODO("Not yet implemented")
    }

    override fun saveCakeOption(): Pair<Long, Long> {
        TODO("Not yet implemented")
    }

    override fun modifyCakeOption(): Pair<Long, Long> {
        TODO("Not yet implemented")
    }

    override fun deleteCakeOption() {
        TODO("Not yet implemented")
    }
}