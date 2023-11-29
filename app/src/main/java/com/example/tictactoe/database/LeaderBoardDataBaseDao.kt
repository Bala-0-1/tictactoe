package com.example.tictactoe.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface LeaderBoardDataBaseDao {
    @Insert
    fun insert(data: LeaderBoardData)

    @Update
    fun update(data: LeaderBoardData)

    @Query("select * from leaderboard order by numberOfWins desc")
    fun fetchAll(): LiveData<List<LeaderBoardData>>

    @Query("select * from leaderboard where name=:name")
    fun getRecordByName(name: String): LeaderBoardData

}