package gsbkomar.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import gsbkomar.data.dto.db.PhotoDbDto

@Dao
interface PhotoDao {

    @Upsert
    suspend fun upsert(photo: PhotoDbDto): Long

    @Query("DELETE FROM PhotoDbDto WHERE url=:url")
    suspend fun delete(url: String)

    @Query("SELECT * FROM PhotoDbDto")
    suspend fun getAll(): List<PhotoDbDto>
}