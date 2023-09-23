package gsbkomar.data

import gsbkomar.data.dto.CategoriesDto
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import javax.inject.Inject

class RetrofitGetUnsplashInstance @Inject constructor() {

    object RetrofitInstance {
        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val getPhotoList: GetPhotoListProvider = retrofit.create(GetPhotoListProvider::class.java)
    }

    interface GetPhotoListProvider {
        @Headers(
            "Accept: application/json",
            "Content-type: application/json",
        )
        @GET("search/collections")
        suspend fun getPhotoListProvider(
            @Query("query") category: String,
            @Query("client_id") key: String = API_KEY
        ): CategoriesDto
    }

    companion object {
        const val BASE_URL = "https://api.unsplash.com/"
        const val API_KEY = "ftXjMCEkjlK7eVdAgh5Nivb87agZwAKtzRC3Q5yuzcY"
    }
}