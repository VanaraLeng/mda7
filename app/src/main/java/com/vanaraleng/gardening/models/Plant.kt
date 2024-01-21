package com.vanaraleng.gardening.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plants")
data class Plant(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var name: String,
    var type: String,
    var wateringFrequency: Int,
    var plantingDate: String
)

