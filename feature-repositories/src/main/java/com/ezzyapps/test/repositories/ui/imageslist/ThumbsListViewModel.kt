package com.ezzyapps.test.repositories.ui.imageslist

import androidx.databinding.ObservableField
import com.ezzyapps.test.pixabay.common.ActivityDelegate
import com.ezzyapps.test.pixabay.common.BaseViewModel
import com.ezzyapps.test.repositories.domain.ImageRepository
import com.ezzyapps.test.repositories.ui.ImageModuleNavEvents.ImageSelectedEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

@HiltViewModel
class ThumbsListViewModel @Inject constructor(

    override val delegate: ActivityDelegate,
    repo: ImageRepository

) : BaseViewModel() {

    private val defaultQuery = "fruits"

    val images = ObservableField<List<ThumbItemViewModel>>()

    var query = defaultQuery

    private val onImageSelected: (Long) -> Unit = {
        delegate.navigate(ImageSelectedEvent(id = it))
    }

    val doOnSearch: (String) -> Unit = { query ->
        disposables.add(
            repo.getPreviews(query)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    images.set(listOf())
                    this.query = query
                    delegate.showLoading(true)
                }
                .doOnNext { delegate.showLoading(false) }
                .doOnTerminate { delegate.showLoading(false) }
                .map { list -> list.map { ThumbItemViewModel(it, onImageSelected) } }
                .subscribeBy(
                    onNext = { repos ->
                        images.set(repos)
                    },
                    onError = { e ->
                        delegate.showMessage("Error: ${e.message ?: "no message"}")
                    }
                )
        )
    }

}