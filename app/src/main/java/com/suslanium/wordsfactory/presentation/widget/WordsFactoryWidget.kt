package com.suslanium.wordsfactory.presentation.widget

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import com.suslanium.wordsfactory.R
import com.suslanium.wordsfactory.presentation.ui.theme.Black50
import com.suslanium.wordsfactory.presentation.ui.theme.GlanceHeadingH3
import com.suslanium.wordsfactory.presentation.ui.theme.GlanceHeadingH5
import com.suslanium.wordsfactory.presentation.ui.theme.GlanceParagraphMedium
import com.suslanium.wordsfactory.presentation.ui.theme.PaddingMedium
import com.suslanium.wordsfactory.presentation.widget.text.GlanceText

class WordsFactoryWidget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {

            Column(
                modifier = GlanceModifier.background(ImageProvider(R.drawable.widget_background)).fillMaxSize()
            ) {
                Box(
                    modifier = GlanceModifier.defaultWeight().fillMaxWidth()
                        .background(ImageProvider(R.drawable.widget_top_gradient))
                        .padding(horizontal = PaddingMedium),
                    contentAlignment = Alignment.CenterStart
                ) {
                    GlanceText(
                        text = LocalContext.current.getString(R.string.app_name),
                        style = GlanceHeadingH3,
                        color = Color.White
                    )
                }
                Box(
                    modifier = GlanceModifier.defaultWeight().padding(horizontal = PaddingMedium),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Row(modifier = GlanceModifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {
                        GlanceText(
                            modifier = GlanceModifier.defaultWeight(),
                            text = LocalContext.current.getString(R.string.my_dictionary),
                            style = GlanceHeadingH5,
                            color = Color.Black
                        )
                        GlanceText(
                            text = LocalContext.current.getString(
                                R.string.words_template,
                                3125
                            ), style = GlanceParagraphMedium, color = Black50
                        )
                    }
                }
                Box(
                    modifier = GlanceModifier.defaultWeight().padding(horizontal = PaddingMedium),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Row(modifier = GlanceModifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {
                        GlanceText(
                            modifier = GlanceModifier.defaultWeight(),
                            text = LocalContext.current.getString(R.string.i_already_remember),
                            style = GlanceHeadingH5,
                            color = Color.Black
                        )
                        GlanceText(
                            text = LocalContext.current.getString(
                                R.string.words_template,
                                41
                            ), style = GlanceParagraphMedium, color = Black50
                        )
                    }
                }
            }
        }
    }
}