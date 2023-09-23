package gsbkomar.domain.usecases.db_usecases

import gsbkomar.domain.models.db.Photo
import gsbkomar.domain.repository.PhotoDataBaseRepository
import javax.inject.Inject

class UpsertPhotoUseCase @Inject constructor(private val photoDataBaseRepository: PhotoDataBaseRepository) {
    suspend fun execute(photo: Photo) = photoDataBaseRepository.upsertPhoto(photo)
}