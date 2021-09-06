package com.clonebeamin.view.mypage

import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.ViewModelProvider
import com.clonebeamin.R
import com.clonebeamin.base.BaseFragment
import com.clonebeamin.databinding.FragmentLoginBinding
import com.clonebeamin.model.UserLoginDetails
import com.clonebeamin.viewmodel.UserLoginViewModel

class LoginFragment : BaseFragment<FragmentLoginBinding, UserLoginViewModel>() {

    override fun getViewModel(): UserLoginViewModel {
        return ViewModelProvider(this).get(UserLoginViewModel::class.java)
    }

    override fun getBindingVariable(): Int = BR.loginViewModel


    override fun init() {
        mViewDataBinding?.userDetails = UserLoginDetails()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_login
    }

}