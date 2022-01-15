package com.ezzyapps.test.repositories.ui

import android.util.Size
import com.ezzyapps.test.repositories.R
import com.ezzyapps.test.repositories.domain.models.PreviewImage

class ThumbItemViewModel(

    image: PreviewImage

) {

    val url = image.url

    val placeholder = R.drawable.ic_camera

    val error = R.drawable.ic_image_broken

    val size = Size(image.width, image.height)

    val name = "by ${image.userName}"

    val tags = image.tags.joinToString(" ")

}