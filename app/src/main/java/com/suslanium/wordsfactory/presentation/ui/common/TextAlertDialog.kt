package com.suslanium.wordsfactory.presentation.ui.common

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.suslanium.wordsfactory.presentation.ui.theme.ButtonSmall
import com.suslanium.wordsfactory.presentation.ui.theme.Dark
import com.suslanium.wordsfactory.presentation.ui.theme.DarkGray
import com.suslanium.wordsfactory.presentation.ui.theme.HeadingH4
import com.suslanium.wordsfactory.presentation.ui.theme.ParagraphMedium
import com.suslanium.wordsfactory.presentation.ui.theme.PrimaryColor

@Composable
fun TextAlertDialog(
    title: String, message: String, acceptButtonText: String, onAccept: () -> Unit
) {
    AlertDialog(containerColor = Color.White, onDismissRequest = onAccept, title = {
        Text(text = title, style = HeadingH4, color = Dark)
    }, text = {
        Text(text = message, style = ParagraphMedium, color = DarkGray)
    }, confirmButton = {
        Button(
            onClick = onAccept, colors = ButtonDefaults.buttonColors(
                contentColor = Color.White, containerColor = PrimaryColor
            )
        ) {
            Text(text = acceptButtonText, style = ButtonSmall)
        }
    })
}