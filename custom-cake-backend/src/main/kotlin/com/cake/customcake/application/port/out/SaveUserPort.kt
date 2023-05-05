package com.cake.customcake.application.port.out

import com.cake.customcake.domain.User

interface SaveUserPort {
    fun save(user: User)
}