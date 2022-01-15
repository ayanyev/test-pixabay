package com.ezzyapps.test.repositories.ui.imagedetails

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.ezzyapps.test.pixabay.common.ActivityDelegate
import com.ezzyapps.test.repositories.domain.ImageRepository
import com.ezzyapps.test.repositories.domain.models.FullImage
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

@HiltViewModel
class ImageDetailsViewModel @Inject constructor(
    delegate: ActivityDelegate,
    repo: ImageRepository
) : ViewModel() {

    private val disposables = CompositeDisposable()

    val image = ObservableField<FullImage>()

    init {
        disposables.add(
            repo.getPhotoDetails(1111)
                .doOnSubscribe { delegate.showLoading(true) }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate {
                    delegate.showLoading(false)
                }
                .subscribeBy(
                    onSuccess = { i -> image.set(i) },
                    onComplete = {
                        // TODO show message
                    },
                    onError = { e ->
                        // TODO show message
                    }
                )
        )
    }

    override fun onCleared() {
        disposables.dispose()
    }

}