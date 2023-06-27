package repository

import api.FrogAPIService
import api.safeApiRequest
import com.example.frogapplication.data.db.AppDatabase
import com.example.frogapplication.data.db.User



open class MovieRepository(
    private val api: FrogAPIService
) : safeApiRequest(){
    open suspend fun getMovieList(apiKey: String,title: String?, year: String) = apiRequest {
        api?.getMovieListData(apiKey ?:"",title ?: "", ">2000")!!
    }


}