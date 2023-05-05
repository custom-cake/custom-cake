package com.cake.customcake.application.port.`in`

import com.cake.customcake.adapter.`in`.web.dto.request.CakeOptionRequest
import com.cake.customcake.domain.CakeOption


interface CakeOptionManagementUseCase {

    fun loadCakeOptionInfo(cakeOptionType: Int, cakeOptionId: Long): CakeOption
    fun loadAllCakeOptionList(storeId: Long): Map<Int, List<CakeOption>>
    fun loadCakeOptionList(storeId: Long, cakeOptionType: Int): List<CakeOption>
    fun saveCakeOption(storeId: Long, cakeOptionType: Int, cakeOptionRequest: CakeOptionRequest): Pair<Int, Long>  // type, id
    fun modifyCakeOption(): Pair<Long, Long>  // type, id
    fun deleteCakeOption(cakeOptionType: Int, optionId: Long)
}