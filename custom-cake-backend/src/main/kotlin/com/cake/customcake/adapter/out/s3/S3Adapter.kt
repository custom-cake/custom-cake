package com.cake.customcake.adapter.out.s3

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.ObjectMetadata
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.IOException
import java.io.InputStream

@Component
class S3Adapter(
    private val amazonS3: AmazonS3
) {
    @Value("\${cloud.aws.s3.bucket}")
    private lateinit var bucket: String

    fun upload(inputStream: InputStream, fileName: String): String {
        try {
            val objMeta = ObjectMetadata()
            objMeta.contentLength = inputStream.available().toLong()
            amazonS3.putObject(bucket, fileName, inputStream, objMeta)
        } catch (e: IOException) {
            throw RuntimeException("S3 파일 업로드 실패")
        }

        return amazonS3.getUrl(bucket, fileName).toString()
    }
}