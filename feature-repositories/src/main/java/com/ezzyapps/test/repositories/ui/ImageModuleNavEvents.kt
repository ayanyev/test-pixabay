package com.ezzyapps.test.repositories.ui

import com.ezzyapps.test.pixabay.common.NavTrigger

sealed class ImageModuleNavEvents : NavTrigger {

    object ImageSelectedEvent: NavTrigger
    object ImageSelectionConfirmedEvent: NavTrigger

}