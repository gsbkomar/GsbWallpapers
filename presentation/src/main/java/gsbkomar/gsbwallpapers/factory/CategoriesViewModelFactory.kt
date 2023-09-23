package gsbkomar.gsbwallpapers.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import gsbkomar.gsbwallpapers.screens.categories.CategoriesViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class CategoriesViewModelFactory @Inject constructor(private val categoriesViewModel: CategoriesViewModel) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        if (modelClass.isAssignableFrom(CategoriesViewModel::class.java)
        ) categoriesViewModel as T else throw java.lang.IllegalArgumentException("Unknown class name")
}