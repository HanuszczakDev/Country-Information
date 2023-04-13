package com.hanuszczak.countryinformation.util

import androidx.recyclerview.widget.DiffUtil
import com.hanuszczak.countryinformation.model.domain.Country

class DiffItemCallback : DiffUtil.ItemCallback<Country>() {
    override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean =
        (oldItem.nameOfficial == newItem.nameOfficial)

    override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean =
        (oldItem == newItem)
}