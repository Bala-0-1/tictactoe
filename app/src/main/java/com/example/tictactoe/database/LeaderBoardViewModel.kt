package com.example.tictactoe.database

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.tictactoe.database.LeaderBoardData
import com.example.tictactoe.database.LeaderBoardDataBase
import com.example.tictactoe.database.LeaderBoardDataBaseDao
import com.example.tictactoe.database.LeaderboardRepository
import kotlinx.coroutines.launch

class LeaderboardViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: LeaderboardRepository
    val leaderboard: LiveData<List<LeaderBoardData>>
    var dao: LeaderBoardDataBaseDao

    init {
        dao = LeaderBoardDataBase.getInstance(application).leaderboardDao
        repository = LeaderboardRepository(dao)
        leaderboard = repository.leaderboard
        Log.i("LeaderBoardViewModel", leaderboard.toString())
    }

    fun insertRecord(name: String,hasWon: Boolean) {
        val num = if(hasWon) 1 else 0
        viewModelScope.launch {
            var existingRecord = dao.getRecordByName(name)

            if (existingRecord != null && hasWon) {
                existingRecord.numberOfWins = existingRecord.numberOfWins+1
                dao.update(existingRecord)
            }
            else if (existingRecord != null && !hasWon) {
                dao.update(existingRecord)
            }

            else {
                // Insert a new record if it doesn't exist
                val leaderboardItem = LeaderBoardData(0, name, num)
                dao.insert(leaderboardItem)
            }
        }
    }
}

