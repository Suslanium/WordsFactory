package com.suslanium.wordsfactory.data.database.model

import androidx.room.Embedded
import androidx.room.Relation
import com.suslanium.wordsfactory.data.database.model.entity.EtymologyEntity
import com.suslanium.wordsfactory.data.database.model.entity.WordEntity

data class WordWithEtymologies(
    @Embedded val word: WordEntity,
    @Relation(
        parentColumn = "wordId",
        entityColumn = "wordOwnerId",
        entity = EtymologyEntity::class
    )
    val etymologies: List<EtymologyWithMeanings>
)