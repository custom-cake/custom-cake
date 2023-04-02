package com.cake.customcakebackend.application.service

import com.cake.customcakebackend.application.port.`in`.UserLoginUseCase
import com.cake.customcakebackend.application.port.out.LoadUserPort
import com.cake.customcakebackend.application.port.out.SaveUserPort
import com.cake.customcakebackend.domain.User
import org.springframework.stereotype.Service

@Service
class UserService(
    private val loadUserPort: LoadUserPort,
    private val saveUserPort: SaveUserPort
) : UserLoginUseCase {
    override fun login(user: User) {
        saveUserPort.save(user)
    }

}