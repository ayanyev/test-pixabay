package com.ezzyapps.test.repositories.ui.imagedetails

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ezzyapps.test.pixabay.common.BaseFragment
import com.ezzyapps.test.repositories.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ImageDetailsFragment : BaseFragment<ImageDetailsViewModel>() {

    @Inject
    lateinit var detailsViewModelFactory: ImageDetailsViewModel.AssistedFactory

    private val args by navArgs<ImageDetailsFragmentArgs>()

    override val layoutRes = R.layout.fragment_details
    override val viewModel by viewModels<ImageDetailsViewModel> {
        ImageDetailsViewModel.provideFactory(detailsViewModelFactory, args.imageId)
    }

}