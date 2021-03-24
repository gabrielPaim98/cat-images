package tk.gabrielpaim.catimages.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

const val CLIENT_ID = "Client-ID 1ceddedc03a5d71"
const val BASE_URL = "https://api.imgur.com/3/gallery/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


/**
 * A retrofit service to fetch a imgur images.
 */
interface ImgurApiService {
    @GET("search/")
    suspend fun searchFor(
        @Query("q") query: String = "cats",
        @Header("Authorization") token: String = CLIENT_ID
    ): NetworkApiResponse
}

/**
 * Main entry point for network access. Call like `ImgurApi.service.searchFor()`
 */
object ImgurApi {
    val service: ImgurApiService by lazy {
        retrofit.create(ImgurApiService::class.java)
    }
}