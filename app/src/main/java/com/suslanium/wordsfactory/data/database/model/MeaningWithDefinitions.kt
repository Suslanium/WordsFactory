package com.suslanium.wordsfactory.data.database.model

import androidx.room.Embedded
import androidx.room.Relation
import com.suslanium.wordsfactory.data.database.model.entity.DefinitionEntity
import com.suslanium.wordsfactory.data.database.model.entity.MeaningEntity

data class MeaningWithDefinitions(
    @Embedded val meaning: MeaningEntity,
    @Relation(
        parentColumn = "meaningId",
        entityColumn = "meaningOwnerId"
    )
    val definitions: List<DefinitionEntity>
)
