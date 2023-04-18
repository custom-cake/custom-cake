package com.cake.customcakebackend.application.port.out

import com.cake.customcakebackend.domain.Operator

interface OperatorPort {
    fun loadByEmailAndPassword(email: String, password: String): Pair<Operator?, Long?>
    fun save(): Long
}