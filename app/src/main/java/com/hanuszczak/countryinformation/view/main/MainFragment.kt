package com.hanuszczak.countryinformation.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hanuszczak.countryinformation.databinding.FragmentMainBinding
import com.hanuszczak.countryinformation.viewmodel.adapter.CountryAdapter
import com.hanuszczak.countryinformation.viewmodel.main.MainViewModel

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private var isScrolling = false

    lateinit var viewModel: MainViewModel

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.didOrientationChange = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity = requireNotNull(this.activity)
        viewModel = ViewModelProvider(
            this,
            SavedStateViewModelFactory(activity.application,
                this
            )
        )[MainViewModel::class.java]
    }

    override fun onResume() {
        super.onResume()
        saveScrollPosition()
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

        binding.countryRecycler.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                isScrolling = newState != AbsListView.OnScrollListener.SCROLL_STATE_IDLE
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                saveScrollPosition()
            }
        })

        viewModel.countries.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
            if (viewModel.didOrientationChange) {
                binding.countryRecycler.scrollToPosition(viewModel.scrollPosition - 1)
                viewModel.didOrientationChange = false
            }
        }

        viewModel.navigateToCountry.observe(viewLifecycleOwner) { country ->
            country?.let {
                val action = MainFragmentDirections.actionMainFragmentToDetailFragment(country)
                this.findNavController().navigate(action)
                viewModel.onCountryNavigated()
            }
        }

        return binding.root
    }

    private fun saveScrollPosition() {
        val linearLayoutManager = binding.countryRecycler.layoutManager as LinearLayoutManager
        val centerScrollPosition =
            (linearLayoutManager.findLastCompletelyVisibleItemPosition() + linearLayoutManager.findFirstCompletelyVisibleItemPosition()) / 2
        viewModel.setScrollPosition(centerScrollPosition)
    }
}