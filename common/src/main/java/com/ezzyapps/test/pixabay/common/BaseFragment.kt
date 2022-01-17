package com.ezzyapps.test.pixabay.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BaseFragment<out VM : ViewModel> : Fragment() {

    @get:LayoutRes
    abstract val layoutRes: Int
    abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return DataBindingUtil.inflate<ViewDataBinding>(inflater, layoutRes, container, false)
            .apply { setVariable(BR.viewModel, viewModel) }.root
    }

}