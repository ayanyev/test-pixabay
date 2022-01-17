package com.ezzyapps.test.repositories.ui.imagedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ezzyapps.test.pixabay.common.BaseFragment
import com.ezzyapps.test.repositories.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageDetailsFragment : BaseFragment<ImageDetailsViewModel>() {

    override val layoutRes = R.layout.fragment_details
    override val viewModel by viewModels<ImageDetailsViewModel>()

    private val args by navArgs<ImageDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = super.onCreateView(inflater, container, savedInstanceState)
        viewModel.setId(args.imageId)
        return root
    }

}