package com.donali.basketapp00084417.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.donali.basketapp00084417.database.entities.BasketMatch

@Dao
interface BasketMatchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(basketMatch: BasketMatch):Long

    @Query("select * from basket_match")
    fun getAll():LiveData<List<BasketMatch>>

    @Query("select * from basket_match")
    fun getLastMatchLive():LiveData<BasketMatch>

    @Query("delete from basket_match")
    suspend fun deleteAll()
}