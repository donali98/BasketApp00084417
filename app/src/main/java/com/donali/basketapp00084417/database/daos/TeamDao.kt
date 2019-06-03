package com.donali.basketapp00084417.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.donali.basketapp00084417.database.entities.Team


@Dao
interface TeamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(team:Team):Long

    @Query("select * from team where id = :id")
    fun getOne(id:Long):LiveData<List<Team>>

    @Query("select * from team")
    fun getAll():LiveData<List<Team>>

    @Query("select * from team where id!= :id")
    fun getAllExcept(id:Long):LiveData<List<Team>>

    @Query("delete from team")
    suspend fun deleteAll()


}