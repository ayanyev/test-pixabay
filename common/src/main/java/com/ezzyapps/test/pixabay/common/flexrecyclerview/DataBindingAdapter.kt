package com.ezzyapps.test.pixabay.common.flexrecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ezzyapps.test.pixabay.common.BR

class DataBindingAdapter<T>(
    private val items: List<T>,
    @LayoutRes private val layoutId: Int
) : RecyclerView.Adapter<DataBindingAdapter.DataBindingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder {
        return DataBindingViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                viewType,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder, position: Int) {
        if (position > itemCount - 1) return
        val bindingSuccess = holder.binding.setVariable(BR.viewModel, items[position])
        if (!bindingSuccess) {
            throw IllegalStateException("Binding ${holder.binding} viewModel variable name should be 'viewModel'")
        }
    }

    override fun getItemViewType(position: Int) = layoutId

    override fun getItemCount() = items.size

    class DataBindingViewHolder(val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root)

}