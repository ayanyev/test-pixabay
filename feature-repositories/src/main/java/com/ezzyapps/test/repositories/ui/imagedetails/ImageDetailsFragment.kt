package com.ezzyapps.test.repositories.ui.imagedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ezzyapps.test.repositories.R
import com.ezzyapps.test.repositories.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageDetailsFragment : Fragment() {

    private val args: ImageDetailsFragmentArgs by navArgs()

    private val vm: ImageDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as? AppCompatActivity)?.apply {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
        return DataBindingUtil.inflate<FragmentDetailsBinding>(inflater, R.layout.fragment_details, container, false)
            .apply {
                viewModel = vm
                vm.setId(args.imageId)
            }.root
    }

}