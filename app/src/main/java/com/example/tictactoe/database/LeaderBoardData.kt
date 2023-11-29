package com.example.tictactoe.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "leaderboard")
data class LeaderBoardData(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name="name")
    var name: String,
    @ColumnInfo(name="numberOfWins")
    var numberOfWins: Int
)
