package com.ezzyapps.test.pixabay.common

import android.util.Size
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ezzyapps.test.pixabay.common.flexrecyclerview.DataBindingAdapter
import com.google.android.flexbox.*
import com.squareup.picasso.Picasso

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

@BindingAdapter("query", "onSearch")
fun SearchView.initialise(query: String, doOnSearch: (String) -> Unit) {

    setIconifiedByDefault(false)

    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            doOnSearch(getQuery().toString())
            clearFocus()
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return false
        }

    })

    setQuery(query, true)
    if (requestFocus()) clearFocus()

}

@BindingAdapter("visible")
fun View.setVisible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("imageUrl", "imageRes", "errorRes", "size")
fun ImageView.setImageFromUrlOrResource(
    url: String?,
    @DrawableRes imageRes: Int,
    @DrawableRes errorRes: Int,
    size: Size
) {
    Picasso.get()
        .load(if (url.isNullOrBlank()) null else url)
        .resize(size.width, size.height)
        .placeholder(imageRes)
        .error(errorRes)
        .into(this)
}