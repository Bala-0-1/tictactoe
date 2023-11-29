package com.example.tictactoe

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
import com.example.tictactoe.databinding.FragmentAiGameBinding
import kotlin.random.Random

class AiGame : Fragment(), View.OnClickListener {

    private lateinit var playerTurnTextView: TextView
    private lateinit var resultTextView: TextView
    private lateinit var buttons: List<ImageButton>
    private var currentPlayer: Int = 1
    private var gameBoard: Array<IntArray> = Array(3) { IntArray(3) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: FragmentAiGameBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_ai_game, container, false)

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
        playerTurnTextView.text = "Player $currentPlayer's turn"
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
                enableAllButtons(false)
                Log.i("AiGame",isBoardGonnaCreateBug().toString())
                if(!isBoardGonnaCreateBug() && !checkForWinner()) {
                    performAIMove()
                    checkGameStatus()

                    enableAllButtons(true)
                    togglePlayerTurn()
                }



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
            resultTextView.text = "Player $currentPlayer wins!"
            val winner: String = if(currentPlayer == 1) "You" else "Computer"
            this.findNavController().navigate(AiGameDirections.actionAiGameToWinLoseFragment(winner))
            enableAllButtons(false)
        } else if (isBoardFull()) {
            resultTextView.text = "It's a draw!"
            this.findNavController().navigate(AiGameDirections.actionAiGameToWinLoseFragment("No one"))
            enableAllButtons(false)
        }
    }

    private fun checkForWinner(): Boolean {

        for (i in 0..2) {
            if (gameBoard[i][0] == currentPlayer && gameBoard[i][1] == currentPlayer && gameBoard[i][2] == currentPlayer) {
                return true
            }
            if (gameBoard[0][i] == currentPlayer && gameBoard[1][i] == currentPlayer && gameBoard[2][i] == currentPlayer) {
                return true
            }
        }
        if (gameBoard[0][0] == currentPlayer && gameBoard[1][1] == currentPlayer && gameBoard[2][2] == currentPlayer) {
            return true
        }
        if (gameBoard[0][2] == currentPlayer && gameBoard[1][1] == currentPlayer && gameBoard[2][0] == currentPlayer) {
            return true
        }
        return false
    }

    private fun isBoardGonnaCreateBug(): Boolean{
        var count = 0;
        for (i in 0..2) {
            for (j in 0..2) {
                if (gameBoard[i][j] == 0) {
                    count += 1
                }
            }
        }
        Log.i("AiGame",count.toString())
        return count == 0
    }
    private fun isBoardFull(): Boolean {
        for (i in 0..2) {
            for (j in 0..2) {
                if (gameBoard[i][j] == 0) {
                    return false
                }
            }
        }
        return true // Board is full
    }

    private fun togglePlayerTurn() {
        currentPlayer = if (currentPlayer == 1) 2 else 1
        playerTurnTextView.text = "Player $currentPlayer's turn"
    }

    private fun enableAllButtons(enable: Boolean) {
        buttons.forEach { button ->
            button.isEnabled = enable
        }
    }

    private fun performAIMove() {

        val emptyCells = mutableListOf<Pair<Int, Int>>()

        for (i in 0..2) {
            for (j in 0..2) {
                if (gameBoard[i][j] == 0) {
                    emptyCells.add(Pair(i, j))
                }
            }
        }

        if (emptyCells.isNotEmpty()) {
            val randomCell = emptyCells[Random.nextInt(emptyCells.size)]
            val aiButton = buttons[randomCell.first * 3 + randomCell.second]
            updateCell(aiButton, randomCell)
        }
    }
}
