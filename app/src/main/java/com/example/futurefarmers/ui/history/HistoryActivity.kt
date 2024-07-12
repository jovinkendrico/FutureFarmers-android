package com.example.futurefarmers.ui.history

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.futurefarmers.R
import com.example.futurefarmers.databinding.ActivityHistoryBinding
import com.example.futurefarmers.ui.PaginateModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    private val viewModel: RelayHistoryViewModel by viewModels {
        PaginateModelFactory.getInstance(this)
    }
    private val adapter = RelayHistoryAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.rvStatus.layoutManager = LinearLayoutManager(this)
        binding.rvStatus.adapter = adapter

        lifecycleScope.launch {
            viewModel.relayHistory.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }
}