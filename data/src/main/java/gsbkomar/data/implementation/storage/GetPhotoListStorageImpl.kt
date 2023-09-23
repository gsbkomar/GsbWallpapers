package gsbkomar.data.implementation.storage

import gsbkomar.data.RetrofitGetUnsplashInstance
import gsbkomar.data.dto.CategoriesDto
import gsbkomar.domain.storage.GetPhotoListStorage
import javax.inject.Inject

class GetPhotoListStorageImpl @Inject constructor() : GetPhotoListStorage {
    override suspend fun get(category: String): CategoriesDto =
        RetrofitGetUnsplashInstance.RetrofitInstance.getPhotoList.getPhotoListProvider(category)
}