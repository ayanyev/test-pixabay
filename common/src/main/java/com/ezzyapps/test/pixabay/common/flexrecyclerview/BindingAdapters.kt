package com.ezzyapps.test.pixabay.common.flexrecyclerview

import android.content.res.Configuration.ORIENTATION_PORTRAIT
import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("items", "layout")
fun RecyclerView.setItems(items: List<Any>?, @LayoutRes layout: Int) {
    if (items == null) return
    val orientation = context.resources.configuration.orientation
    val spanCount = if (orientation == ORIENTATION_PORTRAIT) 2 else 4
    layoutManager = GridLayoutManager(context, spanCount)
    adapter = DataBindingAdapter(items, layout)
}