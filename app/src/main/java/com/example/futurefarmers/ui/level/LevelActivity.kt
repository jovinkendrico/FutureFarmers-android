package com.example.futurefarmers.ui.level

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.futurefarmers.R
import com.example.futurefarmers.databinding.ActivityLevelBinding
import com.example.futurefarmers.ui.ViewModelFactory
import com.example.futurefarmers.ui.config.ConfigViewModel
import com.example.futurefarmers.ui.login.LoginActivity

class LevelActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLevelBinding
    private var token: String? = null
    private val levelViewModel by viewModels<LevelViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLevelBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        levelViewModel.getSession().observe(this){
            token = "Bearer $it"
            if(it == ""){
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }
}