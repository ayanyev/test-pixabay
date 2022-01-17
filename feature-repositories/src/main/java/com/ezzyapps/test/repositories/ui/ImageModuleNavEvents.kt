package com.ezzyapps.test.repositories.ui

import com.ezzyapps.test.pixabay.common.NavTrigger

sealed class ImageModuleNavEvents : NavTrigger {

    class ImageSelectedEvent(val id: Long): ImageModuleNavEvents()
    class ImageSelectionConfirmedEvent(val id: Long): ImageModuleNavEvents()

}