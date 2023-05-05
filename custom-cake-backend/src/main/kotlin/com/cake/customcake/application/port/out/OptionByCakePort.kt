package com.cake.customcake.application.port.out

import com.cake.customcake.domain.OptionByCake

interface OptionByCakePort {
    fun loadList(cakeItemId: Long): List<OptionByCake>
    fun loadListByIdList(optionByCakeIdList: List<Long>, cakeItemId: Long): List<OptionByCake>
    fun loadListByIdList(optionByCakeIdList: List<Long>): List<String>
}