package com.ezzyapps.test.pixabay.common.flexrecyclerview

import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.*

@BindingAdapter("items", "layout")
fun RecyclerView.setItems(items: List<Any>?, @LayoutRes layout: Int) {
    if (items == null) return
    layoutManager = FlexboxLayoutManager(context).apply {
        flexWrap = FlexWrap.WRAP
        flexDirection = FlexDirection.COLUMN
        alignItems = AlignItems.FLEX_START
        justifyContent = JustifyContent.FLEX_START
    }
    adapter = DataBindingAdapter(items, layout)
}