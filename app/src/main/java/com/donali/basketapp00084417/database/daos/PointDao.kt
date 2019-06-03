package com.donali.basketapp00084417.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.donali.basketapp00084417.database.entities.Point


@Dao
interface PointDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(point: Point):Long

    @Query("select * from point")
    fun getAll(): LiveData<List<Point>>

    @Query("delete from point")
    suspend fun deleteAll()
}