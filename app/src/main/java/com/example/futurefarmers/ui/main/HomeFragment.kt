package com.example.futurefarmers.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.futurefarmers.R
import com.example.futurefarmers.data.remote.config.ApiConfig
import com.example.futurefarmers.data.remote.config.ApiService
import com.example.futurefarmers.data.response.DataResponse
import com.example.futurefarmers.databinding.FragmentHomeBinding
import com.example.futurefarmers.ui.ViewModelFactory
import com.example.futurefarmers.ui.login.LoginActivity
import com.example.futurefarmers.ui.plant.AddPlantActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import kotlin.math.log

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private var token: String? = null
    private val mainViewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }
    private var mCompositeDisposable: CompositeDisposable? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        mCompositeDisposable = CompositeDisposable()

        mainViewModel.getSession().observe(viewLifecycleOwner) {
            token = "Bearer $it"
            if (it == "") {
                startActivity(Intent(requireContext(), LoginActivity::class.java))
                requireActivity().finish()
            } else {
                token?.let { token ->
                    mainViewModel.tanaman(token)
                    mainViewModel.getDataPlant().observe(viewLifecycleOwner){
                        binding.tvUmur.text = it.umur.toString()
                        binding.tvPanen.text = it.panen.toString()
                        binding.tvSayur.text = it.nama.toString()
                    }
                    loadDataEvery15Seconds(token)
                }
            }
        }
        binding.cvSedangMenanam.setOnClickListener {
            startActivity(Intent(requireContext(), AddPlantActivity::class.java))
        }
    }

    private fun loadDataEvery15Seconds(token: String) {
        val requestInterface = ApiConfig.getApiService()

        val disposable = Observable.interval(0, 15, TimeUnit.SECONDS)
            .flatMap {
                requestInterface.getDataObservable(token)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
            .subscribe(this::handleResponse, this::handleError)
        Log.e("Home Fragment","Jalan")
        mCompositeDisposable?.add(disposable)
    }

    private fun handleResponse(data: DataResponse) {
        updateUI(data)
    }

    private fun handleError(throwable: Throwable) {
        // Handle errors here
    }

    private fun updateUI(data: DataResponse) {


        binding.tvAngkaSuhu.text = data.suhu.toString()
        binding.tvAngkaKelembapan.text = data.kelembapan.toString()
        binding.tvAngkaTingkatKeasaman.text = data.ph.toString()
        binding.tvAngkaNutrisi.text = data.tds.toString()
        val ph = data.ph.toString().toFloat() * 10
        val suhu = data.suhu.toString().toFloat() * 10

        binding.progressBarPh.progress = ph.toInt()
        binding.progressBarSuhu.progress = suhu.toInt()
        binding.progressBarKelembapan.progress = data.kelembapan
        binding.progressBarNutrisi.progress = data.tds

    }

    override fun onDestroyView() {
        super.onDestroyView()
        mCompositeDisposable?.clear()
    }
}
