package com.ezzyapps.test.images.domain

import com.ezzyapps.test.images.domain.models.ImageDetails
import com.ezzyapps.test.images.domain.models.ImagePreview
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable

interface ImageRepository {

    fun getPreviews(query: String) : Observable<List<ImagePreview>>

    fun getDetails(id: Long) : Maybe<ImageDetails>

}