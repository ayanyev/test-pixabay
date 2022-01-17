package com.ezzyapps.test.pixabay.common

import android.util.Size
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

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
    size: Size?
) {

    if (url == null) return

    Picasso.get()
        .load(if (url.isNullOrBlank()) null else url)
        .run {
            if (size != null) resize(size.width, size.height) else centerCrop()
        }
        .placeholder(imageRes)
        .error(errorRes)
        .into(this)
}