package com.example.tictactoe.database

import androidx.lifecycle.LiveData

class LeaderboardRepository(private val dao: LeaderBoardDataBaseDao) {

    val leaderboard: LiveData<List<LeaderBoardData>> = dao.fetchAll()
}
