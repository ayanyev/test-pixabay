package com.ezzyapps.test.repositories.ui.imagedetails

import com.ezzyapps.test.pixabay.common.ActivityDelegate
import com.ezzyapps.test.pixabay.common.test.ImmediateSchedulersRule
import com.ezzyapps.test.repositories.domain.ImageRepository
import com.ezzyapps.test.repositories.ui.list.fakeFullDetails
import com.ezzyapps.test.repositories.ui.list.fakeHitId
import io.mockk.*
import io.reactivex.rxjava3.core.Maybe
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class ImageDetailsViewModelTest {

    @get:Rule
    val rxTestRule = ImmediateSchedulersRule()

    private val repository = mockk<ImageRepository>()

    private val delegate = spyk<ActivityDelegate>()

    @Test
    fun successTest() {

        every { repository.getDetails(fakeHitId) } returns Maybe.just(fakeFullDetails)

        val viewModel = spyk(ImageDetailsViewModel(fakeHitId, delegate, repository))

        verifyOrder {
            repository.getDetails(fakeHitId)
            delegate.showLoading(true)
            delegate.showLoading(false)
        }

    }

    @Test
    fun imageNotFoundTest() {

        val message = "Error: image no found"

        val msgSlot = slot<String>()

        every { repository.getDetails(fakeHitId) } returns Maybe.empty()

        val viewModel = spyk(ImageDetailsViewModel(fakeHitId, delegate, repository))

        verifyOrder {
            repository.getDetails(fakeHitId)
            delegate.showLoading(true)
            delegate.showLoading(false)
            delegate.showMessage(capture(msgSlot))
        }

        Assert.assertEquals(message, msgSlot.captured)

    }

}