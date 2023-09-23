package gsbkomar.domain.usecases.db_usecases

import gsbkomar.domain.models.db.Photo
import gsbkomar.domain.repository.PhotoDataBaseRepository
import javax.inject.Inject

class GetAllPhotoUseCase @Inject constructor(private val photoDataBaseRepository: PhotoDataBaseRepository) {
    suspend fun execute() : List<Photo> = photoDataBaseRepository.getAllPhoto()
}