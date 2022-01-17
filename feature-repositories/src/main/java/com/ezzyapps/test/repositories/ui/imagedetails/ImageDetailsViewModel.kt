package com.ezzyapps.test.repositories.ui.imagedetails

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.ezzyapps.test.pixabay.common.ActivityDelegate
import com.ezzyapps.test.pixabay.common.BaseViewModel
import com.ezzyapps.test.repositories.domain.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

@HiltViewModel
class ImageDetailsViewModel @Inject constructor(

    override val delegate: ActivityDelegate,
    private val repo: ImageRepository

) : BaseViewModel() {

    val image = ObservableField<FullImageViewModel>()

    fun setId(id: Long) {
        disposables.add(
            repo.getPhotoDetails(id)
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

}