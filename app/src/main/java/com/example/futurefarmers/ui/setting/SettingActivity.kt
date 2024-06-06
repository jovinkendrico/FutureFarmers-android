package com.example.futurefarmers.ui.setting

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.futurefarmers.R
import com.example.futurefarmers.databinding.ActivitySettingBinding
import com.example.futurefarmers.ui.ViewModelFactory
import com.example.futurefarmers.ui.config.ConfigActivity
import com.example.futurefarmers.ui.control.ControlActivity
import com.example.futurefarmers.ui.level.LevelActivity
import com.example.futurefarmers.ui.login.LoginActivity
import com.example.futurefarmers.ui.main.MainActivity
import com.example.futurefarmers.ui.plant.PlantViewModel

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding
    private var token: String? = null
    private val settingViewModel by viewModels<SettingViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySettingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        settingViewModel.getSession().observe(this){
            token = "Bearer $it"
            if(it == ""){
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
            }
        }
        binding.btnLogout.setOnClickListener{
            settingViewModel.logout()
            showToast("Logout Berhasil")
            startActivity(Intent(this,LoginActivity::class.java))
        }
        binding.imageView3.setOnClickListener {
            navToConfigTimeout()
        }
        binding.tvRelayConfig.setOnClickListener{
            navToConfigTimeout()
        }
        binding.deskripsiRelayConfig.setOnClickListener{
            navToConfigTimeout()
        }
        binding.imageView4.setOnClickListener {
            navToLevelConfig()
        }
        binding.deskripsiLevelConfig.setOnClickListener {
            navToLevelConfig()
        }
        binding.tvLevelConfig.setOnClickListener {
            navToLevelConfig()
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
                    startActivity(Intent(this,SettingActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    private fun navToConfigTimeout()
    {
        startActivity(Intent(this,ConfigActivity::class.java))
    }

    private fun navToLevelConfig(){
        startActivity(Intent(this,LevelActivity::class.java))
    }
}