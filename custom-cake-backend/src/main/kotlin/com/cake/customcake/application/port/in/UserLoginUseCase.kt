package com.cake.customcake.application.port.`in`

import com.cake.customcake.domain.User

interface UserLoginUseCase {
    fun login(user: User)
}