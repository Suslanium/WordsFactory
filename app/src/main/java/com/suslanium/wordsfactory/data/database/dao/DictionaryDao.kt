package com.suslanium.wordsfactory.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.suslanium.wordsfactory.data.database.model.TestQuestion
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

    @Query("UPDATE learn_coefficient SET coefficient = coefficient + 1 WHERE word = :word")
    suspend fun incrementWordCoefficient(word: String)

    @Query("UPDATE learn_coefficient SET coefficient = coefficient - 1 WHERE word = :word")
    suspend fun decrementWordCoefficient(word: String)

    @Query(
        """
    SELECT 
        correctAnswer, 
        correctAnswerDefinition, 
        firstIncorrectAnswer, 
        (SELECT c3.word 
         FROM learn_coefficient c3 
         WHERE c3.word <> correctAnswer AND c3.word <> firstIncorrectAnswer 
         ORDER BY RANDOM() 
         LIMIT 1) as secondIncorrectAnswer 
    FROM (
        SELECT 
            c.word AS correctAnswer, 
            (SELECT d.definition 
             FROM words w 
             JOIN etymologyentity e ON e.wordOwnerId = w.wordId AND w.word = c.word 
             JOIN meaningentity m ON m.etymologyOwnerId = e.etymologyId 
             JOIN definitionentity d ON d.meaningOwnerId = m.meaningId 
             ORDER BY RANDOM() 
             LIMIT 1) as correctAnswerDefinition, 
            (SELECT c2.word 
             FROM learn_coefficient c2 
             WHERE c2.word <> c.word 
             ORDER BY RANDOM() 
             LIMIT 1) as firstIncorrectAnswer 
        FROM learn_coefficient c
        WHERE c.word IN (SELECT w2.word FROM words w2)
        ORDER BY c.coefficient ASC 
        LIMIT 10
    )
"""
    )
    suspend fun getTestQuestions(): List<TestQuestion>

    @Query("SELECT COUNT(*) FROM learn_coefficient c WHERE c.coefficient > 5 AND c.word IN (SELECT w.word FROM words w)")
    suspend fun getLearnedWordsCount(): Int

}