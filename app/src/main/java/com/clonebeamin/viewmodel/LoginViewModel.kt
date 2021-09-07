package com.clonebeamin.viewmodel

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.clonebeamin.Event
import com.clonebeamin.common.Common
import com.clonebeamin.model.User
import com.clonebeamin.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginViewModel : ViewModel() {
    companion object {
        const val TAG = "UserLoginViewModel"
    }

    private val apiService: ApiService = Common.getApiService
    private val compositeDisposable = CompositeDisposable()

    val user: MutableLiveData<User> = MutableLiveData<User>()
    val id = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    private val _message = MutableLiveData<Event<String>>()

    val message: LiveData<Event<String>>
        get() = _message

    init {
        id.value = ""
        password.value = ""
    }

    fun doLoginRequest() {
        if (id.value.isNullOrBlank()) {
            _message.value = Event("아이디를 확인해주세요.")
        } else if (password.value.isNullOrBlank()) {
            _message.value = Event("비밀번호를 확인해주세요.")
        } /*else if (!Patterns.EMAIL_ADDRESS.matcher(id.value!!).matches()) {
            _message.value = Event("이메일형식을 확인해주세요.")
        }*/ else {
            compositeDisposable.add(
                apiService.login(id.value, password.value)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { loginModel ->
                            if (loginModel != null) {
                                user.value = loginModel
                                user.value?.id = id.value
                                user.value?.password = password.value
                            }
                        }, {
                            _message.value = Event("$it")
                        })
            )
        }
        Log.e(TAG, "doLoginRequest: [id] ${id.value}\n[pw] ${password.value}")
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}