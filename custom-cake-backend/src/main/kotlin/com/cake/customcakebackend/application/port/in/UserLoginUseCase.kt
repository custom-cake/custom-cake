package com.cake.customcakebackend.application.port.`in`

import com.cake.customcakebackend.domain.User

interface UserLoginUseCase {
    fun login(user: User)
}