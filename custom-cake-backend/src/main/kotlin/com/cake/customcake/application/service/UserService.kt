package com.cake.customcake.application.service

import com.cake.customcake.application.port.`in`.UserLoginUseCase
import com.cake.customcake.application.port.out.LoadUserPort
import com.cake.customcake.application.port.out.SaveUserPort
import com.cake.customcake.domain.User
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