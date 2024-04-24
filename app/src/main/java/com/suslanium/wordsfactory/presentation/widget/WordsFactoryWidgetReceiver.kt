package com.suslanium.wordsfactory.presentation.widget

import android.content.Context
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.updateAll
import kotlinx.coroutines.runBlocking

class WordsFactoryWidgetReceiver : GlanceAppWidgetReceiver() {

    override val glanceAppWidget: GlanceAppWidget = WordsFactoryWidget()

    override fun onEnabled(context: Context?) {
        super.onEnabled(context)
        runBlocking {
            if (context != null) {
                WordsFactoryWidget().updateAll(context)
            }
        }
    }
}