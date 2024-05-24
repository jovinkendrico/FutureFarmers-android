package com.example.futurefarmers.ui.main

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

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }
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

        mainViewModel.dataDashboard.observe(this){it->
            updateUI(it)
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