package com.cake.customcakebackend.exception

class CustomCakeException {
    class BadRequestException(message: String) : Exception(message)
    class NotFound(message: String): RuntimeException(message)
}