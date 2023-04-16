package com.hanuszczak.countryinformation.view.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.hanuszczak.countryinformation.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner

        val country = DetailFragmentArgs.fromBundle(requireArguments()).selectedCountry

        binding.country = country

        binding.googleMapsImg.setOnClickListener {
            val action = DetailFragmentDirections.actionDetailFragmentToMapsFragment()
            this.findNavController().navigate(action)
        }

        return binding.root
    }
}