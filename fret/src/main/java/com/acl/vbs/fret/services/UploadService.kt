package com.acl.vbs.fret.services

import org.springframework.web.multipart.MultipartFile

interface UploadService {
    fun saveLocalFile(multipartFile: MultipartFile, fileName: String, originFilePath: String): String
    fun deleteLocalFile(url: String)
}