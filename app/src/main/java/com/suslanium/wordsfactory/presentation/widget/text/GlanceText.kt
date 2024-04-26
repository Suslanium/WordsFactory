package com.suslanium.wordsfactory.presentation.widget.text

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.text.TextPaint
import android.util.TypedValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.content.res.ResourcesCompat
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box

@Composable
fun GlanceText(
    modifier: GlanceModifier = GlanceModifier,
    text: String,
    style: GlanceTextStyle,
    color: Color,
    textAlignment: Alignment = Alignment.CenterStart
) {
    Box(modifier = modifier, contentAlignment = textAlignment) {
        Image(
            provider = ImageProvider(
                LocalContext.current.customFontImage(text, color, style)
            ), contentDescription = null
        )
    }
}

private fun Float.spToPx(context: Context): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP, this, context.resources.displayMetrics
    )
}

private fun Context.customFontImage(
    text: String, color: Color, style: GlanceTextStyle
): Bitmap {
    val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = style.fontSize.value.spToPx(this@customFontImage)
        this.color = color.toArgb()
        letterSpacing = style.letterSpacing
        typeface = ResourcesCompat.getFont(this@customFontImage, style.font)
    }

    val baseline = -textPaint.ascent()
    val textWidth = textPaint.measureText(text).toInt()
    val textHeight = (baseline + textPaint.descent()).toInt()
    val bitmap = Bitmap.createBitmap(textWidth, textHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    canvas.drawText(text, 0f, baseline, textPaint)
    return bitmap
}