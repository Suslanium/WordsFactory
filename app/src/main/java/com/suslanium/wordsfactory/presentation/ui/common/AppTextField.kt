package com.suslanium.wordsfactory.presentation.ui.common

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.suslanium.wordsfactory.presentation.ui.theme.Dark
import com.suslanium.wordsfactory.presentation.ui.theme.DarkGray
import com.suslanium.wordsfactory.presentation.ui.theme.Error
import com.suslanium.wordsfactory.presentation.ui.theme.PaddingMedium
import com.suslanium.wordsfactory.presentation.ui.theme.ParagraphMedium
import com.suslanium.wordsfactory.presentation.ui.theme.TextFieldBorder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    placeHolder: String? = null,
    value: String,
    onValueChange: (String) -> Unit,
    trailingIcon: (@Composable () -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isError: Boolean = false,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        disabledContainerColor = Color.Transparent,
        unfocusedBorderColor = TextFieldBorder,
        focusedBorderColor = TextFieldBorder,
        disabledBorderColor = TextFieldBorder,
        focusedTextColor = DarkGray,
        unfocusedTextColor = DarkGray,
        disabledTextColor = DarkGray,
        focusedTrailingIconColor = Dark,
        unfocusedTrailingIconColor = Dark,
        disabledTrailingIconColor = Dark,
        errorContainerColor = Color.Transparent,
        errorTrailingIconColor = Error,
        errorBorderColor = Error,
        errorCursorColor = Error,
        cursorColor = TextFieldBorder
    )
) {
    val interactionSource = remember { MutableInteractionSource() }

    BasicTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        textStyle = ParagraphMedium,
        visualTransformation = visualTransformation,
        interactionSource = interactionSource,
        enabled = enabled,
        singleLine = true,
        cursorBrush = if (!isError) SolidColor(TextFieldBorder) else SolidColor(Error)
    ) { innerTextField ->
        OutlinedTextFieldDefaults.DecorationBox(
            value = value,
            colors = colors,
            innerTextField = innerTextField,
            enabled = enabled,
            singleLine = true,
            visualTransformation = visualTransformation,
            interactionSource = interactionSource,
            isError = isError,
            trailingIcon = trailingIcon,
            contentPadding = PaddingValues(all = PaddingMedium),
            placeholder = if (placeHolder != null) {
                {
                    Text(text = placeHolder, style = ParagraphMedium, color = DarkGray)
                }
            } else {
                null
            },
            container = {
                OutlinedTextFieldDefaults.ContainerBox(
                    enabled = enabled,
                    isError = isError,
                    interactionSource = interactionSource,
                    colors = colors,
                    shape = RoundedCornerShape(16.dp),
                    focusedBorderThickness = OutlinedTextFieldDefaults.FocusedBorderThickness,
                    unfocusedBorderThickness = OutlinedTextFieldDefaults.UnfocusedBorderThickness
                )
            },
        )
    }
}