package com.example.tictactoe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.tictactoe.databinding.FragmentHomeScreenBinding

class HomeScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentHomeScreenBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home_screen, container, false
        )

        binding.homeStartButton.setOnClickListener { view:View ->
            view.findNavController().navigate(R.id.action_homeScreenFragment_to_singleMultiplayerChooseScreen2)

        }

        binding.leaderboardhomescreen.setOnClickListener{
            it.findNavController().navigate(R.id.action_homeScreenFragment_to_leaderboardFragment)
        }
        return binding.root
    }
}
