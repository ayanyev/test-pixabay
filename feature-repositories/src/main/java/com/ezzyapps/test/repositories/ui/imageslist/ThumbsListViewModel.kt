package com.ezzyapps.test.repositories.ui.imageslist

import androidx.annotation.VisibleForTesting
import androidx.databinding.ObservableField
import com.ezzyapps.test.pixabay.common.ActivityDelegate
import com.ezzyapps.test.pixabay.common.BaseViewModel
import com.ezzyapps.test.repositories.domain.ImageRepository
import com.ezzyapps.test.repositories.domain.models.PreviewImage
import com.ezzyapps.test.repositories.ui.ImageModuleNavEvents.ImageSelectedEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

@HiltViewModel
class ThumbsListViewModel @Inject constructor(

    override val delegate: ActivityDelegate,
    private val repo: ImageRepository

) : BaseViewModel() {

    private val defaultQuery = "fruits"

    val images = ObservableField<List<ThumbItemViewModel>>()

    var query = defaultQuery

    val doOnSearch: (String) -> Unit = { query -> doSearch(query) }

    @VisibleForTesting
    fun doSearch(query: String) {
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
                .subscribeBy(
                    onNext = { hits ->
                        showPreviews(hits)
                    },
                    onError = { e ->
                        delegate.showMessage("Error: ${e.message ?: "no message"}")
                    }
                )
        )
    }

    @VisibleForTesting
    fun showDetails(id: Long) {
        delegate.navigate(ImageSelectedEvent(id))
    }

    @VisibleForTesting
    fun showPreviews(list: List<PreviewImage>) {
        val vms = list.map {
            ThumbItemViewModel(it) { id -> showDetails(id) }
        }
        images.set(vms)
    }

}