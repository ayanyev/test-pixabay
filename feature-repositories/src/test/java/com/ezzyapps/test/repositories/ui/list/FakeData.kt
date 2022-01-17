package com.ezzyapps.test.repositories.ui.list

import com.ezzyapps.test.repositories.domain.models.FullImage
import com.ezzyapps.test.repositories.domain.models.PreviewImage

const val fakeHitId: Long = 1111

val fakePreview = PreviewImage(
    id = fakeHitId,
    userName = "Mad Rabbit",
    url = "https://cdn.pixabay.com/photo/2021/09/23/05/30/strawberry-6648685_150.jpg",
    width = 150,
    height = 100,
    tags = listOf("strawberry", "fruit", "food")
)

val fakeFullDetails = FullImage(
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