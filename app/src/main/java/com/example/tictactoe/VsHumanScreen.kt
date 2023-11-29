package com.example.tictactoe

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.tictactoe.databinding.FragmentVsHumanScreenBinding


class VsHumanScreen : Fragment() {

    lateinit var binding: FragmentVsHumanScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_vs_human_screen, container, false)

        // Set up click listener for the button
        binding.humanPlayButton.setOnClickListener { view: View ->
            // Read the player names when the button is clicked
            val playerone = binding.player1.text.toString()
            val playertwo = binding.player2.text.toString()
            // Navigate to the GameFragment with player names as arguments
            view.findNavController().navigate(VsHumanScreenDirections.actionVsHumanScreenToGameFragment(playerone, playertwo))
        }

        return binding.root
    }
}
