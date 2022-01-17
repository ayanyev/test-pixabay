package com.ezzyapps.test.repositories.ui.imageslist

import androidx.fragment.app.viewModels
import com.ezzyapps.test.pixabay.common.BaseFragment
import com.ezzyapps.test.repositories.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThumbsListFragment : BaseFragment<ThumbsListViewModel>() {

    override val layoutRes = R.layout.fragment_list
    override val viewModel by viewModels<ThumbsListViewModel>()

    //    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        (requireActivity() as? AppCompatActivity)?.apply {
//            supportActionBar?.setDisplayHomeAsUpEnabled(false)
//        }
//        return DataBindingUtil.inflate<FragmentListBinding>(inflater, R.layout.fragment_list, container, false)
//            .apply { this.viewModel = vm }
//            .root
//    }

}