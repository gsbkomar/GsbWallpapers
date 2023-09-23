package gsbkomar.data.dto.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import gsbkomar.domain.models.db.Photo

@Entity
data class PhotoDbDto(
    override val url: String,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
) : Photo