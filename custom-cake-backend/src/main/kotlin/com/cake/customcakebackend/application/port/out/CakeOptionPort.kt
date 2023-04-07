package com.cake.customcakebackend.application.port.out

import com.cake.customcakebackend.domain.CakeOption

interface CakeOptionPort {
    fun loadInfo(cakeOptionType: Long, cakeOptionId: Long): CakeOption
    fun loadList(storeId: Long): List<CakeOption>
    fun save(): Pair<Long, Long>  // type, id
    fun modify(): Pair<Long, Long>  // type, id
    fun delete()
}