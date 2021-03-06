package com.ezzyapps.test.images.ui.imagedetails

import android.util.Size
import com.ezzyapps.test.images.R
import com.ezzyapps.test.images.domain.models.ImageDetails

class FullImageViewModel(
    image: ImageDetails
) {

    val url = image.url

    val size = Size(image.width, image.height)

    val placeholder = R.drawable.ic_camera

    val error = R.drawable.ic_image_broken

    val name = "created by ${image.userName}"

    val tags = image.tags.joinToString(" | ")

    val likes = image.likesCount.toString()

    val downloads = image.downloadsCount.toString()

    val comments = image.commentsCount.toString()

}