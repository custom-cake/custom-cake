package com.cake.customcake.application.port.out

import java.awt.image.BufferedImage

interface UploadImagePort {
    fun uploadImage(image: BufferedImage, fullFileName: String): String
}