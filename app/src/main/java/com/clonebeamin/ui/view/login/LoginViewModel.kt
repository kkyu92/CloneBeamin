package com.clonebeamin.ui.view.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.clonebeamin.ui.base.BaseViewModel
import com.clonebeamin.ui.utill.Event
import com.clonebeamin.data.remote.login.LoginDataItem
import com.clonebeamin.data.remote.login.LoginInfo
import com.clonebeamin.data.remote.retrofit.RetrofitApi
import com.clonebeamin.data.remote.retrofit.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginViewModel : BaseViewModel() {
    private val retrofitApi: RetrofitApi = RetrofitClient.loginAPI

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
                    retrofitApi
                        .login(LoginInfo(id, password))
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe { showProgress() }
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSuccess { hideProgress() }
                        .subscribe({
                            _loginData.postValue(it)
                        }, {
                            it.printStackTrace()
                            _message.postValue(Event("$it"))
                        })
                )
            }
        }
    }

    companion object {
        private const val TAG = "LoginViewModel"
    }
}