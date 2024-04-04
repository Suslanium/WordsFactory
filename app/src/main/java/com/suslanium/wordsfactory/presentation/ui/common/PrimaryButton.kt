package com.suslanium.wordsfactory.presentation.ui.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.suslanium.wordsfactory.presentation.ui.theme.ButtonMedium
import com.suslanium.wordsfactory.presentation.ui.theme.PaddingMedium
import com.suslanium.wordsfactory.presentation.ui.theme.PaddingSmall
import com.suslanium.wordsfactory.presentation.ui.theme.PrimaryColor

@Composable
fun PrimaryButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit = {}, enabled: Boolean = true, isLoading: Boolean = false) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .alpha(if (enabled) 1f else 0.7f),
        enabled = enabled,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = PrimaryColor,
            disabledContentColor = Color.White,
            disabledContainerColor = PrimaryColor,
        ),
        onClick = onClick
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(PaddingMedium),
                color = Color.White
            )
            Spacer(modifier = Modifier.width(PaddingSmall))
        }
        Text(text = text, style = ButtonMedium)
    }
}