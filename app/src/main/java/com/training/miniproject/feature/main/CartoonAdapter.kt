package com.training.miniproject.feature.main

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.training.miniproject.model.cartoon.Cartoon


class CartoonAdapter: PagingDataAdapter<Cartoon, CartoonViewHolder>(CARTOON_COMPARATOR) {

    companion object {
        private val CARTOON_COMPARATOR = object : DiffUtil.ItemCallback<Cartoon>() {
            override fun areItemsTheSame(oldItem: Cartoon, newItem: Cartoon): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Cartoon, newItem: Cartoon): Boolean =
                oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: CartoonViewHolder, position: Int) {
        val cartoonItem = getItem(position)
        if (cartoonItem != null)
            holder.bind(cartoonItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartoonViewHolder {
        return CartoonViewHolder.create(parent)
    }
}