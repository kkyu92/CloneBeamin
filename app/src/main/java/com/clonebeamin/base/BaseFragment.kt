package com.clonebeamin.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment<B : ViewDataBinding>(
    @LayoutRes private val layoutRes: Int
) : Fragment() {

    private val compositeDisposable = CompositeDisposable()
    private var _binding: B? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        compositeDisposable.clear()
        super.onDestroyView()
    }
    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}