package com.suslanium.wordsfactory.presentation.ui.screen.dictionary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.suslanium.wordsfactory.R
import com.suslanium.wordsfactory.domain.entity.dictionary.Definition
import com.suslanium.wordsfactory.domain.entity.dictionary.Meaning
import com.suslanium.wordsfactory.domain.entity.dictionary.WordEtymology
import com.suslanium.wordsfactory.presentation.ui.common.AppTextField
import com.suslanium.wordsfactory.presentation.ui.screen.dictionary.components.DictionaryWord
import com.suslanium.wordsfactory.presentation.ui.theme.Dark
import com.suslanium.wordsfactory.presentation.ui.theme.PaddingMedium

private val mockWord = listOf(
    WordEtymology(
        word = "Cooking", phonetic = "[ˈkʊkɪŋ]", audioUrl = "", meanings = listOf(
            Meaning(
                partOfSpeech = "Noun", definitions = listOf(
                    Definition(
                        definition = "The practice or skill of preparing food by combining, mixing, and heating ingredients.",
                        example = "he developed an interest in cooking."
                    ),
                    Definition(
                        definition = "The practice or skill of preparing food by combining, mixing, and heating ingredients.",
                        example = "he developed an interest in cooking."
                    ),
                )
            )
        )
    )
)

@Composable
fun DictionaryScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = PaddingMedium, end = PaddingMedium, top = 24.dp)
    ) {
        AppTextField(enabled = true, value = "", onValueChange = { }, trailingIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.search_icon),
                    contentDescription = null,
                    tint = Dark
                )
            }
        })

        DictionaryWord(wordEtymologies = mockWord)
    }
}