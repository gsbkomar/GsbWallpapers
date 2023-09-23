package gsbkomar.data.dto.tree

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import gsbkomar.domain.models.tree.CoverPhoto

@JsonClass(generateAdapter = true)
data class CoverPhotoDto(
    @Json(name = "urls")
    override val urls: UrlsDto
) : CoverPhoto