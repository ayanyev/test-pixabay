package com.ezzyapps.test.images.ui.list

import com.ezzyapps.test.images.domain.models.ImageDetails
import com.ezzyapps.test.images.domain.models.ImagePreview

const val fakeHitId: Long = 1111

val fakePreview = ImagePreview(
    id = fakeHitId,
    userName = "Mad Rabbit",
    url = "https://cdn.pixabay.com/photo/2021/09/23/05/30/strawberry-6648685_150.jpg",
    width = 150,
    height = 100,
    tags = listOf("strawberry", "fruit", "food")
)

val fakeFullDetails = ImageDetails(
    id = fakeHitId,
    userName = "Mad Rabbit",
    url = "https://cdn.pixabay.com/photo/2021/09/23/05/30/strawberry-6648685_150.jpg",
    width = 150,
    height = 100,
    tags = listOf("strawberry", "fruit", "food"),
    likesCount = 343,
    downloadsCount = 4564565,
    commentsCount = 344
)

val fakePreviews = listOf(
    fakePreview, fakePreview, fakePreview,
    fakePreview, fakePreview, fakePreview,
    fakePreview, fakePreview
)