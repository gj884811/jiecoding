package com.example.frogapplication.data.repository

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import repository.UserRepository

/**
 *
intermediate layer
 */

class AuthViewModel(
  private val repository: UserRepository,
) :  ViewModel() {
  var email: String? = null
  var password: String? = null
  lateinit var job: Job



  public override fun onCleared() {
    super.onCleared()
    if(::job.isInitialized) job.cancel()
  }
  suspend fun registerUser(name: String, email: String, password: String, confirmpasd: String)= withContext(Dispatchers.IO)
  {
      repository.registerUser(name, email, password, confirmpasd)
  }

  suspend fun userLogin(email: String, password: String): Boolean {
    if (email.isNullOrEmpty() || password.isNullOrEmpty()){
      return false
    }
    return repository.isUserExistsByEmail(email)
  }


}