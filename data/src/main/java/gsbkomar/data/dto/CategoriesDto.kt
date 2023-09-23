package gsbkomar.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import gsbkomar.data.dto.tree.ResultsDto
import gsbkomar.domain.models.Category

@JsonClass(generateAdapter = true)
data class CategoriesDto(
    @Json(name = "results")
    override val results: List<ResultsDto>
) : Category