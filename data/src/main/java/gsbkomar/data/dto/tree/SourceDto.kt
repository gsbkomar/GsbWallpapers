package gsbkomar.data.dto.tree

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import gsbkomar.domain.models.tree.Source

@JsonClass(generateAdapter = true)
data class SourceDto(
    @Json(name = "cover_photo")
    override val coverPhoto: CoverPhotoDto
) : Source