package gsbkomar.domain.repository

import gsbkomar.domain.models.Category

interface UnsplashPhotoRepository {
    suspend fun getPhotoListByCategory(category: String) : Category
}