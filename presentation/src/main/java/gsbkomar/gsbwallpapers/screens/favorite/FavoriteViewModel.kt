package gsbkomar.gsbwallpapers.screens.favorite

import androidx.lifecycle.ViewModel
import gsbkomar.data.dto.db.PhotoDbDto
import gsbkomar.data.implementation.repository.PhotoDataBaseRepositoryImpl
import gsbkomar.data.mappers.toEntity
import gsbkomar.domain.usecases.db_usecases.GetAllPhotoUseCase
import gsbkomar.gsbwallpapers.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(repositoryImpl: PhotoDataBaseRepositoryImpl) : ViewModel() {

    private val getAllPhotoUseCase = GetAllPhotoUseCase(repositoryImpl)

    private var _state = MutableStateFlow<State>(State.Loading)
    var state = _state.asStateFlow()

    suspend fun getAllFavorite() : List<PhotoDbDto> {
        _state.value = State.Loading
        val list = getAllPhotoUseCase.execute().map { it.toEntity() }
        _state.value = State.Success
        return list
    }
}