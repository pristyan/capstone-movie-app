package com.aoc.core.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aoc.core.base.viewmodel.BaseViewModel
import com.aoc.core.base.viewmodel.BaseViewModelContract


/**
 * Created by Chandra.
 **/

abstract class BaseFragment<VM : BaseViewModel, VMC : BaseViewModelContract, VB : ViewDataBinding> :
    Fragment() {

    private var _binding: VB? = null
    val binding: VB
        get() = _binding as VB

    private var _viewModel: VM? = null

    @Suppress("UNCHECKED_CAST")
    val viewModel: VMC
        get() = _viewModel as VMC

    abstract var viewModelProviderFactory: ViewModelProvider.Factory

    abstract fun getLayoutId(): Int

    abstract fun getViewModelClass(): Class<VM>

    abstract fun getViewModelFactory(): ViewModelProvider.Factory

    abstract fun subscribeLiveData()

    abstract fun setupDependencyInjection()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        _binding?.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeLiveData()
    }

    override fun onAttach(context: Context) {
        setupDependencyInjection()
        super.onAttach(context)
        _viewModel = ViewModelProvider(this, getViewModelFactory()).get(getViewModelClass())
    }

    override fun onDetach() {
        _viewModel = null
        super.onDetach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}