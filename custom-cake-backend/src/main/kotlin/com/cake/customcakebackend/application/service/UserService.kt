package com.cake.customcakebackend.application.service

import com.cake.customcakebackend.application.port.`in`.UserLoginUseCase
import com.cake.customcakebackend.application.port.out.LoadUserPort
import com.cake.customcakebackend.application.port.out.SaveUserPort
import com.cake.customcakebackend.domain.User

class UserService(
    private val loadUserPort: LoadUserPort,
    private val saveUserPort: SaveUserPort
) : UserLoginUseCase {
    override fun login(user: User) {
        TODO("Login logic")
        return saveUserPort.save(user)
    }

}