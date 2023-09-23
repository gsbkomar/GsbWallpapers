package gsbkomar.gsbwallpapers.screens.fullscreen_photo

import androidx.lifecycle.ViewModel
import gsbkomar.data.dto.db.PhotoDbDto
import gsbkomar.data.implementation.repository.PhotoDataBaseRepositoryImpl
import gsbkomar.data.mappers.toEntity
import gsbkomar.domain.usecases.db_usecases.DeletePhotoUseCase
import gsbkomar.domain.usecases.db_usecases.GetAllPhotoUseCase
import gsbkomar.domain.usecases.db_usecases.UpsertPhotoUseCase
import gsbkomar.gsbwallpapers.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class FullscreenPhotoViewModel @Inject constructor(
    repositoryImpl: PhotoDataBaseRepositoryImpl
): ViewModel() {

    private val upsertPhotoUseCase = UpsertPhotoUseCase(repositoryImpl)
    private val deletePhotoUseCase = DeletePhotoUseCase(repositoryImpl)
    private val getAllPhotoUseCase = GetAllPhotoUseCase(repositoryImpl)

    private var _state = MutableStateFlow<State>(State.Loading)
    var state = _state.asStateFlow()

    suspend fun upsertFavoritePhoto(photo: PhotoDbDto) {
        _state.value = State.Loading
        upsertPhotoUseCase.execute(photo)
        _state.value = State.Success
    }

    suspend fun deleteFavoritePhoto(url: String) {
        _state.value = State.Loading
        deletePhotoUseCase.execute(url)
        _state.value = State.Success
    }

    suspend fun getAllFavoritePhoto() : List<PhotoDbDto> {
        _state.value = State.Loading
        val list = getAllPhotoUseCase.execute().map { it.toEntity() }
        _state.value = State.Success
        return list
    }
}