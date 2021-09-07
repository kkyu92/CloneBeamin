package com.clonebeamin.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.clonebeamin.model.User
import com.clonebeamin.network.ApiClient
import com.clonebeamin.network.LoginModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginViewModel : ViewModel() {
    companion object {
        private const val TAG = "UserLoginViewModel"
    }
    private val compositeDisposable = CompositeDisposable()
    var login: MutableLiveData<LoginModel> = MutableLiveData()

    fun loginObserver(): MutableLiveData<LoginModel> {
        return login
    }

    fun makeApiCall(id: String, password: String) {
        compositeDisposable.add(
            ApiClient.getApi().login(id, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.e(TAG, "makeApiCall: access - ${it.access}\nrefresh - ${it.refresh}", )
                    login.value?.access = it.access
                    login.value?.refresh = it.refresh
                },{
                    Log.e(TAG, "makeApiCall: error - $it")
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}