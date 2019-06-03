package com.donali.basketapp00084417.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "result",
    foreignKeys = [
        ForeignKey(
            entity = BasketMatch::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("match_id")
        ),
        ForeignKey(
            entity = Team::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("winner_id")
        )
    ]
)
class Result (
    @ColumnInfo(name = "match_id")
    val match_id:Long,
    @ColumnInfo(name = "winner_id")
    val winner_id:Long
){
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0
}