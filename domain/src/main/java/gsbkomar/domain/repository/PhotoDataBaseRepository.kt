package gsbkomar.domain.repository

import gsbkomar.domain.models.db.Photo

interface PhotoDataBaseRepository {

    suspend fun upsertPhoto(photo: Photo)

    suspend fun deletePhoto(url: String)

    suspend fun getAllPhoto() : List<Photo>
}