package com.donali.basketapp00084417.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.donali.basketapp00084417.database.entities.Result

@Dao
interface ResultDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(result:Result):Long

    @Query("select * from result")
    fun getAll(): LiveData<List<Result>>

    @Query("delete from result")
    suspend fun deleteAll()
}