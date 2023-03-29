package com.cake.customcakebackend.application.port.out

import com.cake.customcakebackend.domain.User

interface LoadUserPort {
    fun load(id: Long): User
}