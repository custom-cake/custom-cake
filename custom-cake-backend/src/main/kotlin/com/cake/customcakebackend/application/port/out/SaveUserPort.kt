package com.cake.customcakebackend.application.port.out

import com.cake.customcakebackend.domain.User

interface SaveUserPort {
    fun save(user: User)
}