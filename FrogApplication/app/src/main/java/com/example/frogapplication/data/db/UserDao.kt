package com.example.frogapplication.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(user: User) : Long

    @Query("SELECT * FROM user WHERE id = $CURRENT_USER_ID")
    fun getuser(): LiveData<User>

    @Query("SELECT EXISTS(SELECT 1 FROM user WHERE email = :email LIMIT 1)")
    suspend fun checkUserExistsByEmail(email: String): Boolean

}