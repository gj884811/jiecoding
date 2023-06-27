package api

import com.example.frogapplication.data.MovieListResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.Executors


interface FrogAPIService {

    @GET("/")
  suspend fun getMovieListData(
        @Query("apikey") apiKey: String,
        @Query("s") searchQuery: String,
        @Query("y") year: String = ">2000"
        ): Response<MovieListResponse>
    companion object{
        private var instance: FrogAPIService? = null
        operator fun invoke(): FrogAPIService {
            if (instance == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://www.omdbapi.com/")
                    .client(OkHttpClient.Builder().build())
                    .callbackExecutor(Executors.newSingleThreadExecutor())
                    .addConverterFactory(
                        MoshiConverterFactory.create(
                            Moshi.Builder()
                                .add(KotlinJsonAdapterFactory())
                                .build()
                        )
                    )
                    .build()
                instance = retrofit.create(FrogAPIService::class.java)
            }
            return instance!!
        }
    }
}