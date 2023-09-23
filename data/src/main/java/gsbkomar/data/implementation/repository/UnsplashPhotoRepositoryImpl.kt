package gsbkomar.data.implementation.repository

import gsbkomar.data.dto.CategoriesDto
import gsbkomar.data.implementation.storage.GetPhotoListStorageImpl
import gsbkomar.domain.repository.UnsplashPhotoRepository
import javax.inject.Inject

class UnsplashPhotoRepositoryImpl @Inject constructor() : UnsplashPhotoRepository {
    override suspend fun getPhotoListByCategory(category: String): CategoriesDto =
        GetPhotoListStorageImpl().get(category)
}