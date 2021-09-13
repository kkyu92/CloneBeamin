package com.clonebeamin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.clonebeamin.base.BaseViewModel
import com.clonebeamin.data.Event
import com.clonebeamin.data.login.LoginInfo
import com.clonebeamin.data.login.LoginDataItem
import com.clonebeamin.network.ApiService
import com.clonebeamin.network.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginViewModel : BaseViewModel() {
    companion object {
        const val TAG = "UserLoginViewModel"
    }

    private val apiService: ApiService = RetrofitClient.getApiService
    private val compositeDisposable = CompositeDisposable()

    private val _message = MutableLiveData<Event<String>>()
    private val _loginData = MutableLiveData<LoginDataItem>()

    val message: LiveData<Event<String>> get() = _message
    val loginData: LiveData<LoginDataItem> get() = _loginData

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
                        .login(LoginInfo(id, password))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            _loginData.value = it
                        }, {
                            it.printStackTrace()
                            _message.value = Event("$it")
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