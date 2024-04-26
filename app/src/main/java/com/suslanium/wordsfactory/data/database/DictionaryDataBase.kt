package com.suslanium.wordsfactory.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.suslanium.wordsfactory.data.database.dao.DictionaryDao
import com.suslanium.wordsfactory.data.database.model.entity.DefinitionEntity
import com.suslanium.wordsfactory.data.database.model.entity.EtymologyEntity
import com.suslanium.wordsfactory.data.database.model.entity.LearnCoefficient
import com.suslanium.wordsfactory.data.database.model.entity.MeaningEntity
import com.suslanium.wordsfactory.data.database.model.entity.WordEntity

@Database(
    entities = [WordEntity::class, EtymologyEntity::class, MeaningEntity::class, DefinitionEntity::class, LearnCoefficient::class],
    version = 1,
    exportSchema = false
)
abstract class DictionaryDataBase : RoomDatabase() {

    abstract fun dictionaryDao(): DictionaryDao

}