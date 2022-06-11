package com.example.ccfinancegrouptask.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    abstract fun initDI()

    abstract fun bindView(inflater: LayoutInflater, container: ViewGroup?): View

    abstract fun initUI()

    abstract fun initObservers()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //initDI() TODO
        return bindView(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initUI() TODO
        //initObservers() TODO
    }
}