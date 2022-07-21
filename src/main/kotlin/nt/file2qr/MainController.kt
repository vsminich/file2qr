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
    val stats = mutableMapOf<Int, Int>()
    val shrinks = mutableMapOf<Int, Int>()

    @GetMapping("/hello")
    fun test() = "Hello, TEST 4! $stats shrinks: $shrinks"

    fun download(urlStr: String): ByteArray {
        val url = URL(urlStr)
        BufferedInputStream(url.openStream()).use {
            return it.readAllBytes()
        }
    }

    var prepared: ByteArray = """C:\Users\minic\.jdks\openjdk-17.0.2\bin\java.exe -XX:TieredStopAtLevel=1 -noverify -Dspring.output.ansi.enabled=always -Dcom.sun.management.jmxremote -Dspring.jmx.enabled=true -Dspring.liveBeansView.mbeanDomain -Dspring.application.admin.enabled=true "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2021.3.3\lib\idea_rt.jar=58265:C:\Program Files\JetBrains\IntelliJ IDEA 2021.3.3\bin" -Dfile.encoding=UTF-8 -classpath C:\Work\file2qr\target\classes;C:\Users\minic\.m2\repository\org\springframework\boot\spring-boot-starter-web\2.6.7\spring-boot-starter-web-2.6.7.jar;C:\Users\minic\.m2\repository\org\springframework\boot\spring-boot-starter\2.6.7\spring-boot-starter-2.6.7.jar;C:\Users\minic\.m2\repository\org\springframework\boot\spring-boot\2.6.7\spring-boot-2.6.7.jar;C:\Users\minic\.m2\repository\org\springframework\boot\spring-boot-autoconfigure\2.6.7\spring-boot-autoconfigure-2.6.7.jar;C:\Users\minic\.m2\repository\org\springframework\boot\spring-boot-starter-logging\2.6.7\spring-boot-starter-logging-2.6.7.jar;C:\Users\minic\.m2\repository\ch\qos\logback\logback-classic\1.2.11\logback-classic-1.2.11.jar;C:\Users\minic\.m2\repository\ch\qos\logback\logback-core\1.2.11\logback-core-1.2.11.jar;C:\Users\minic\.m2\repository\org\apache\logging\log4j\log4j-to-slf4j\2.17.2\log4j-to-slf4j-2.17.2.jar;C:\Users\minic\.m2\repository\org\apache\logging\log4j\log4j-api\2.17.2\log4j-api-2.17.2.jar;C:\Users\minic\.m2\repository\org\slf4j\jul-to-slf4j\1.7.36\jul-to-slf4j-1.7.36.jar;C:\Users\minic\.m2\repository\jakarta\annotation\jakarta.annotation-api\1.3.5\jakarta.annotation-api-1.3.5.jar;C:\Users\minic\.m2\repository\org\yaml\snakeyaml\1.29\snakeyaml-1.29.jar;C:\Users\minic\.m2\repository\org\springframework\boot\spring-boot-starter-json\2.6.7\spring-boot-starter-json-2.6.7.jar;C:\Users\minic\.m2\repository\com\fasterxml\jackson\datatype\jackson-datatype-jdk8\2.13.2\jackson-datatype-jdk8-2.13.2.jar;C:\Users\minic\.m2\repository\com\fasterxml\jackson\datatype\jackson-datatype-jsr310\2.13.2\jackson-datatype-jsr310-2.13.2.jar;C:\Users\minic\.m2\repository\com\fasterxml\jackson\module\jackson-module-parameter-names\2.13.2\jackson-module-parameter-names-2.13.2.jar;C:\Users\minic\.m2\repository\org\springframework\boot\spring-boot-starter-tomcat\2.6.7\spring-boot-starter-tomcat-2.6.7.jar;C:\Users\minic\.m2\repository\org\apache\tomcat\embed\tomcat-embed-core\9.0.62\tomcat-embed-core-9.0.62.jar;C:\Users\minic\.m2\repository\org\apache\tomcat\embed\tomcat-embed-el\9.0.62\tomcat-embed-el-9.0.62.jar;C:\Users\minic\.m2\repository\org\apache\tomcat\embed\tomcat-embed-websocket\9.0.62\tomcat-embed-websocket-9.0.62.jar;C:\Users\minic\.m2\repository\org\springframework\spring-web\5.3.19\spring-web-5.3.19.jar;C:\Users\minic\.m2\repository\org\springframework\spring-beans\5.3.19\spring-beans-5.3.19.jar;C:\Users\minic\.m2\repository\org\springframework\spring-webmvc\5.3.19\spring-webmvc-5.3.19.jar;C:\Users\minic\.m2\repository\org\springframework\spring-aop\5.3.19\spring-aop-5.3.19.jar;C:\Users\minic\.m2\repository\org\springframework\spring-context\5.3.19\spring-context-5.3.19.jar;C:\Users\minic\.m2\repository\org\springframework\spring-expression\5.3.19\spring-expression-5.3.19.jar;C:\Users\minic\.m2\repository\com\fasterxml\jackson\module\jackson-module-kotlin\2.13.2\jackson-module-kotlin-2.13.2.jar;C:\Users\minic\.m2\repository\com\fasterxml\jackson\core\jackson-databind\2.13.2.1\jackson-databind-2.13.2.1.jar;C:\Users\minic\.m2\repository\com\fasterxml\jackson\core\jackson-core\2.13.2\jackson-core-2.13.2.jar;C:\Users\minic\.m2\repository\com\fasterxml\jackson\core\jackson-annotations\2.13.2\jackson-annotations-2.13.2.jar;C:\Users\minic\.m2\repository\org\jetbrains\kotlin\kotlin-reflect\1.6.21\kotlin-reflect-1.6.21.jar;C:\Users\minic\.m2\repository\org\jetbrains\kotlin\kotlin-stdlib\1.6.21\kotlin-stdlib-1.6.21.jar;C:\Users\minic\.m2\repository\org\jetbrains\kotlin\kotlin-stdlib-common\1.6.21\kotlin-stdlib-common-1.6.21.jar;C:\Users\minic\.m2\repository\org\jetbrains\annotations\13.0\annotations-13.0.jar;C:\Users\minic\.m2\repository\org\jetbrains\kotlin\kotlin-stdlib-jdk8\1.6.21\kotlin-stdlib-jdk8-1.6.21.jar;C:\Users\minic\.m2\repository\org\jetbrains\kotlin\kotlin-stdlib-jdk7\1.6.21\kotlin-stdlib-jdk7-1.6.21.jar;C:\Users\minic\.m2\repository\org\slf4j\slf4j-api\1.7.36\slf4j-api-1.7.36.jar;C:\Users\minic\.m2\repository\org\springframework\spring-core\5.3.19\spring-core-5.3.19.jar;C:\Users\minic\.m2\repository\org\springframework\spring-jcl\5.3.19\spring-jcl-5.3.19.jar;C:\Users\minic\.m2\repository\com\google\zxing\core\3.4.1\core-3.4.1.jar;C:\Users\minic\.m2\repository\com\google\zxing\javase\3.4.1\javase-3.4.1.jar;C:\Users\minic\.m2\repository\com\beust\jcommander\1.78\jcommander-1.78.jar;C:\Users\minic\.m2\repository\com\github\jai-imageio\jai-imageio-core\1.4.0\jai-imageio-core-1.4.0.jar nt.file2qr.File2qrApplicationKt
OpenJDK 64-Bit Server VM warning: Options -Xverify:none and -noverify were deprecated in JDK 13 and will likely be removed in a future release.

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.6.7)

2022-07-21 20:13:30.721  INFO 8508 --- [           main] nt.file2qr.File2qrApplicationKt          : Starting File2qrApplicationKt using Java 17.0.2 on LAPTOP-IK4AHPN9 with PID 8508 (C:\Work\file2qr\target\classes started by Vladimir in C:\Work\file2qr)
2022-07-21 20:13:30.723  INFO 8508 --- [           main] nt.file2qr.File2qrApplicationKt          : No active profile set, falling back to 1 default profile: "default"
2022-07-21 20:13:31.221  INFO 8508 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2022-07-21 20:13:31.226  INFO 8508 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2022-07-21 20:13:31.226  INFO 8508 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.62]
2022-07-21 20:13:31.285  INFO 8508 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2022-07-21 20:13:31.285  INFO 8508 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 532 ms
2022-07-21 20:13:31.487  INFO 8508 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2022-07-21 20:13:31.493  INFO 8508 --- [           main] nt.file2qr.File2qrApplicationKt          : Started File2qrApplicationKt in 1.018 seconds (JVM running for 1.359)
2022-07-21 20:15:18.458  INFO 8508 --- [nio-8080-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2022-07-21 20:15:18.458  INFO 8508 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2022-07-21 20:15:18.458  INFO 8508 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 0 ms
""".toByteArray()
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
        stats.clear()
        shrinks.clear()
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

    // version 2

    fun ByteArray.buildBuffer2(start: Int, len: Int, size: Int): ByteArray {
        val end = min(start + len, size)
        val data = copyOfRange(start, end)
        return ByteBuffer.allocate(Int.SIZE_BYTES).putInt(start).array()
            .plus(ByteBuffer.allocate(Int.SIZE_BYTES).putInt(end).array())
            .plus(ByteBuffer.allocate(Int.SIZE_BYTES).putInt(size).array())
            .plus(data)
    }

    @GetMapping("/qr2")
    fun getQR2(
        response: HttpServletResponse,
        @RequestParam len: Int,
        @RequestParam off: Int,
        @RequestParam(required = false) size: Int?,
        @RequestParam(required = false) base64: Boolean?,
    ) {
        response.contentType = MediaType.IMAGE_JPEG_VALUE
        prepared
            ?.takeUnless { off > prepared.size }
            ?.buildBuffer2(off, len, prepared.size)
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

    @GetMapping("/seq")
    @ResponseBody
    fun seq(
        @RequestParam(required = false) len: Int?,
        @RequestParam(required = false) off: Int?,
        @RequestParam(required = false) shrink: Int?,
        @RequestParam(required = false) size: Int?,
        @RequestParam(required = false) base64: Boolean?,
        @RequestParam(required = false) stat: Int?,
    ): String {
        base64mode = base64 ?: false
        val l = len ?: defaultLen
        val lCurrent = shrink(l, shrink)
        val s = shrink ?: 0
        val o = off ?: 0
        stat?.let {
            if (it > 0) stats.compute(it) { _, v -> v?.inc() ?: 1 }
            else stats.compute(-it) { _, v -> v?.dec() ?: -1 }
        }
        shrinks.compute(s) { _, v -> v?.inc() ?: 1 }
        val result = "<html><body><img src=\"/qr2?len=$lCurrent&off=$o&size=${size ?: defaultImageSize}&base64=$base64mode\"></img></body></html>"
        return if (o + l >= prepared.size) result else
            """
                <script>
                    document.addEventListener('keydown', (event) => {
                      if (event.code == 'KeyS') {
                        shrink();
                      } else {
                        next();
                      }
                    }, false);
                
                    function next(){
                        window.location.href = '/seq?len=$l&off=${o + lCurrent}&size=${size ?: defaultImageSize}&base64=$base64mode&stat=${lCurrent}';
                    }
                    function shrink(){
                        window.location.href = '/seq?len=$l&shrink=${s.inc()}&off=${o}&size=${size ?: defaultImageSize}&base64=$base64mode&stat=${-lCurrent}';
                    }
                </script>
            """.trimIndent().plus(result)
    }

    private fun shrink(l: Int, shrink: Int?): Int = when {
        shrink == null || shrink < 1 -> l
        l > 1500 -> l - shrink * 100
        else -> (l - shrink * 50).takeUnless { it < 100 } ?: 100
    }
}