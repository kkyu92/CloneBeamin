package com.clonebeamin.view.mypage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.clonebeamin.R
import com.clonebeamin.databinding.FragmentLoginBinding
import com.clonebeamin.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {
    companion object {
        private const val TAG = "LoginFragment"
    }

    lateinit var binding: FragmentLoginBinding
    lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding.lifecycleOwner = this

        viewModel.message.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        })
        viewModel.token.observe(viewLifecycleOwner, {
            refresh.text = it.refresh
            access.text = it.access
            findNavController().navigateUp()
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login_button.setOnClickListener{
            viewModel.doLoginRequest(id_input.text.toString(), password_input.text.toString())
        }
        close_btn.setOnClickListener{
            findNavController().navigateUp()
        }
    }
}