package com.suslanium.wordsfactory.presentation.ui.screen.dictionary.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.suslanium.wordsfactory.R
import com.suslanium.wordsfactory.domain.entity.dictionary.WordEtymology
import com.suslanium.wordsfactory.presentation.ui.screen.dictionary.components.word.definitionCard
import com.suslanium.wordsfactory.presentation.ui.screen.dictionary.components.word.etymologyHeader
import com.suslanium.wordsfactory.presentation.ui.screen.dictionary.components.word.meaningPartOfSpeech
import com.suslanium.wordsfactory.presentation.ui.theme.Gray
import com.suslanium.wordsfactory.presentation.ui.theme.HeadingH5
import com.suslanium.wordsfactory.presentation.ui.theme.PaddingSmall

@Composable
fun DictionaryWord(wordEtymologies: List<WordEtymology>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = PaddingSmall)
    ) {
        wordEtymologies.forEachIndexed { index, wordEtymology ->
            etymologyHeader(wordEtymology)

            wordEtymology.meanings?.let { meanings ->
                meanings.forEach { meaning ->
                    meaningPartOfSpeech(meaning)

                    item {
                        Text(
                            modifier = Modifier.padding(top = PaddingSmall, bottom = 6.dp),
                            text = stringResource(id = R.string.meanings),
                            style = HeadingH5,
                            color = Color.Black
                        )
                    }

                    meaning.definitions?.let { definitions ->
                        definitions.forEach { definition ->
                            definitionCard(definition)
                        }
                    }
                }
            }

            if (index != wordEtymologies.lastIndex) {
                item {
                    HorizontalDivider(modifier = Modifier.padding(top = 8.dp, bottom = 5.dp), color = Gray)
                }
            }
        }
    }
}