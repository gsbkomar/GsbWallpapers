package gsbkomar.data.dto.tree

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import gsbkomar.domain.models.tree.Tags

@JsonClass(generateAdapter = true)
data class TagsDto(
    @Json(name = "source")
    override val source: SourceDto?
) : Tags