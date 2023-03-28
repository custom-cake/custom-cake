package com.cake.customcakebackend.infrastructure.gateway

import org.springframework.stereotype.Component

@Component
class VerificationCodeStorageImpl : VerificationCodeStorage {

    private val tempStorage = HashMap<String, String>()

    // Todo : put code in redis (Redis TTL)
    override fun put(key: String, code: String, expireTime: Long) {
        tempStorage[key] = code
    }

    override fun confirm(key: String, code: String): Boolean {
        if (tempStorage[key] != code)
            return false

        tempStorage.remove(key)
        return true
    }
}