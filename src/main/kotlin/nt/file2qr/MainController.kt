package nt.file2qr

import nt.file2qr.Base45Encoder.encodeToBase45QrPayload
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.io.BufferedInputStream
import java.lang.Integer.min
import java.net.URL
import javax.servlet.http.HttpServletResponse


@RestController
class MainController {
    @GetMapping("/hello")
    fun test() = "Hello, TEST!"

    fun download(urlStr: String): ByteArray {
        val url = URL(urlStr)
        BufferedInputStream(url.openStream()).use {
            return it.readAllBytes()
        }
    }

    var prepared: ByteArray = "asdf khasldjf asjkdfh asjkdfh kjashdf ".toByteArray()
    val defaultLen = 5

    @GetMapping("/start")
    @ResponseBody
    fun start(
        @RequestParam(required = false) len: Int?,
        @RequestParam(required = false) off: Int?,
        @RequestParam(required = false) int: Int?,
    ): String {
        val l = len ?: defaultLen
        val o = off ?: 0
        val int0 = int ?: 1000
        val result = "<html><body><img src=\"/qr?len=$l&off=$o\"></img></body></html>"
        return if (o + l >= prepared.size) result else
            result +
                    "<script>\n" +
                    "         setTimeout(next, $int0);\n" +
                    "         function next(){\n" +
                    "            window.location.href = '/start?len=$l&off=${o + l}&int=$int0';\n" +
                    "         }\n" +
                    "      </script>"
    }

    @GetMapping("/prepare")
    fun prepare(@RequestParam lnk: String) {
        prepared = download(lnk)
    }

    @GetMapping("/qr")
    fun getQR(
        response: HttpServletResponse,
        @RequestParam len: Int,
        @RequestParam off: Int,
        @RequestParam(required = false) size: Int?,
    ) {
        response.contentType = MediaType.IMAGE_JPEG_VALUE

        println("$len $off")

        prepared
            ?.takeUnless { off > prepared.size }
            ?.copyOfRange(off, min(off + len, prepared.size))
            ?.let(::encodeToBase45QrPayload)
            ?.let {
                QrCode.createQR(
                    it, "png", size ?: 500, size ?: 500,
                    response.outputStream
                )
            }
    }
}