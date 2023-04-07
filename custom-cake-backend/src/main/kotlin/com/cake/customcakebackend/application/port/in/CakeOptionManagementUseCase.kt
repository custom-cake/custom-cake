package com.cake.customcakebackend.application.port.`in`

import com.cake.customcakebackend.domain.CakeOption

interface CakeOptionManagementUseCase {

    fun loadCakeOptionInfo(cakeOptionType: Long, cakeOptionId: Long): CakeOption
    fun loadCakeOptionList(storeId: Long): List<CakeOption>
    fun saveCakeOption(): Pair<Long, Long>  // type, id
    fun modifyCakeOption(): Pair<Long, Long>  // type, id
    fun deleteCakeOption()
}