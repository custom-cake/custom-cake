package com.cake.customcakebackend.exception

class StoreException {
    class NotFound(message: String): RuntimeException(message)
}