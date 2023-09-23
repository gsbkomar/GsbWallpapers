package gsbkomar.domain.usecases

import gsbkomar.domain.repository.UnsplashPhotoRepository
import javax.inject.Inject

class GetPhotoListByCategoryUseCase @Inject constructor(private val unsplashPhotoRepository: UnsplashPhotoRepository) {
    suspend fun execute(category: String) =
        unsplashPhotoRepository.getPhotoListByCategory(category)
}