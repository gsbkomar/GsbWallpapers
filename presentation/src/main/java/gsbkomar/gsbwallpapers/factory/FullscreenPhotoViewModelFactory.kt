package gsbkomar.gsbwallpapers.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import gsbkomar.gsbwallpapers.screens.fullscreen_photo.FullscreenPhotoViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class FullscreenPhotoViewModelFactory @Inject constructor(private val fullscreenPhotoViewModel: FullscreenPhotoViewModel) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        if (modelClass.isAssignableFrom(FullscreenPhotoViewModel::class.java)
        ) fullscreenPhotoViewModel as T else throw java.lang.IllegalArgumentException("Unknown class name")
}