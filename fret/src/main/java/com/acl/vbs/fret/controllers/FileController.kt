package com.acl.vbs.fret.controllers

import org.springframework.http.MediaType.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*


@RestController
@RequestMapping(produces = [IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE, IMAGE_GIF_VALUE, APPLICATION_PDF_VALUE])
class FileController {
//    private val logs: Logger = LoggerFactory.getLogger(FileController::class.java)

    @GetMapping("fret/files")
    fun readFile(@RequestParam file: String): ByteArray {
        // Décoder le nom de fichier à partir de la partie encodée de l'URL
        val decodedFileName = String(Base64.getUrlDecoder().decode(file))
        val filePath = Paths.get(decodedFileName)
        return Files.readAllBytes(filePath)
    }
}