package com.cake.customcakebackend.application.port.out

import com.cake.customcakebackend.domain.CakeOption

interface CakeOptionPort {
    fun loadInfo(cakeOptionType: Int, cakeOptionId: Long): CakeOption
    fun loadAllCakeOptionList(storeId: Long): Map<Int, List<CakeOption>>
    fun loadCakeOptionList(storeId: Long, cakeOptionType: Int): List<CakeOption>
    fun save(storeId: Long, cakeOptionType: Int): Pair<Long, Long>  // type, id
    fun modify(): Pair<Long, Long>  // type, id
    fun delete()
}