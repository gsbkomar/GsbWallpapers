package gsbkomar.gsbwallpapers.screens.photo_list_category

import androidx.lifecycle.ViewModel
import gsbkomar.data.implementation.repository.UnsplashPhotoRepositoryImpl
import gsbkomar.domain.models.Category
import gsbkomar.domain.usecases.GetPhotoListByCategoryUseCase
import gsbkomar.gsbwallpapers.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class PhotoListViewModel @Inject constructor(repositoryImpl: UnsplashPhotoRepositoryImpl): ViewModel() {

    private val getPhotoListByCategoryUseCase = GetPhotoListByCategoryUseCase(repositoryImpl)

    private var _state = MutableStateFlow<State>(State.Loading)
    var state = _state.asStateFlow()

    suspend fun getPhotoListByCategory(category: String) : Category {
        _state.value = State.Loading
       val photo = getPhotoListByCategoryUseCase.execute(category)
        _state.value = State.Success
        return photo
    }
}
