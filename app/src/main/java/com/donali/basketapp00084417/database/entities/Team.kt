package com.donali.basketapp00084417.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "team")
data class Team(
    @ColumnInfo(name = "name")
    var name:String
) {
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0
}