package com.ezzyapps.test.repositories.ui

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.ezzyapps.test.repositories.domain.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

@HiltViewModel
class ThumbsListViewModel @Inject constructor(repo: PhotoRepository) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _itemClicks = PublishSubject.create<Int>()

    val itemClicks: Observable<Int> = _itemClicks

    val images = ObservableField<List<ThumbItemViewModel>>()

    val isLoading = ObservableBoolean(false)

    val errorMsg = ObservableField("")

    val errorMsgVisibility = ObservableField(false)

    init {

        disposables.add(
            repo.getPreviews("fruits")
                .doOnSubscribe { isLoading.set(true) }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { hideError(); isLoading.set(false) }
                .doOnError { isLoading.set(false) }
                .map { list -> list.map { ThumbItemViewModel(it) } }
                .subscribeBy(
                    onNext = { repos -> images.set(repos) },
                    onError = { e -> showError(e.message ?: "Smth happened!!!") }
                )
        )
    }

    private fun showError(msg: String) {
        errorMsg.set(msg)
        errorMsgVisibility.set(true)
    }

    private fun hideError() {
        errorMsgVisibility.set(false)
    }

    override fun onCleared() {
        disposables.dispose()
    }

}