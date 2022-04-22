package nt.file2qr

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MainController {
    @GetMapping("/test")
    fun test() = "Hello, TEST!"
}