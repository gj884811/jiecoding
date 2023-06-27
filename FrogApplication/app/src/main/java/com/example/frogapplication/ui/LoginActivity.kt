package com.example.frogapplication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.frogapplication.R
import com.example.frogapplication.data.db.AppDatabase
import com.example.frogapplication.data.repository.AuthViewModel
import com.example.frogapplication.databinding.ActivityLoginBinding
import com.example.frogapplication.until.NoInternetException
import com.example.frogapplication.until.SessionManager
import com.example.frogapplication.until.toast
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.launch
import repository.UserRepository
import repository.ViewModelFactory

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private lateinit var viewModel: AuthViewModel
    private lateinit var sessionManager: SessionManager

    private lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = DataBindingUtil.setContentView(this, R.layout.activity_login)
        sessionManager = SessionManager(this)

        // Check if the user is already logged in
        if (sessionManager.isLoggedIn) {
            navigateToMoveActivity()
            return
        }
        // In the LoginActivity, create the AppDatabase instance
        val db = AppDatabase(this)
        val repository = UserRepository(db)
        factory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)
        binding.buttonSignIn.setOnClickListener{
            LoginUser()
        }
        binding.textViewSignUp.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
            finish()
        }
        binding.buttonBiometric.setOnClickListener {
            startActivity(Intent(this, BiometricActivity::class.java))
            finish()
        }
//        viewModel.getLoggedInUser()?.observe(this, Observer { user ->
//            if(user !=null){
//                Intent(this, MoveActivity::class.java).also {
//                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                    startActivity(it)
//                }
//            }
//        })
    }

    private fun navigateToMoveActivity() {
        val intent = Intent(this@LoginActivity, MoveActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }


    private fun LoginUser() {
        val email = binding.editTextEmail.text.toString().trim()
        val password = binding.editTextPassword.text.toString().trim()
        if (email.isEmpty() || password.isEmpty()) {
            toast("Please enter email and password")
            return
        }
        lifecycleScope.launch {
            try {
                var loginResponse = viewModel.userLogin(email, password)
                if (loginResponse) {
                    // Save the logged-in user email and update session
                    sessionManager.email = email
                    sessionManager.isLoggedIn = true
                    navigateToMoveActivity()
                } else {
                    // User is not registered, handle accordingly
                    val message = "Please register first!"
                    toast(message)
                }


            } catch (e: ApiException) {
                e.printStackTrace()
            } catch (e: NoInternetException) {
                e.printStackTrace()
            }
        }
    }

}