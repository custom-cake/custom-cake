package com.cake.customcake.application.port.out

import com.cake.customcake.domain.Operator

interface OperatorPort {
    fun loadByEmailAndPassword(email: String, password: String): Pair<Operator?, Long?>
    fun save(): Long
}