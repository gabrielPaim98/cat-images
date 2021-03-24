package tk.gabrielpaim.catimages.service

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import tk.gabrielpaim.catimages.model.ApiResponse

const val CLIENT_ID = "Client-ID 1ceddedc03a5d71"
const val BASE_URL = "https://api.imgur.com/3/gallery/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ImgurApiService {
    @GET("search/")
    suspend fun searchFor(
        @Query("q") query: String = "cats",
        @Header("Authorization") token: String = CLIENT_ID
    ): ApiResponse
}

object ImgurApi {
    val service: ImgurApiService by lazy {
        retrofit.create(ImgurApiService::class.java)
    }
}