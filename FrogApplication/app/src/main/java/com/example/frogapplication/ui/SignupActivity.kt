package com.example.frogapplication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import api.FrogAPIService
import com.example.frogapplication.R
import com.example.frogapplication.data.db.AppDatabase
import com.example.frogapplication.data.repository.AuthViewModel
import com.example.frogapplication.databinding.ActivitySignupBinding
import com.example.frogapplication.until.NoInternetException
import com.example.frogapplication.until.toast
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.launch
import repository.UserRepository
import repository.ViewModelFactory

class SignupActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySignupBinding
    private lateinit var viewModel: AuthViewModel

    private lateinit var factory: ViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        // In the LoginActivity, create the AppDatabase instance
        val db = AppDatabase(this)
        val repository = UserRepository(db)
        factory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)
        binding.buttonSignUp.setOnClickListener{
            SignUpUser()
        }
    }

    private fun SignUpUser() {
        val name = binding.editTextName.text.toString().trim()
        val email = binding.editTextEmail.text.toString().trim()
        val password = binding.editTextPassword.text.toString().trim()
        val confirmpasd = binding.editTextPassword.text.toString().trim()
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()|| confirmpasd.isEmpty()) {
            toast("Information cannot be empty!!!")
            return
        }
        lifecycleScope.launch {
            try {
                val saveUser = viewModel.registerUser(name, email, password, confirmpasd)
                if(saveUser){
                    val intent = Intent(this@SignupActivity, MoveActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    }else{
                        toast("registration exception!!!!!")
                    }

            } catch (e: ApiException) {
                e.printStackTrace()
            } catch (e: NoInternetException) {
                e.printStackTrace()
            }
        }

    }
}