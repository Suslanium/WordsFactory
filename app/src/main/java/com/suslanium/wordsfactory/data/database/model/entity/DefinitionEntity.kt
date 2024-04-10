package com.suslanium.wordsfactory.data.database.model.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = MeaningEntity::class,
        parentColumns = ["meaningId"],
        childColumns = ["meaningOwnerId"],
        onDelete = CASCADE,
        onUpdate = CASCADE
    )], indices = [Index(value = ["meaningOwnerId"])]
)
data class DefinitionEntity(
    @PrimaryKey(autoGenerate = true) val definitionId: Long = 0,
    val meaningOwnerId: Long,
    val definition: String,
    val example: String?
)