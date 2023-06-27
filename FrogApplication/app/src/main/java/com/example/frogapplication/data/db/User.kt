package com.example.frogapplication.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

const val CURRENT_USER_ID = 0

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0, // Generate unique ids automatically
    var name: String? = null,
    var email: String? = null,
    var password: String? = null,
)