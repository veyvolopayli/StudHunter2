package com.veyvolopayli.studhunter.domain.usecases.create_publication

import com.veyvolopayli.studhunter.domain.model.PublicationToUpload
import com.veyvolopayli.studhunter.domain.repository.PublicationRepository
import javax.inject.Inject

class UploadPublicationUseCase @Inject constructor(
    private val publicationRepository: PublicationRepository
) {
    operator fun invoke(images: List<String>, publicationToUpload: PublicationToUpload): String //todo
}