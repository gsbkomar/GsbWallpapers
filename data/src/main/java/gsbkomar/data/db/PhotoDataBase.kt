package gsbkomar.data.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import gsbkomar.data.db.dao.PhotoDao
import gsbkomar.data.dto.db.PhotoDbDto

@Database(
    entities = [PhotoDbDto::class],
    version = 1,
    exportSchema = false
)

abstract class PhotoDataBase : RoomDatabase() {

    abstract val photoDao: PhotoDao

    companion object {
        private var INSTANCE: PhotoDataBase? = null
        private const val DATABASE_NAME = "cocktails.db"

        fun getInstance(application: Application): PhotoDataBase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    application,
                    PhotoDataBase::class.java,
                    DATABASE_NAME
                ).build().also { INSTANCE = it }
            }
    }
}