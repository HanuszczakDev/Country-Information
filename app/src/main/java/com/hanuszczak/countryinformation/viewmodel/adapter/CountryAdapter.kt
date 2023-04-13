package com.hanuszczak.countryinformation.viewmodel.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hanuszczak.countryinformation.databinding.CountryCardLayoutBinding
import com.hanuszczak.countryinformation.model.domain.Country
import com.hanuszczak.countryinformation.util.DiffItemCallback

class CountryAdapter(val clickListener: (country: Country) -> Unit)
    : ListAdapter<Country, CountryAdapter.ViewHolder>(DiffItemCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder =
        ViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class ViewHolder(val binding: CountryCardLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun inflateFrom(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CountryCardLayoutBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(item: Country, clickListener: (country: Country) -> Unit) {
            binding.country = item
            binding.root.setOnClickListener { clickListener(item) }
        }
    }
}