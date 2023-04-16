package com.hanuszczak.countryinformation.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.hanuszczak.countryinformation.databinding.FragmentMainBinding
import com.hanuszczak.countryinformation.view.MainFragmentDirections
import com.hanuszczak.countryinformation.viewmodel.adapter.CountryAdapter
import com.hanuszczak.countryinformation.viewmodel.main.MainViewModel
import com.hanuszczak.countryinformation.viewmodel.factory.ViewModelFactory

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(
            this,
            ViewModelFactory(activity.application)
        )[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = CountryAdapter {
            viewModel.onCountryClicked(it)
        }

        binding.countryRecycler.adapter = adapter

        viewModel.countries.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }

        viewModel.navigateToCountry.observe(viewLifecycleOwner) { country ->
            country?.let {
                val action = MainFragmentDirections.actionMainFragmentToDetailFragment()
                this.findNavController().navigate(action)
                viewModel.onCountryNavigated()
            }
        }

        return binding.root
    }
}