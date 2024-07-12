package com.example.futurefarmers.ui.control

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.viewModels
import com.example.futurefarmers.R
import com.example.futurefarmers.data.remote.config.ApiConfig
import com.example.futurefarmers.data.response.DataResponse
import com.example.futurefarmers.data.response.GetRelayStatusResponse
import com.example.futurefarmers.databinding.FragmentConfigBinding
import com.example.futurefarmers.databinding.FragmentSettingBinding
import com.example.futurefarmers.ui.ViewModelFactory
import com.example.futurefarmers.ui.login.LoginActivity
import com.example.futurefarmers.ui.main.MainViewModel
import com.google.gson.JsonObject
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
/**
 * A simple [Fragment] subclass.
 * Use the [ConfigFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ConfigFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentConfigBinding
    private var token: String? = null

    private val controlViewModel by viewModels<ControlViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }
    private var mCompositeDisposable: CompositeDisposable? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentConfigBinding.inflate(inflater, container, false)
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


        controlViewModel.getSession().observe(viewLifecycleOwner) {
            token = "Bearer $it"
            if (it == "") {
                startActivity(Intent(requireContext(), LoginActivity::class.java))
                requireActivity().finish()
            }else{
                token?.let { token ->
                    loadDataEvery15Seconds(token)
                }
            }
        }
        setupSwitchListeners()
        setupToggleButtonListeners()
    }
    private fun setupToggleButtonListeners() {
        binding.kipasButton.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                val param = JsonObject().apply {
                    addProperty("manual_five", 1)
                }
                token?.let { controlViewModel.updateRelayManualFive(it,param) }
                binding.swOnOffKipas.isEnabled = true
            } else {
                val param = JsonObject().apply {
                    addProperty("manual_five", 0)
                }
                token?.let { controlViewModel.updateRelayManualFive(it,param) }
                binding.swOnOffKipas.isEnabled = false
            }
        }

        binding.lampuButton.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                val param = JsonObject().apply {
                    addProperty("manual_six", 1)
                }
                token?.let { controlViewModel.updateRelayManualSix(it,param) }
                binding.swOnOffLampu.isEnabled = true

            } else {
                val param = JsonObject().apply {
                    addProperty("manual_six", 0)
                }
                token?.let { controlViewModel.updateRelayManualSix(it,param) }
                binding.swOnOffLampu.isEnabled = false

            }
        }

        binding.phUpButton.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                val param = JsonObject().apply {
                    addProperty("manual_one", 1)
                }
                token?.let { controlViewModel.updateRelayManualOne(it,param) }
                binding.swOnOffPhUp.isEnabled = true

            } else {
                val param = JsonObject().apply {
                    addProperty("manual_one", 0)
                }
                token?.let { controlViewModel.updateRelayManualOne(it,param) }
                binding.swOnOffPhUp.isEnabled = false

            }
        }

        binding.phDownButton.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                val param = JsonObject().apply {
                    addProperty("manual_two", 1)
                }
                token?.let { controlViewModel.updateRelayManualTwo(it,param) }
                binding.swOnOffPhDown.isEnabled = true

            } else {
                val param = JsonObject().apply {
                    addProperty("manual_two", 0)
                }
                token?.let { controlViewModel.updateRelayManualTwo(it,param) }
                binding.swOnOffPhDown.isEnabled = false

            }
        }

        binding.nutrisiButton.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                val param = JsonObject().apply {
                    addProperty("manual_three", 1)
                }
                token?.let { controlViewModel.updateRelayManualThree(it,param) }
                binding.swOnOffNutrisi.isEnabled = true

            } else {
                val param = JsonObject().apply {
                    addProperty("manual_three", 0)
                }
                token?.let { controlViewModel.updateRelayManualThree(it,param) }
                binding.swOnOffNutrisi.isEnabled = false

            }
        }

    }
    private fun setupSwitchListeners() {
        binding.swOnOffKipas.setOnCheckedChangeListener { buttonView, isChecked ->
            Log.d("ConfigFragment", "Fan switch changed: $isChecked")
            if (isChecked == true){
                val param = JsonObject().apply {
                    addProperty("fan", 1)
                }
                token?.let { controlViewModel.updateRelayFan(it,param) }
            }else{
                val param = JsonObject().apply {
                    addProperty("fan", 0)
                }
                token?.let { controlViewModel.updateRelayFan(it,param) }
            }
        }

        binding.swOnOffLampu.setOnCheckedChangeListener { buttonView, isChecked ->
            Log.d("ConfigFragment", "Light switch changed: $isChecked")
            if (isChecked == true){
                val param = JsonObject().apply {
                    addProperty("light", 1)
                }
                token?.let { controlViewModel.updateRelayLight(it,param) }
            }else{
                val param = JsonObject().apply {
                    addProperty("light", 0)
                }
                token?.let { controlViewModel.updateRelayLight(it,param) }
            }
        }

        binding.swOnOffNutrisi.setOnCheckedChangeListener { buttonView, isChecked ->
            Log.d("ConfigFragment", "Nutrisi switch changed: $isChecked")
            if (isChecked == true){
                val param = JsonObject().apply {
                    addProperty("nutrisi", 1)
                }
                token?.let { controlViewModel.updateRelayNutrisi(it,param) }
            }else{
                val param = JsonObject().apply {
                    addProperty("nutrisi", 0)
                }
                token?.let { controlViewModel.updateRelayNutrisi(it,param) }
            }
        }

        binding.swOnOffPhUp.setOnCheckedChangeListener { buttonView, isChecked ->
            Log.d("ConfigFragment", "pH Up switch changed: $isChecked")
            if (isChecked == true){
                val param = JsonObject().apply {
                    addProperty("ph_up", 1)
                }
                token?.let { controlViewModel.updateRelayPhUp(it,param) }
            }else{
                val param = JsonObject().apply {
                    addProperty("ph_up", 0)
                }
                token?.let { controlViewModel.updateRelayPhUp(it,param) }
            }
        }

        binding.swOnOffPhDown.setOnCheckedChangeListener { buttonView, isChecked ->
            Log.d("ConfigFragment", "pH Down switch changed: $isChecked")
            if (isChecked == true){
                val param = JsonObject().apply {
                    addProperty("ph_down", 1)
                }
                token?.let { controlViewModel.updateRelayPhDown(it,param) }
            }else{
                val param = JsonObject().apply {
                    addProperty("ph_down", 0)
                }
                token?.let { controlViewModel.updateRelayPhDown(it,param) }
            }
        }

    }
    private fun loadDataEvery15Seconds(token: String) {
        val requestInterface = ApiConfig.getApiService()

        val disposable = Observable.interval(0, 5, TimeUnit.SECONDS)
            .flatMap {
                requestInterface.getRelayStatus(token)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
            .subscribe(this::handleResponse, this::handleError)
        Log.e("Config Fragment","Jalan")
        mCompositeDisposable?.add(disposable)
    }
    private fun handleResponse(data: GetRelayStatusResponse) {
        updateUI(data)
    }

    private fun handleError(throwable: Throwable) {
        // Handle errors here
    }

    private fun updateUI(data: GetRelayStatusResponse) {
        binding.swOnOffKipas.isChecked = data.fan == 1
        binding.swOnOffLampu.isChecked =  data.light == 1
        binding.swOnOffNutrisi.isChecked =  data.nutA == 1
        binding.swOnOffPhUp.isChecked = data.phUp == 1
        binding.swOnOffPhDown.isChecked =  data.phDown == 1
        binding.phUpButton.isChecked = data.isManual1 == 1
        binding.phDownButton.isChecked = data.isManual2 == 1
        binding.nutrisiButton.isChecked = data.isManual3 == 1
        binding.kipasButton.isChecked = data.isManual5 == 1
        binding.lampuButton.isChecked = data.isManual6 == 1
        binding.swOnOffKipas.isEnabled = data.isManual5 == 1
        binding.swOnOffLampu.isEnabled = data.isManual6 == 1
        binding.swOnOffPhUp.isEnabled = data.isManual1 == 1
        binding.swOnOffPhDown.isEnabled = data.isManual2 == 1
        binding.swOnOffNutrisi.isEnabled = data.isManual3 == 1
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mCompositeDisposable?.clear()
    }

}