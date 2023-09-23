package gsbkomar.gsbwallpapers.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import gsbkomar.gsbwallpapers.screens.favorite.FavoriteViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class FavoriteViewModelFactory @Inject constructor(private val favoriteViewModel: FavoriteViewModel) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)
        ) favoriteViewModel as T else throw java.lang.IllegalArgumentException("Unknown class name")
}