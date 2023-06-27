package repository

import api.safeApiRequest
import com.example.frogapplication.data.db.AppDatabase
import com.example.frogapplication.data.db.User
import com.example.frogapplication.data.db.UserDao


open class UserRepository(
    private val db: AppDatabase? = null
) : safeApiRequest(){

    val userDao: UserDao? = db?.getUserDao()
    suspend fun isUserExistsByEmail(email: String): Boolean {
        return userDao?.checkUserExistsByEmail(email) ?: false
    }

   suspend fun registerUser(name: String, email: String, password: String, confirmpasd: String) : Boolean {
       val user = User(
           name = name,
           email= email,
           password = password
       )
       val userId = userDao?.upsert(user)
       return userId != null // Returning true if the userId is not null
    }


}