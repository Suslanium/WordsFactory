package com.suslanium.wordsfactory.data.database.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "learn_coefficient")
data class LearnCoefficient(
    @PrimaryKey val word: String,
    val coefficient: Long = 0
)
