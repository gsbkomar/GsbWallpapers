package gsbkomar.domain.storage

import gsbkomar.domain.models.Category

interface GetPhotoListStorage {
    suspend fun get(category: String) : Category
}