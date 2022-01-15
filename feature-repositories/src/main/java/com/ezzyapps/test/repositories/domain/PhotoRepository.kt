package com.ezzyapps.test.repositories.domain

import com.ezzyapps.test.repositories.domain.models.FullImage
import com.ezzyapps.test.repositories.domain.models.PreviewImage
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface PhotoRepository {

    fun getPreviews(query: String) : Observable<List<PreviewImage>>

    fun getPhotoDetails(id: Int) : Maybe<FullImage>

}