package com.ezzyapps.test.repositories.ui.imagedetails

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ezzyapps.test.pixabay.common.ActivityDelegate
import com.ezzyapps.test.pixabay.common.BaseViewModel
import com.ezzyapps.test.repositories.domain.ImageRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy

class ImageDetailsViewModel @AssistedInject constructor(

    @Assisted val hitId: Long,
    override val delegate: ActivityDelegate,
    repo: ImageRepository

) : BaseViewModel() {

    val image = ObservableField<FullImageViewModel>()

    init {

        disposables.add(
            repo.getPhotoDetails(hitId)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    delegate.showLoading(true)
                }
                .doOnTerminate {
                    delegate.showLoading(false)
                }
                .subscribeBy(
                    onSuccess = { i ->
                        image.set(FullImageViewModel(i))
                    },
                    onComplete = {
                        delegate.showMessage("Error: image no found")
                    },
                    onError = { e ->
                        delegate.showMessage("Error: ${e.message ?: "no message"}")
                    }
                )
        )

    }

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(hitId: Long): ImageDetailsViewModel
    }

    companion object {

        fun provideFactory(
            assistedFactory: AssistedFactory,
            hitId: Long
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(hitId) as T
            }
        }

    }

}