package gsbkomar.domain.usecases.db_usecases

import gsbkomar.domain.repository.PhotoDataBaseRepository
import javax.inject.Inject

class DeletePhotoUseCase @Inject constructor(private val photoDataBaseRepository: PhotoDataBaseRepository) {
    suspend fun execute(url: String) = photoDataBaseRepository.deletePhoto(url)
}