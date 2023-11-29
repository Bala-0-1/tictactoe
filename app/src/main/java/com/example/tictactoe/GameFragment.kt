package com.example.tictactoe

import com.example.tictactoe.database.LeaderboardViewModel
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tictactoe.databinding.FragmentGameBinding

class GameFragment : Fragment(), View.OnClickListener {

    private lateinit var playerTurnTextView: TextView
    private lateinit var resultTextView: TextView
    private lateinit var playerone: String
    private lateinit var playertwo: String
    private lateinit var buttons: List<ImageButton>
    private var currentPlayer: Int = 1
    private var gameBoard: Array<IntArray> = Array(3) { IntArray(3) }
    private lateinit var rootView: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         rootView =DataBindingUtil.inflate(inflater,R.layout.fragment_game,container,false)

        playerone = arguments?.getString("player1").toString()
        playertwo = arguments?.getString("player2").toString()
        playerTurnTextView = rootView.textView
        resultTextView = rootView.textView2

        buttons = listOf(
            rootView.button, rootView.button2,
            rootView.button3, rootView.button4,
            rootView.button5, rootView.button6,
            rootView.button7, rootView.button8,
            rootView.button9
        )

        buttons.forEach { button ->
            button.setOnClickListener(this)
        }

        initializeGameBoard()
        return rootView.root
    }

    private fun initializeGameBoard() {
        currentPlayer = 1
        playerTurnTextView.text = " $playerone\'s turn"
        resultTextView.text = ""

        for (i in 0..2) {
            for (j in 0..2) {
                gameBoard[i][j] = 0
            }
        }

        enableAllButtons(true)
    }

    override fun onClick(view: View?) {
        if (view is ImageButton) {
            val cellId = getCellId(view)

            if (gameBoard[cellId.first][cellId.second] == 0) {
                updateCell(view, cellId)
                checkGameStatus()
                togglePlayerTurn()
            }
        }
    }

    private fun getCellId(view: View): Pair<Int, Int> {
        val id = view.id
        val row = (id - R.id.button) / 3
        val col = (id - R.id.button) % 3
        return Pair(row, col)
    }

    private fun updateCell(button: ImageButton, cellId: Pair<Int, Int>) {
        val symbol = if (currentPlayer == 1) R.drawable.x else R.drawable.o
        button.setImageResource(symbol)
        gameBoard[cellId.first][cellId.second] = currentPlayer
    }

    private fun checkGameStatus() {
        if (checkForWinner()) {
            val winner:String = if(currentPlayer == 1) playerone else playertwo
            val loser: String = if(currentPlayer == 1) playertwo else playerone
            resultTextView.text = "$winner wins!"
            activity?.let { addRecord(it.getApplicationContext(),winner,true) }
            activity?.let { addRecord(it.applicationContext,loser,false) }
            this.findNavController().navigate(GameFragmentDirections.actionGameFragmentToWinLoseFragment(winner))
            enableAllButtons(false)
        } else if (isBoardFull()) {
            resultTextView.text = "It's a draw!"
            activity?.let { addRecord(it.getApplicationContext(),playerone,false) }
            activity?.let { addRecord(it.applicationContext,playertwo,false) }
            this.findNavController().navigate(GameFragmentDirections.actionGameFragmentToWinLoseFragment("No one"))
            enableAllButtons(false)
        }
    }

    private fun addRecord(applicationContext: Context, name: String, hasWon: Boolean) {
        var viewModel: LeaderboardViewModel = LeaderboardViewModel(applicationContext as Application)
        viewModel.insertRecord(name,hasWon)
    }

    private fun checkForWinner(): Boolean {
        // Check rows, columns, and diagonals for a winner
        for (i in 0..2) {
            if (gameBoard[i][0] == currentPlayer && gameBoard[i][1] == currentPlayer && gameBoard[i][2] == currentPlayer) {
                return true // Row win
            }
            if (gameBoard[0][i] == currentPlayer && gameBoard[1][i] == currentPlayer && gameBoard[2][i] == currentPlayer) {
                return true // Column win
            }
        }
        if (gameBoard[0][0] == currentPlayer && gameBoard[1][1] == currentPlayer && gameBoard[2][2] == currentPlayer) {
            return true // Diagonal win (top-left to bottom-right)
        }
        if (gameBoard[0][2] == currentPlayer && gameBoard[1][1] == currentPlayer && gameBoard[2][0] == currentPlayer) {
            return true // Diagonal win (top-right to bottom-left)
        }
        return false
    }

    private fun isBoardFull(): Boolean {
        for (i in 0..2) {
            for (j in 0..2) {
                if (gameBoard[i][j] == 0) {
                    return false // There is an empty cell
                }
            }
        }
        return true // Board is full
    }

    private fun togglePlayerTurn() {
        currentPlayer = if (currentPlayer == 1) 2 else 1
        val dispText: String = if(currentPlayer == 1) playerone else playertwo
        playerTurnTextView.text = "$dispText\'s turn"
    }

    private fun enableAllButtons(enable: Boolean) {
        buttons.forEach { button ->
            button.isEnabled = enable
        }
    }
}
