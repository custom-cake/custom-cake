package com.cake.customcake.application.port.out

import com.cake.customcake.domain.User

interface LoadUserPort {
    fun load(id: Long): User
    fun loadUserNameAndPhone(userId: Long): Pair<String, String>
}