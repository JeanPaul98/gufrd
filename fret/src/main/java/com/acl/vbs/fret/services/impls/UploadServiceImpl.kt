package com.acl.vbs.fret.services.impls

import com.acl.vbs.fret.services.UploadService
import com.acl.vbs.fret.utils.AppUtils.USER_FOLDER
import com.acl.vbs.fret.utils.AppUtils.getFileExtension
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import org.springframework.web.util.UriComponentsBuilder
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption.REPLACE_EXISTING
import java.util.*


@Service
class UploadServiceImpl : UploadService {

    private val logs: Logger = LoggerFactory.getLogger(UploadServiceImpl::class.java)

    override fun saveLocalFile(multipartFile: MultipartFile, fileName: String, originFilePath: String): String {
        val filename = "$fileName.${getFileExtension(Objects.requireNonNull(multipartFile.originalFilename)!!)}"
        return getString(multipartFile, "$USER_FOLDER$originFilePath", filename)
    }

    override fun deleteLocalFile(url: String) {
        try {
            // Création de l'URI à partir du chemin
            val uriComponents = UriComponentsBuilder.fromUriString(url).build()

            // Récupération de la query
            val fileParam = uriComponents.queryParams.getFirst("file")

            fileParam?.let {
                val decodedFileName = String(Base64.getUrlDecoder().decode(fileParam))

                // Log du nom de fichier décodé
                logs.info("Delete file: {}", decodedFileName)

                // Obtenir le Path à partir du nom de fichier décodé
                val userPath: Path = Paths.get(decodedFileName)

                Files.deleteIfExists(userPath)

            }

        } catch (e: Exception) {
            // Gestion des exceptions avec log des erreurs
            logs.error("Erreur lors de la suppression du fichier : {}", e.message)
        }
    }

    private fun getString(file: MultipartFile, filePath: String, fileName: String): String {
        val userPath: Path = Paths.get(filePath).toAbsolutePath().normalize()

        try {
            // Crée le répertoire s'il n'existe pas
            if (!Files.exists(userPath)) {
                Files.createDirectories(userPath)
                logs.info("Le chemin a été créé : $userPath")
            }

            val targetPath = userPath.resolve(fileName)
            Files.deleteIfExists(targetPath)
            Files.copy(file.inputStream, targetPath, REPLACE_EXISTING)
            logs.info("Fichier copié : ${file.name}")

            // Retourne l'URL du fichier
            return getImageUrl(targetPath)
        } catch (e: Exception) {
            logs.error("Erreur lors de la gestion du fichier : ${e.message}")
            throw e
        }
    }

    private fun getImageUrl(targetPath: Path): String {
        val fileName = Base64.getUrlEncoder().encodeToString(targetPath.toString().toByteArray(StandardCharsets.UTF_8))
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("fret/files").queryParam("file", fileName)
            .toUriString()
    }
}
