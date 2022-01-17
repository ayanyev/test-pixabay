package com.ezzyapps.test.repositories.domain

import com.ezzyapps.test.repositories.domain.models.ImageDetails
import com.ezzyapps.test.repositories.domain.models.ImagePreview
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable

interface ImageRepository {

    fun getPreviews(query: String) : Observable<List<ImagePreview>>

    fun getDetails(id: Long) : Maybe<ImageDetails>

}