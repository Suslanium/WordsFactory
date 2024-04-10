package com.suslanium.wordsfactory.data.database.model.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = EtymologyEntity::class,
        parentColumns = ["etymologyId"],
        childColumns = ["etymologyOwnerId"],
        onDelete = CASCADE
    )], indices = [Index(value = ["etymologyOwnerId"])]
)
data class MeaningEntity(
    @PrimaryKey(autoGenerate = true) val meaningId: Long = 0,
    val etymologyOwnerId: Long,
    val partOfSpeech: String
)
