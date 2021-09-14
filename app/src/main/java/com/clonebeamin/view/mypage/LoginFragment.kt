package com.clonebeamin.view.mypage

import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.clonebeamin.R
import com.clonebeamin.base.BaseFragment
import com.clonebeamin.databinding.FragmentLoginBinding
import com.clonebeamin.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {
    private lateinit var viewModel: LoginViewModel

    override fun init() {
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        viewModel.message.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        })
        viewModel.loginData.observe(viewLifecycleOwner, {
            refresh.text = it.refresh
            access.text = it.access
            findNavController().popBackStack()
        })
        viewModel.isLoading.observe(viewLifecycleOwner, { isLoading ->
            if (isLoading) {
                showToast("isLoading: true")
            } else {
                showToast("isLoading: false")
            }
        })

        binding.closeBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.loginButton.setOnClickListener {
            viewModel.doLoginRequest(id_input.text.toString(), password_input.text.toString())
        }
    }

    companion object {
        private const val TAG = "LoginFragment"
    }
}