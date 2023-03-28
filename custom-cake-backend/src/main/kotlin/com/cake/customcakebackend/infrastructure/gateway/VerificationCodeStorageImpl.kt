package com.cake.customcakebackend.infrastructure.gateway

import org.springframework.stereotype.Component

@Component
class VerificationCodeStorageImpl : VerificationCodeStorage {

    private val tempStorage = HashMap<String, String>()

    // Todo : put code in redis (Redis TTL)
    override fun put(key: String, code: String, expireTime: Long) {
        tempStorage[key] = code
    }
}