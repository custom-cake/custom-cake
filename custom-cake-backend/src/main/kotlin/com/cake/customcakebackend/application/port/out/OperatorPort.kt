package com.cake.customcakebackend.application.port.out

import com.cake.customcakebackend.domain.Operator

interface OperatorPort {
    fun loadByEmailAndPassword(email: String, password: String): List<Operator>
    fun save(): Long
}