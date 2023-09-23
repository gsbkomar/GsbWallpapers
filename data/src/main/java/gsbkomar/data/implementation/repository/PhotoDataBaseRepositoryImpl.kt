package gsbkomar.data.implementation.repository

import android.app.Application
import gsbkomar.data.db.PhotoDataBase
import gsbkomar.data.mappers.toEntity
import gsbkomar.domain.models.db.Photo
import gsbkomar.domain.repository.PhotoDataBaseRepository
import javax.inject.Inject

class PhotoDataBaseRepositoryImpl @Inject constructor(app: Application) : PhotoDataBaseRepository {

    private val db = PhotoDataBase.getInstance(app)

    override suspend fun upsertPhoto(photo: Photo) {
        db.photoDao.upsert(photo.toEntity())
    }

    override suspend fun deletePhoto(url: String) {
       db.photoDao.delete(url)
    }

    override suspend fun getAllPhoto(): List<Photo> {
        return db.photoDao.getAll()
    }
}