package com.cake.customcake.adapter.out.s3

import com.cake.customcake.application.port.out.UploadImagePort
import org.springframework.stereotype.Component
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO

@Component
class S3ImageAdapter(
    private val s3Adapter: S3Adapter
) : UploadImagePort {
    override fun uploadImage(image: BufferedImage, fullFileName: String): String {
        val out = ByteArrayOutputStream()
        ImageIO.write(image, "jpeg", out)
        val inputStream = ByteArrayInputStream(out.toByteArray())

        return s3Adapter.upload(inputStream, fullFileName)
    }
}