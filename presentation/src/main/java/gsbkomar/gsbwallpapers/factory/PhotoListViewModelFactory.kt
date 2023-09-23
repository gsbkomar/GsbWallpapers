package gsbkomar.gsbwallpapers.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import gsbkomar.gsbwallpapers.screens.photo_list_category.PhotoListViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class PhotoListViewModelFactory @Inject constructor(private val photoListViewModel: PhotoListViewModel) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        if (modelClass.isAssignableFrom(PhotoListViewModel::class.java)
        ) photoListViewModel as T else throw java.lang.IllegalArgumentException("Unknown class name")
}