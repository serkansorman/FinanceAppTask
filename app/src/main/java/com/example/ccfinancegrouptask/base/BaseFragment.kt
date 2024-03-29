package com.example.ccfinancegrouptask.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    abstract fun bindView(inflater: LayoutInflater, container: ViewGroup?): View

    abstract fun initUI()

    abstract fun initObservers()

    abstract fun fetchData()

    abstract fun startLoading()

    abstract fun endLoading()

    abstract fun showErrorHandlerLayout()

    abstract fun hideErrorHandlerLayout()

    abstract fun setOnErrorRefreshClick()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return bindView(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initObservers()
        fetchData()
    }
}