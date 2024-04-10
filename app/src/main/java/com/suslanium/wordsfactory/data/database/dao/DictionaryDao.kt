package com.suslanium.wordsfactory.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.suslanium.wordsfactory.data.database.model.DefinitionEntity
import com.suslanium.wordsfactory.data.database.model.EtymologyEntity
import com.suslanium.wordsfactory.data.database.model.MeaningEntity
import com.suslanium.wordsfactory.data.database.model.WordEntity

@Dao
interface DictionaryDao {

    @Upsert
    suspend fun upsertDefinition(definitionEntity: DefinitionEntity): Long

    @Upsert
    suspend fun upsertMeaning(meaningEntity: MeaningEntity): Long

    @Upsert
    suspend fun upsertEtymology(etymologyEntity: EtymologyEntity): Long

    @Upsert
    suspend fun upsertWord(wordEntity: WordEntity): Long

    @Query("DELETE FROM words WHERE word = :word")
    suspend fun deleteWord(word: String)

}