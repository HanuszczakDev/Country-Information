package com.hanuszczak.countryinformation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.hanuszczak.countryinformation.databinding.FragmentMainBinding
import com.hanuszczak.countryinformation.viewmodel.main.MainViewModel
import com.hanuszczak.countryinformation.viewmodel.main.MainViewModelFactory

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(
            this,
            MainViewModelFactory(activity.application)
        )[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.countries.observe(viewLifecycleOwner) {
            var result = ""
            it.forEach { item ->
                result += "official: ${item.nameOfficial} region: ${item.region} \n"
            }
            binding.textView.text = result
        }

        return binding.root
    }
}