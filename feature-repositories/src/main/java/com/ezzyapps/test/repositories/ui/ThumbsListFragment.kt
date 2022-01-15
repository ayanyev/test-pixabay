package com.ezzyapps.test.repositories.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ezzyapps.test.repositories.R
import com.ezzyapps.test.repositories.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable

@AndroidEntryPoint
class ThumbsListFragment : Fragment() {

    companion object {
        fun newInstance() = ThumbsListFragment()
    }

    private val vm: ThumbsListViewModel by viewModels()

    private val disposables = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as? AppCompatActivity)?.apply {
//            title = getString(R.string.title_main)
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
        return DataBindingUtil.inflate<FragmentListBinding>(inflater, R.layout.fragment_list, container, false)
            .apply { this.viewModel = vm }
            .root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        disposables.add(
//            vm.itemClicks
//                .subscribeBy {
//
//                    val fragment = DetailsFragment.newInstance().apply {
//                        arguments = bundleOf(ARG_REPO_ID to it)
//                    }
//
//                    parentFragmentManager.beginTransaction()
//                        .replace(R.id.container, fragment)
//                        .addToBackStack(null)
//                        .commit()
//                }
//        )
//    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }
}