package com.ezzyapps.test.repositories.ui.imageslist

import androidx.fragment.app.viewModels
import com.ezzyapps.test.pixabay.common.BaseFragment
import com.ezzyapps.test.repositories.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreviewListFragment : BaseFragment<PreviewListViewModel>() {

    override val layoutRes = R.layout.fragment_list
    override val viewModel by viewModels<PreviewListViewModel>()

}