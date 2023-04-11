package com.hanuszczak.countryinformation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.hanuszczak.countryinformation.databinding.FragmentMainBinding
import com.hanuszczak.countryinformation.viewmodel.main.MainViewModel

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.countryDto.observe(viewLifecycleOwner) {
            binding.textView.text = it?.cca2
        }

        return binding.root
    }
}