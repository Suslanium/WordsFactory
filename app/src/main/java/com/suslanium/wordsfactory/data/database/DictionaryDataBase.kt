package com.suslanium.wordsfactory.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.suslanium.wordsfactory.data.database.dao.DictionaryDao
import com.suslanium.wordsfactory.data.database.model.DefinitionEntity
import com.suslanium.wordsfactory.data.database.model.EtymologyEntity
import com.suslanium.wordsfactory.data.database.model.MeaningEntity
import com.suslanium.wordsfactory.data.database.model.WordEntity

@Database(
    entities = [WordEntity::class, EtymologyEntity::class, MeaningEntity::class, DefinitionEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DictionaryDataBase : RoomDatabase() {

    abstract fun dictionaryDao(): DictionaryDao

}