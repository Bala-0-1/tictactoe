// LeaderboardFragment.kt

package com.example.tictactoe.database

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tictactoe.R
import com.example.tictactoe.databinding.FragmentLeaderBoardBinding


class LeaderBoard : Fragment() {

    private lateinit var leaderboardViewModel: LeaderboardViewModel

    private  lateinit var binding: FragmentLeaderBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_leader_board, container, false)
        val rootView = binding.root

        leaderboardViewModel = ViewModelProvider(this)[LeaderboardViewModel::class.java]

        val leaderboardContainer: LinearLayout = rootView.findViewById(R.id.leaderboardContainer)

        leaderboardViewModel.leaderboard.observe(viewLifecycleOwner, Observer { leaderboard ->
            leaderboardContainer.removeAllViews() // Clear previous views
            if(leaderboard.isEmpty()){
                binding.leaderboardtext.text = "Nothing to see here "
            }
            else {
                for (entry in leaderboard) {
                    val itemView =
                        LayoutInflater.from(context).inflate(R.layout.leaderboard_item, null)

                    val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
                    val pointsTextView: TextView = itemView.findViewById(R.id.pointsTextView)

                    nameTextView.text = entry.name
                    pointsTextView.text = entry.numberOfWins.toString()

                    leaderboardContainer.addView(itemView)
                }
            }
        })

        return rootView
    }
}
