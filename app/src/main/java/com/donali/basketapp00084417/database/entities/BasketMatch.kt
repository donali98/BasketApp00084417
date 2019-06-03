package com.donali.basketapp00084417.database.entities

import androidx.room.*
import com.donali.basketapp00084417.database.converters.TimeStampConverter
import java.util.*

@Entity(
    tableName = "basket_match",
    foreignKeys = [
        ForeignKey(
            entity = Team::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("id_team_1")
        ),
        ForeignKey(
            entity = Team::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("id_team_2")
        )
    ]
)
data class BasketMatch (
    @ColumnInfo(name = "id_team_1")
    val id_team_1:Long,
    @ColumnInfo(name = "id_team_2")
    val id_team_2:Long,
    @ColumnInfo(name = "end_date")
    @TypeConverters(TimeStampConverter::class)
    val end_date:Date
){
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0
}