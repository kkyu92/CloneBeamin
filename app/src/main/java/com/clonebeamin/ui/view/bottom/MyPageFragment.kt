package com.clonebeamin.ui.view.bottom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.clonebeamin.R
import com.clonebeamin.databinding.FragmentMyPageBinding
import kotlinx.android.synthetic.main.fragment_my_page.*

class MyPageFragment : Fragment() {
    lateinit var binding: FragmentMyPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_page, container, false)

        binding.moveLogin.setOnClickListener{
            findNavController().navigate(R.id.action_myPageFragment_to_loginFragment)
        }
        return binding.root
    }
}