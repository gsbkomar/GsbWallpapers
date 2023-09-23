package gsbkomar.data.dto.tree

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import gsbkomar.domain.models.tree.Urls

@JsonClass(generateAdapter = true)
data class UrlsDto(
    @Json(name = "raw")
    override val raw: String
) : Urls