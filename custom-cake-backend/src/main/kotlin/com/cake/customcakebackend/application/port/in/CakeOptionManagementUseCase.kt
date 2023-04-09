package com.cake.customcakebackend.application.port.`in`

import com.cake.customcakebackend.domain.CakeOption


interface CakeOptionManagementUseCase {

    fun loadCakeOptionInfo(cakeOptionType: Long, cakeOptionId: Long): CakeOption
    fun loadAllCakeOptionList(storeId: Long): Map<Int, List<CakeOption>>
    fun loadCakeOptionList(storeId: Long, cakeOptionType: Long): List<CakeOption>
    fun saveCakeOption(): Pair<Long, Long>  // type, id
    fun modifyCakeOption(): Pair<Long, Long>  // type, id
    fun deleteCakeOption()
}