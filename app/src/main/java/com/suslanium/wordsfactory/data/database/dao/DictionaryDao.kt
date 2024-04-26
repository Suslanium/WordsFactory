package com.suslanium.wordsfactory.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.suslanium.wordsfactory.data.database.model.WordWithCoefficient
import com.suslanium.wordsfactory.data.database.model.WordWithEtymologies
import com.suslanium.wordsfactory.data.database.model.entity.DefinitionEntity
import com.suslanium.wordsfactory.data.database.model.entity.EtymologyEntity
import com.suslanium.wordsfactory.data.database.model.entity.LearnCoefficient
import com.suslanium.wordsfactory.data.database.model.entity.MeaningEntity
import com.suslanium.wordsfactory.data.database.model.entity.WordEntity
import kotlinx.coroutines.flow.Flow

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

    @Query("SELECT COUNT(*) FROM words")
    fun getDictionaryWordsCount(): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWordCoefficient(wordCoefficient: LearnCoefficient)

    @Query("UPDATE learn_coefficient SET coefficient = :coefficient WHERE word = :word")
    suspend fun setWordCoefficient(word: String, coefficient: Int)

    @Query("SELECT word FROM words WHERE word NOT IN (:words) ORDER BY RANDOM() LIMIT 10")
    suspend fun getRandomWordsExcept(words: List<String>): List<String>

    @Transaction
    @Query("SELECT * FROM words w JOIN learn_coefficient lc ON w.word = lc.word ORDER BY (SELECT c.coefficient FROM learn_coefficient c WHERE c.word = w.word) ASC LIMIT 10")
    suspend fun getLeastLearntWords(): List<WordWithCoefficient>

    @Query("SELECT COUNT(*) FROM learn_coefficient c WHERE c.coefficient > 5 AND c.word IN (SELECT w.word FROM words w)")
    fun getLearntWordsCount(): Flow<Int>

}