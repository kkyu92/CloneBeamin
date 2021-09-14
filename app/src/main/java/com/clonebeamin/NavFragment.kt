package com.clonebeamin

import androidx.navigation.fragment.findNavController
import com.clonebeamin.base.BaseFragment
import com.clonebeamin.databinding.FragmentNavBinding

class NavFragment : BaseFragment<FragmentNavBinding>(R.layout.fragment_nav) {

    override fun init() {
        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.searchFragment -> {
                    findNavController().navigate(R.id.action_navFragment_to_searchFragment)
                }
                R.id.favoriteFragment -> {
                    findNavController().navigate(R.id.action_navFragment_to_favoriteFragment)
                }
                R.id.orderListFragment -> {
                    findNavController().navigate(R.id.action_navFragment_to_orderListFragment)
                }
                R.id.myPageFragment -> {
                    findNavController().navigate(R.id.action_navFragment_to_myPageFragment)
                }
            }
            true
        }
    }

    companion object {
        private const val TAG = "NavFragment"
    }
}