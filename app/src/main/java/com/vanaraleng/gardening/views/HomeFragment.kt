package com.vanaraleng.gardening.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.vanaraleng.gardening.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            button.setOnClickListener {
                // Go to garden log
                val direction =
                    HomeFragmentDirections.actionHomeFragmentToLogFragment()
                findNavController().navigate(direction)
            }
        }

    }
}