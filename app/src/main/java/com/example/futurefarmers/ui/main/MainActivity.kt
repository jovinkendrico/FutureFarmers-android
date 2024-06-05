package com.example.futurefarmers.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.futurefarmers.R
import com.example.futurefarmers.data.response.DataResponse
import com.example.futurefarmers.databinding.ActivityMainBinding
import com.example.futurefarmers.ui.ViewModelFactory
import com.example.futurefarmers.ui.config.ConfigActivity
import com.example.futurefarmers.ui.control.ControlActivity
import com.example.futurefarmers.ui.login.LoginActivity
import com.example.futurefarmers.ui.plant.AddPlantActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private var token: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        mainViewModel.getSession().observe(this){
            token = "Bearer $it"
            if(it == ""){
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
            }else{
                token?.let {
                    mainViewModel.tanaman(it)
                    mainViewModel.dashboard(it)
                    mainViewModel.getDataDashboard().observe(this) {
                        updateUI(it)
                    }
                    mainViewModel.getDataPlant().observe(this){
                        binding.tvSayur.text = it.nama.toString()
                        binding.tvPanen.text = it.umur.toString()
                        binding.tvUmur.text = it.tanggal.toString()
                    }
                }
            }
        }
        binding.cvSedangMenanam.setOnClickListener{
            startActivity(Intent(this,AddPlantActivity::class.java))
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
                    startActivity(Intent(this,ConfigActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }
    private fun updateUI(data: DataResponse) {
        // Update UI with the latest data
        binding.tvAngkaSuhu.text = data.suhu.toString()
        binding.tvAngkaKelembapan.text = data.kelembapan.toString()
        binding.tvAngkaTingkatKeasaman.text = data.ph.toString()
        binding.tvAngkaKesehatan.text = data.tds.toString()
    }
}