package com.ezzyapps.test.pixabay.common

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject

class ActivityDelegate {

    private val navigationSubject = PublishSubject.create<NavTrigger>()

    val navEvents: Observable<NavTrigger> = navigationSubject

    fun navigate(trigger: NavTrigger) {
        navigationSubject.onNext(trigger)
    }

    private val loadingStateSubject = BehaviorSubject.create<Boolean>()

    val loadingEvents: Observable<Boolean> = loadingStateSubject

    fun showLoading(loading: Boolean) {
        loadingStateSubject.onNext(loading)
    }


}

interface NavTrigger