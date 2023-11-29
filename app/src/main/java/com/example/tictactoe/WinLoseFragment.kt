package com.example.tictactoe

import com.example.tictactoe.database.LeaderboardViewModel
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.text.toLowerCase
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.tictactoe.database.LeaderBoardData
import com.example.tictactoe.databinding.FragmentGameBinding
import com.example.tictactoe.databinding.FragmentWinLoseBinding


class WinLoseFragment : Fragment() {

    private lateinit var binding: FragmentWinLoseBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var winStatement:String = "wins"
       binding = DataBindingUtil.inflate(inflater,R.layout.fragment_win_lose,container,false)
        val arg1: String? = arguments?.getString("winner")
        if(arg1?.toLowerCase() == "you"){
            winStatement = "win"
        }

        binding.leaderboardhomescreen.setOnClickListener {
            it.findNavController().navigate(R.id.action_winLoseFragment_to_leaderboardFragment)
        }

        binding.win.text = "$arg1 $winStatement"
        return binding.root

    }


}