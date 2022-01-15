package com.ezzyapps.test.repositories.ui.imageslist

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.ezzyapps.test.pixabay.common.ActivityDelegate
import com.ezzyapps.test.repositories.domain.ImageRepository
import com.ezzyapps.test.repositories.ui.ImageModuleNavEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

@HiltViewModel
class ThumbsListViewModel @Inject constructor(
    delegate: ActivityDelegate,
    repo: ImageRepository
) : ViewModel() {

    private val disposables = CompositeDisposable()

    val images = ObservableField<List<ThumbItemViewModel>>()

    val isLoading = ObservableBoolean(false)

    val errorMsg = ObservableField("")

    val errorMsgVisibility = ObservableField(false)

    val query = ObservableField("fruits")

    private val onImageSelected: (Long) -> Unit = {
        delegate.navigate(ImageModuleNavEvents.ImageSelectedEvent)
    }

    val doOnSearch: (String) -> Unit = { query ->
        disposables.add(
            repo.getPreviews(query)
                .doOnSubscribe { delegate.showLoading(true)}
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { delegate.showLoading(false) }
                .doOnTerminate { delegate.showLoading(false) }
                .map { list -> list.map { ThumbItemViewModel(it, onImageSelected) } }
                .subscribeBy(
                    onNext = { repos -> images.set(repos) },
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