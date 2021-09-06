package com.clonebeamin.viewmodel

import androidx.databinding.Observable
import com.clonebeamin.base.BaseViewModel
import com.clonebeamin.model.UserLoginDetails

class UserLoginViewModel : BaseViewModel() {

    fun doLoginRequest(userLoginDetails: UserLoginDetails?) {
        println("Mobile Number : " + userLoginDetails?.mobileNumber)
        println("Password : " + userLoginDetails?.password)
    }

    fun handleSuccessResponse() {

    }

    fun handleFailureResponse() {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

}