package nt.file2qr

import nt.file2qr.Base45Encoder.encodeToBase45QrPayload
import org.apache.tomcat.util.codec.binary.Base64
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.io.BufferedInputStream
import java.lang.Integer.min
import java.net.URL
import java.nio.ByteBuffer
import javax.servlet.http.HttpServletResponse


@RestController
class MainController {
    @GetMapping("/hello")
    fun test() = "Hello, TEST 3!"

    fun download(urlStr: String): ByteArray {
        val url = URL(urlStr)
        BufferedInputStream(url.openStream()).use {
            return it.readAllBytes()
        }
    }

    var prepared: ByteArray = "asdf khasldjf asjkdfh asjkdfh kjashdf ".toByteArray()
    var base64mode: Boolean = false
    val defaultLen = 5
    val defaultImageSize = 800

    @GetMapping("/start")
    @ResponseBody
    fun start(
        @RequestParam(required = false) len: Int?,
        @RequestParam(required = false) off: Int?,
        @RequestParam(required = false) int: Int?,
        @RequestParam(required = false) size: Int?,
        @RequestParam(required = false) base64: Boolean?,
        @RequestParam(required = false) extended: Boolean?,
    ): String {
        base64mode = base64 ?: false
        val l = len ?: defaultLen
        val o = off ?: 0
        val int0 = int ?: 1000
        val result = "<html><body><img src=\"/qr?len=$l&off=$o&size=${size ?: defaultImageSize}&base64=$base64mode&extended=${extended ?: true}\"></img></body></html>"
        return if (o + l >= prepared.size) result else
            result + """
                <script>
                    document.addEventListener('DOMContentLoaded', function(){
                        setTimeout(next, $int0);
                    });
                    function next(){
                        window.location.href = '/start?len=$l&off=${o + l}&int=$int0&size=${size ?: defaultImageSize}&base64=$base64mode&extended=${extended ?: true}';
                    }
                </script>
            """.trimIndent()
    }

    @GetMapping("/prepare")
    fun prepare(@RequestParam lnk: String) {
        prepared = download(lnk)
    }

    fun ByteArray.buildBuffer(start: Int, len: Int, size: Int, extended: Boolean): ByteArray {
        val end = min(start + len, size)
        val data = copyOfRange(start, end)
        return if (extended) ByteBuffer.allocate(Int.SIZE_BYTES).putInt(start).array()
            .plus(ByteBuffer.allocate(Int.SIZE_BYTES).putInt(end).array())
            .plus(data)
        else data
    }

    @GetMapping("/qr")
    fun getQR(
        response: HttpServletResponse,
        @RequestParam len: Int,
        @RequestParam off: Int,
        @RequestParam(required = false) size: Int?,
        @RequestParam(required = false) base64: Boolean?,
        @RequestParam(required = false) extended: Boolean?,
    ) {
        response.contentType = MediaType.IMAGE_JPEG_VALUE
        prepared
            ?.takeUnless { off > prepared.size }
            ?.buildBuffer(off, len, prepared.size, extended ?: true)
            /*?.also {

                println(it.size)
                ByteBuffer.wrap(it.copyOfRange(0,4)).int.let(::println)
                ByteBuffer.wrap(it.copyOfRange(4,8)).int.let(::println)
            }*/
            ?.let {
                if (base64 == true) it.let(Base64::encodeBase64).let(::String) else it.let(::encodeToBase45QrPayload)
            }
            ?.let {
                QrCode.createQR(
                    it, "png", size ?: defaultImageSize, size ?: defaultImageSize,
                    response.outputStream
                )
            }
    }
}