package com.cake.customcake.application.port.out

import com.cake.customcake.adapter.out.persistence.entity.CakeOptionEntity
import com.cake.customcake.domain.CakeOption

interface CakeOptionPort {
    fun loadInfo(cakeOptionType: Int, cakeOptionId: Long): CakeOption
    fun loadAllCakeOptionList(storeId: Long): Map<Int, List<CakeOption>>
    fun loadCakeOptionList(storeId: Long, cakeOptionType: Int): List<CakeOption>
    fun save(cakeOptionType: Int, cakeOption: CakeOption): Pair<Int, Long>  // type, id
    fun modify(): Pair<Long, Long>  // type, id
    fun delete(cakeOptionType: Int, optionId: Long)
    fun loadListByIdList(optionIdList: List<Long>): List<String>
}