package com.ezzyapps.test.pixabay.common

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    abstract val delegate: ActivityDelegate

    protected val disposables = CompositeDisposable()

    override fun onCleared() {
        disposables.dispose()
    }

}