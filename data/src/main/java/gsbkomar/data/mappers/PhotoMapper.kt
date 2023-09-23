package gsbkomar.data.mappers

import gsbkomar.data.dto.db.PhotoDbDto
import gsbkomar.domain.models.db.Photo

fun Photo.toEntity() = PhotoDbDto(
   url = url
)