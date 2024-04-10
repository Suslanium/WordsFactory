package com.suslanium.wordsfactory.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.suslanium.wordsfactory.data.database.model.WordWithEtymologies
import com.suslanium.wordsfactory.data.database.model.entity.DefinitionEntity
import com.suslanium.wordsfactory.data.database.model.entity.EtymologyEntity
import com.suslanium.wordsfactory.data.database.model.entity.MeaningEntity
import com.suslanium.wordsfactory.data.database.model.entity.WordEntity

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

    @Transaction
    @Query("SELECT * FROM words WHERE word = :word")
    suspend fun getWord(word: String): WordWithEtymologies?

    @Query("SELECT EXISTS (SELECT 1 FROM words WHERE word = :word)")
    suspend fun isWordInDictionary(word: String): Boolean
}