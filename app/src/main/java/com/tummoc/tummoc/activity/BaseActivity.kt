package com.tummoc.tummoc.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseActivity<VB : ViewDataBinding, VM : ViewModel> : AppCompatActivity() {
    lateinit var mViewModel: VM
    lateinit var mBinding: VB
    abstract fun provideBindingVariable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDataBinding()
        setupView(savedInstanceState)
        setUpObservers()
        setUpListener()
    }

    @LayoutRes
    abstract fun provideLayoutId(): Int
    abstract fun setupView(savedInstanceState: Bundle?)
    abstract fun setUpObservers()
    abstract fun setUpListener()


    private fun setDataBinding() {
        mBinding = DataBindingUtil.setContentView(this, provideLayoutId())
        mBinding.lifecycleOwner = this
        provideBindingVariable()
        mBinding.executePendingBindings()
    }
}