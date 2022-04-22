package nt.file2qr

import com.google.zxing.*
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import java.io.File
import java.io.IOException
import java.io.OutputStream
import kotlin.Throws
import kotlin.jvm.JvmStatic

object QrCode {
    @Throws(WriterException::class, IOException::class)
    fun createQR(
        data: String,
        format: String,
        height: Int, width: Int,
        stream: OutputStream
    ) {
        val matrix: BitMatrix = MultiFormatWriter().encode(
            data,
            BarcodeFormat.QR_CODE, width, height
        )
        MatrixToImageWriter.writeToStream(
            matrix,
            format,
            stream
        )
    }
}