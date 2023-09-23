package gsbkomar.data.dto.tree

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import gsbkomar.domain.models.tree.Results

@JsonClass(generateAdapter = true)
data class ResultsDto(
    @Json(name = "tags")
    override val tags: List<TagsDto>
) : Results