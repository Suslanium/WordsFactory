package com.suslanium.wordsfactory.data.database.model

import androidx.room.Embedded
import androidx.room.Relation
import com.suslanium.wordsfactory.data.database.model.entity.EtymologyEntity
import com.suslanium.wordsfactory.data.database.model.entity.MeaningEntity

data class EtymologyWithMeanings(
    @Embedded val etymology: EtymologyEntity,
    @Relation(
        parentColumn = "etymologyId",
        entityColumn = "etymologyOwnerId",
        entity = MeaningEntity::class
    )
    val meanings: List<MeaningWithDefinitions>
)