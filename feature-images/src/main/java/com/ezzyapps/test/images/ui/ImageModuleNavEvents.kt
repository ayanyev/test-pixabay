package com.ezzyapps.test.images.ui

import com.ezzyapps.test.pixabay.common.NavTrigger

sealed class ImageModuleNavEvents : NavTrigger {

    data class ImageSelectedEvent(val id: Long): ImageModuleNavEvents()
    data class ImageSelectionConfirmedEvent(val id: Long): ImageModuleNavEvents()

}