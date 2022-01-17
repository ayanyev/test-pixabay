package com.ezzyapps.test.repositories.data.mappers

import com.ezzyapps.test.repositories.data.local.models.HitDbo
import com.ezzyapps.test.repositories.data.remote.models.HitDto
import com.ezzyapps.test.repositories.domain.models.FullImage
import com.ezzyapps.test.repositories.domain.models.PreviewImage

fun HitDto.toLocal() = HitDbo(
    hitId = id,
    userName = user,
    urlFull = largeImageURL,
    urlThumb = webFormatURL,
    widthFull = imageWidth,
    heightFull = imageHeight,
    widthThumb = webFormatWidth,
    heightThumb = webFormatHeight,
    tags = tags,
    likesCount = likes,
    downloadsCount = downloads,
    commentsCount = comments
)

fun List<HitDto>.toLocal() = map { it.toLocal() }

fun HitDbo.toPreviewImage() = PreviewImage(
    id = hitId,
    userName = userName,
    url = urlThumb,
    width = widthThumb,
    height = heightThumb,
    tags = tags.split(",")
)

fun List<HitDbo>.toPreview() = map { it.toPreviewImage() }

fun HitDbo.toFullImage() = FullImage(
    id = hitId,
    userName = userName,
    url = urlThumb,
    width = widthFull,
    height = heightFull,
    tags = tags.split(","),
    likesCount = likesCount,
    downloadsCount = downloadsCount,
    commentsCount = commentsCount
)