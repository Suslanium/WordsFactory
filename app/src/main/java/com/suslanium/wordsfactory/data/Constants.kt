package com.suslanium.wordsfactory.data

import java.util.Locale

object Constants {

    const val API_BASE_URL = "https://api.dictionaryapi.dev/api/v2/entries/en/"

    const val WORD_INFO_PATH_ARGUMENT = "word"
    const val WORD_INFO_URL = "{$WORD_INFO_PATH_ARGUMENT}"
    const val DB_NAME = "dictionary_db"
    const val PREFS_NAME = "dictionary_prefs"
    const val TIMESTAMP_KEY = "timestamp"

}

object Common {
    fun String.titleCase() = replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.ENGLISH
        ) else it.toString()
    }
}