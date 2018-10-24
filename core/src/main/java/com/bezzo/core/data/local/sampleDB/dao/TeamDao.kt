package com.bezzo.core.data.local.sampleDB.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.bezzo.core.data.model.Team
import com.bezzo.core.data.model.TeamResponse
import com.bezzo.core.util.constanta.AppConstans
import io.reactivex.Flowable

@Dao
interface TeamDao {
    @Query("SELECT * FROM " + AppConstans.TEAM)
    fun getAll(): Flowable<List<Team>>

    @Query("SELECT * FROM " + AppConstans.TEAM
            + " LIMIT 1")
    fun get(): Flowable<Team>

    @Query("SELECT * FROM " + AppConstans.TEAM
            + " LIMIT :limit")
    fun getLimit(limit : Int): Flowable<Team>

    @Query("SELECT * FROM " + AppConstans.TEAM
            + " WHERE idTeam=:id")
    fun get(id: String): Flowable<Team>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(value : Team)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserts(values : List<Team>)

    @Query("DELETE FROM " + AppConstans.TEAM)
    fun deleteAll()

    @Query("DELETE FROM " + AppConstans.TEAM
            + " WHERE idTeam=:id")
    fun delete(id: String)

    @Query("SELECT COUNT(*) FROM " + AppConstans.TEAM)
    fun count(): Int
}