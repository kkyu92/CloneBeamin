package com.clonebeamin.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.clonebeamin.Event
import com.clonebeamin.common.Common
import com.clonebeamin.model.request.LoginRequest
import com.clonebeamin.model.response.LoginResponse
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

    private val _message = MutableLiveData<Event<String>>()
    private val _token = MutableLiveData<LoginResponse>()

    val message: LiveData<Event<String>> get() = _message
    val token: LiveData<LoginResponse> get() = _token

    fun doLoginRequest(id: String, password: String) {
        when {
            // postValue = background 에서 값 변경
            // setValue = mainThread 에서 값 변경
            id.isBlank() -> {
                _message.postValue(Event("아이디를 확인해주세요."))
            }
            password.isBlank() -> {
                _message.postValue(Event("비밀번호를 확인해주세요."))
            }
//            !Patterns.EMAIL_ADDRESS.matcher(id).matches() -> {
//                _message.postValue(Event("이메일형식을 확인해주세요."))
//            }
            else -> {
                compositeDisposable.add(
                    apiService
                        .login(LoginRequest(id, password))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            _token.postValue(it)
                        }, {
                            it.printStackTrace()
                            _message.postValue(Event("$it"))
                        })
                )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}