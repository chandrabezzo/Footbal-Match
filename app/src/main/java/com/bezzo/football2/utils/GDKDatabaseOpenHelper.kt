package com.bezzo.football2.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.bezzo.core.data.model.Event
import com.bezzo.core.data.model.Team
import com.bezzo.core.util.constanta.AppConstans
import org.jetbrains.anko.db.*

class GDKDatabaseOpenHelper(ctx : Context) : ManagedSQLiteOpenHelper(ctx, AppConstans.DB_NAME,
        null, 1) {

    companion object {
        private var instance: GDKDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): GDKDatabaseOpenHelper {
            if (instance == null) {
                instance = GDKDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as GDKDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(Event.TABLE_EVENT, true,
                Event.ID_EVENT to TEXT + PRIMARY_KEY,
                Event.EVENT_NAME to TEXT,
                Event.DATE_EVENT to TEXT,
                Event.ID_HOME_TEAM to TEXT,
                Event.HOME_TEAM_NAME to TEXT,
                Event.HOME_SCORE to TEXT,
                Event.HOME_GOAL_DETAILS to TEXT,
                Event.HOME_RED_CARDS to TEXT,
                Event.HOME_YELLOW_CARDS to TEXT,
                Event.HOME_GOALKEEPER to TEXT,
                Event.HOME_DEFENSE to TEXT,
                Event.HOME_MIDFIELD to TEXT,
                Event.HOME_FORWARD to TEXT,
                Event.HOME_SUBSTITUTES to TEXT,
                Event.HOME_FORMATION to TEXT,
                Event.ID_AWAY_TEAM to TEXT,
                Event.AWAY_TEAM_NAME to TEXT,
                Event.AWAY_SCORE to TEXT,
                Event.AWAY_GOAL_DETAILS to TEXT,
                Event.AWAY_RED_CARDS to TEXT,
                Event.AWAY_YELLOW_CARDS to TEXT,
                Event.AWAY_GOAL_KEEPER to TEXT,
                Event.AWAY_DEFENSE to TEXT,
                Event.AWAY_MIDFIELD to TEXT,
                Event.AWAY_FORWARD to TEXT,
                Event.AWAY_SUBTITUTES to TEXT,
                Event.AWAY_FORMATION to TEXT,
                Event.TIME_EVENT to TEXT)

        db?.createTable(Team.TABLE_TEAM, true,
                Team.ID_TEAM to TEXT + PRIMARY_KEY,
                Team.TEAM_NAME to TEXT,
                Team.FORMED_YEAR to TEXT,
                Team.LEAGUE_ID to TEXT,
                Team.STADIUM to TEXT,
                Team.DESCRIPTION to TEXT,
                Team.IMAGE_TEAM to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(Event.TABLE_EVENT, true)
        db?.dropTable(Team.TABLE_TEAM, true)
    }
}

val Context.database: GDKDatabaseOpenHelper
    get() = GDKDatabaseOpenHelper.getInstance(applicationContext)