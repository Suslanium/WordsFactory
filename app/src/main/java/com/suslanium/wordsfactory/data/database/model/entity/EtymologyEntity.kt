package com.suslanium.wordsfactory.data.database.model.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = WordEntity::class,
        parentColumns = ["wordId"],
        childColumns = ["wordOwnerId"],
        onDelete = CASCADE
    )], indices = [Index(value = ["wordOwnerId"])]
)
data class EtymologyEntity(
    @PrimaryKey(autoGenerate = true) val etymologyId: Long = 0,
    val wordOwnerId: Long,
    val phonetic: String?,
    val audioUrl: String?
)