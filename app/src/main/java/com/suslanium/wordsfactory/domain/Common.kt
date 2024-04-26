package com.suslanium.wordsfactory.domain

import java.util.Locale

object Common {

    fun String.titleCase() = replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.ENGLISH
        ) else it.toString()
    }

}