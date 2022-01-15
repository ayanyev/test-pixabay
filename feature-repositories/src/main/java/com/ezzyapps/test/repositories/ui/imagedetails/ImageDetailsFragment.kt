package com.ezzyapps.test.repositories.ui.imagedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ezzyapps.test.repositories.R
import com.ezzyapps.test.repositories.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable

@AndroidEntryPoint
class ImageDetailsFragment : Fragment() {

    private val vm: ImageDetailsViewModel by viewModels()

    private val disposables = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as? AppCompatActivity)?.apply {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
        return DataBindingUtil.inflate<FragmentDetailsBinding>(inflater, R.layout.fragment_details, container, false)
            .apply { this.viewModel = vm }
            .root
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }
}