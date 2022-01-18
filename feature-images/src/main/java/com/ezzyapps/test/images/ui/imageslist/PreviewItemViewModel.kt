package com.ezzyapps.test.images.ui.imageslist

import android.util.Size
import androidx.databinding.ObservableField
import com.ezzyapps.test.images.R
import com.ezzyapps.test.images.domain.models.ImagePreview

class PreviewItemViewModel(

    image: ImagePreview,
    onClick: (Long) -> Unit

) {

    private val height = 300

    val url = image.url

    val error = R.drawable.ic_image_broken

    val size = normalizeSize(image.width, image.height, height)

    val name = "by ${image.userName}"

    val tags = image.tags.joinToString(" ")

    val doOnClick: () -> Unit = {
        onClick(image.id)
    }

    val loading = ObservableField(true)

    val onLoadingFinished: () -> Unit = {
        loading.set(false)
    }

    private fun normalizeSize(width: Int, height: Int, normalizedHeight: Int) =
        Size((width.toDouble() / height.toDouble() * normalizedHeight).toInt(), normalizedHeight)

}