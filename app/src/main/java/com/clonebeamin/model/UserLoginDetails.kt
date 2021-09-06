package com.clonebeamin.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

class UserLoginDetails : BaseObservable() {

    var mobileNumber: String? = null
        @Bindable get
        set(mobileNumber) {
            field = mobileNumber
            notifyPropertyChanged(BR.mobileNumber)
        }

    var password: String? = null
        @Bindable get
        set(password) {
            field = password
            notifyPropertyChanged(BR.password)
        }

}