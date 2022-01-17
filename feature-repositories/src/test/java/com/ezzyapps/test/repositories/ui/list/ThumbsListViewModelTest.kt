package com.ezzyapps.test.repositories.ui.list

import com.ezzyapps.test.pixabay.common.ActivityDelegate
import com.ezzyapps.test.pixabay.common.test.ImmediateSchedulersRule
import com.ezzyapps.test.repositories.domain.ImageRepository
import com.ezzyapps.test.repositories.ui.imageslist.ThumbsListViewModel
import io.mockk.*
import io.reactivex.rxjava3.core.Observable.error
import io.reactivex.rxjava3.core.Observable.just
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class ThumbsListViewModelTest {

    @get:Rule
    val rxTestRule = ImmediateSchedulersRule()

    private val repository = mockk<ImageRepository>()

    private val delegate = spyk<ActivityDelegate>()

    private val query = "query"

    @Test
    fun successTest() {

        val idSlot = slot<Long>()

        every { repository.getPreviews(query) } returns just(fakePreviews)

        val viewModel = spyk(ThumbsListViewModel(delegate, repository))

        viewModel.doSearch(query)

        verifyOrder {
            repository.getPreviews(query)
            delegate.showLoading(true)
            delegate.showLoading(false)
            viewModel.showPreviews(any())
        }

        viewModel.images.get()?.first()!!.doOnClick.invoke()

        verifyOrder {
            viewModel.showDetails(capture(idSlot))
        }

        assertEquals(fakeHitId, idSlot.captured)

    }

    @Test
    fun errorLoadingRepositoryDetailsTest() {

        val e = Exception("Images not found")

        val errorSlot = slot<String>()

        every { repository.getPreviews(query) } returns error(e)

        val viewModel = spyk(ThumbsListViewModel(delegate, repository))

        viewModel.doSearch(query)

        verifyOrder {
            repository.getPreviews(query)
            delegate.showLoading(true)
            delegate.showLoading(false)
            delegate.showMessage(capture(errorSlot))
        }

        assertEquals("Error: ${e.message}", errorSlot.captured)

    }

}