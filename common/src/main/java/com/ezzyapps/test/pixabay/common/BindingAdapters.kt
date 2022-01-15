package com.ezzyapps.test.pixabay.common

import android.util.Size
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ezzyapps.test.pixabay.common.flexrecyclerview.DataBindingAdapter
import com.google.android.flexbox.*
import com.squareup.picasso.Callback
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

@BindingAdapter("visible")
fun View.setVisible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("imageUrl", "imageRes", "errorRes", "size", requireAll = false)
fun ImageView.setImageFromUrlOrResource(
    url: String?,
    @DrawableRes imageRes: Int? = null,
    @DrawableRes errorRes: Int? = null,
    size: Size? = null
) {
    val requestedScaleType = scaleType
    scaleType = ImageView.ScaleType.CENTER
    when {
        url == null && imageRes == null -> return
        url == null && imageRes != null -> setImageResource(imageRes)
        else -> Picasso.get()
            .load(if (url.isNullOrBlank()) null else url)
            .apply {
                if (errorRes != null) error(errorRes)
                if (imageRes != null) placeholder(imageRes)
                if (size == null) centerCrop() else resize(size.width, size.height)
            }
            .into(this, object : Callback {
                override fun onSuccess() {
                    scaleType = requestedScaleType
                }
                override fun onError(e: Exception?) {
                    // TODO("Not yet implemented")
                }

            })
    }
}