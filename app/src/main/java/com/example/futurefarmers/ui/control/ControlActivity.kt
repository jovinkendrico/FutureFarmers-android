package com.example.futurefarmers.ui.control

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.futurefarmers.R
import com.example.futurefarmers.databinding.ActivityConfigBinding
import com.example.futurefarmers.databinding.ActivityControlBinding
import com.example.futurefarmers.ui.ViewModelFactory
import com.example.futurefarmers.ui.config.ConfigViewModel
import com.example.futurefarmers.ui.login.LoginActivity
import com.example.futurefarmers.ui.main.MainActivity
import com.example.futurefarmers.ui.main.MainViewModel
import com.example.futurefarmers.ui.setting.SettingActivity

class ControlActivity : AppCompatActivity() {
    private lateinit var binding: ActivityControlBinding
    private var token: String? = null
    private val controlViewModel by viewModels<ControlViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityControlBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        controlViewModel.getSession().observe(this){
            token = "Bearer $it"
            if(it == ""){
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }

        binding.bottomNavigationView.setOnNavigationItemSelectedListener() { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home-> {
                    startActivity(Intent(this, MainActivity::class.java))
                    true
                }
                R.id.navigation_control -> {
                    startActivity(Intent(this, ControlActivity::class.java))
                    true
                }
                R.id.navigation_setting->{
                    startActivity(Intent(this, SettingActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }
}