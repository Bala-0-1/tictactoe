package com.example.tictactoe.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase




@Database(entities = [LeaderBoardData::class], version = 1,  exportSchema = false)
abstract class LeaderBoardDataBase : RoomDatabase(){

    abstract val leaderboardDao: LeaderBoardDataBaseDao

    companion object {
        @Volatile
        private var INSTANCE: LeaderBoardDataBase? = null

        fun getInstance(context: Context): LeaderBoardDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LeaderBoardDataBase::class.java,
                        "leaderboard"
                    ).allowMainThreadQueries().fallbackToDestructiveMigration().build()

                }
                return instance


            }



        }

    }

}