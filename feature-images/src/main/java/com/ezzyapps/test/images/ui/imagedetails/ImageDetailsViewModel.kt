package com.ezzyapps.test.images.ui.imagedetails

import androidx.annotation.VisibleForTesting
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ezzyapps.test.pixabay.common.ActivityDelegate
import com.ezzyapps.test.pixabay.common.BaseViewModel
import com.ezzyapps.test.images.domain.ImageRepository
import com.ezzyapps.test.images.domain.models.ImageDetails
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy

class ImageDetailsViewModel @AssistedInject constructor(

    @Assisted val hitId: Long,
    override val delegate: ActivityDelegate,
    private val repo: ImageRepository

) : BaseViewModel() {

    val image = ObservableField<FullImageViewModel>()

    init {

        fetchDetails(hitId)

    }

    @VisibleForTesting
    fun showDetails(i: ImageDetails) {
        image.set(FullImageViewModel(i))
    }

    @VisibleForTesting
    fun fetchDetails(hitId: Long) {
        disposables.add(
            repo.getDetails(hitId)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    delegate.showLoading(true)
                }
                .doOnTerminate {
                    delegate.showLoading(false)
                }
                .subscribeBy(
                    onSuccess = { i ->
                        showDetails(i)
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