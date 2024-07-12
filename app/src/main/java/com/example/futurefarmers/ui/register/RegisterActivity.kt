package com.example.futurefarmers.ui.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.futurefarmers.R
import com.example.futurefarmers.databinding.ActivityLoginBinding
import com.example.futurefarmers.databinding.ActivityRegisterBinding
import com.example.futurefarmers.ui.AuthModelFactory
import com.example.futurefarmers.ui.login.LoginActivity
import com.example.futurefarmers.ui.login.LoginViewModel
import com.example.futurefarmers.ui.main.MainActivity
import com.google.gson.JsonObject

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val registerViewModel by viewModels<RegisterViewModel> {
        AuthModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnRegister.setOnClickListener{
            var name = binding.etName.text.toString()
            var username = binding.etUsername.text.toString()
            var password = binding.etPassword.text.toString()
            val param = JsonObject().apply {
                addProperty("name", name)
                addProperty("username", username)
                addProperty("password", password)
            }
            registerViewModel.register(param)
            registerViewModel.getRegisterResponse().observe(this){
                if (!it.error.toBoolean()){
                    startActivity(Intent(this, LoginActivity::class.java))
                    showToast("Register Berhasil")
                }
            }
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}