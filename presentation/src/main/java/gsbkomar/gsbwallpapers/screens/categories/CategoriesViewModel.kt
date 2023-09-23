package gsbkomar.gsbwallpapers.screens.categories

import androidx.lifecycle.ViewModel
import gsbkomar.gsbwallpapers.State
import gsbkomar.gsbwallpapers.screens.categories.models.CategoryCard
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class CategoriesViewModel @Inject constructor() : ViewModel() {

    private val categoryCardList = mutableListOf<CategoryCard>()

    private var _state = MutableStateFlow<State>(State.Loading)
    var state = _state.asStateFlow()


    fun getCategoryList() : MutableList<CategoryCard> {
        _state.value = State.Loading
        with(categoryCardList) {
        add(
                CategoryCard(
                    "https://i.pinimg.com/originals/a5/8c/82/a58c829623a48b2e1f2f4a37888d3706.jpg",
                    "Nature"
                )
            )
            add(
                CategoryCard(
                    "https://oboi.ringtonz.ru/uploads/posts/2020-04/1585984024_arkhitektura_zdanie_fasad_dizajn_116773_1440x2560.jpg",
                    "Architecture"
                )
            )
            add(
                CategoryCard(
                    "https://w.forfun.com/fetch/e6/e68cdae3cecaf4732657b139a1a25332.jpeg",
                    "City"
                )
            )
            add(
                CategoryCard(
                    "https://img.goodfon.ru/original/640x1136/0/ef/macaron-almond-cookies-4672.jpg",
                    "Food"
                )
            )
            add(
                CategoryCard(
                    "https://image.winudf.com/v2/image1/Y29tLnRvcGlseWEuYW5pbWFsc3dhbGxwYXBlcnMuaGRfc2NyZWVuXzBfMTYxNjkxNzAxNV8wODQ/screen-0.jpg?fakeurl=1&type=.jpg",
                    "Animals"
                )
            )
            add(
                CategoryCard(
                    "https://oboi.ringtonz.ru/uploads/posts/2020-10/1602430955_para_siluety_lyubov_obyatiya_118554_1440x2560.jpg",
                    "People"
                )
            )
            add(
                CategoryCard(
                    "https://oboi-telefon.ru/wallpapers/77882/33367.jpg",
                    "Travel"
                )
            )
            add(
                CategoryCard(
                    "https://w.forfun.com/fetch/c4/c4aebe00a9572ba559babe83255a97a3.jpeg",
                    "Technology"
                )
            )
            add(
                CategoryCard(
                    "https://w.forfun.com/fetch/41/41a28cd0662554ed041845c646204883.jpeg?h=1200&r=0.5",
                    "Interior"
                )
            )
        }
        _state.value = State.Success
        return categoryCardList
    }
}