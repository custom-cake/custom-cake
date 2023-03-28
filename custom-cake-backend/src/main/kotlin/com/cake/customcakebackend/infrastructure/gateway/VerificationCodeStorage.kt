package com.cake.customcakebackend.infrastructure.gateway

interface VerificationCodeStorage {

    fun put(key: String, code: String, expireTime: Long = 300)
    fun confirm(key: String, code: String): Boolean
}