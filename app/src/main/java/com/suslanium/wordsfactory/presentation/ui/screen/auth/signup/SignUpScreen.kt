package com.suslanium.wordsfactory.presentation.ui.screen.auth.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.suslanium.wordsfactory.R
import com.suslanium.wordsfactory.presentation.state.AuthEvent
import com.suslanium.wordsfactory.presentation.state.AuthState
import com.suslanium.wordsfactory.presentation.ui.common.AppTextField
import com.suslanium.wordsfactory.presentation.ui.common.ObserveAsEvents
import com.suslanium.wordsfactory.presentation.ui.common.PrimaryButton
import com.suslanium.wordsfactory.presentation.ui.common.TextAlertDialog
import com.suslanium.wordsfactory.presentation.ui.theme.Dark
import com.suslanium.wordsfactory.presentation.ui.theme.DarkGray
import com.suslanium.wordsfactory.presentation.ui.theme.HeadingH4
import com.suslanium.wordsfactory.presentation.ui.theme.PaddingMedium
import com.suslanium.wordsfactory.presentation.ui.theme.PaddingSmall
import com.suslanium.wordsfactory.presentation.ui.theme.ParagraphMedium
import com.suslanium.wordsfactory.presentation.ui.theme.PrimaryColor
import com.suslanium.wordsfactory.presentation.viewmodel.SignUpViewModel
import org.koin.androidx.compose.koinViewModel

private const val AUTH_TAG = "auth"

@Composable
fun SignUpScreen(onNavigateToSignIn: () -> Unit, onNavigateToNext: () -> Unit = {}) {
    val viewModel: SignUpViewModel = koinViewModel()
    val formData by remember { viewModel.formData }
    val screenState by remember { viewModel.screenState }
    val signUpFormIsCorrectlyFilled by remember { viewModel.signUpFormIsCorrectlyFilled }

    val bottomHint = buildAnnotatedString {
        withStyle(
            style = SpanStyle(color = DarkGray)
        ) {
            append(stringResource(id = R.string.already_have_an_account))
            append(" ")
        }

        pushStringAnnotation(AUTH_TAG, AUTH_TAG)
        withStyle(style = SpanStyle(color = PrimaryColor)) {
            append(stringResource(id = R.string.sign_in))
        }
        pop()
    }

    ObserveAsEvents(flow = viewModel.authEvents) {
        when (it) {
            AuthEvent.Success -> onNavigateToNext()
        }
    }

    formData.errorMessageId?.let { errorMessageId ->
        TextAlertDialog(
            title = stringResource(id = R.string.error_title),
            message = stringResource(id = errorMessageId),
            acceptButtonText = stringResource(id = R.string.ok),
            onAccept = viewModel::consumeErrorMessage
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = PaddingMedium)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(253.dp),
            painter = painterResource(id = R.drawable.sign_up),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(PaddingMedium))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.sign_up),
            style = HeadingH4,
            color = Dark,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(PaddingSmall))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.create_your_account),
            style = ParagraphMedium,
            color = DarkGray,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(PaddingMedium))
        AppTextField(
            enabled = screenState !is AuthState.Loading,
            placeHolder = stringResource(id = R.string.name),
            value = formData.name,
            onValueChange = viewModel::setName,
            isError = formData.nameValidationErrorType != null
        )
        Spacer(modifier = Modifier.height(PaddingMedium))
        AppTextField(
            enabled = screenState !is AuthState.Loading,
            placeHolder = stringResource(id = R.string.email),
            value = formData.email,
            onValueChange = viewModel::setEmail,
            isError = formData.emailValidationErrorType != null
        )
        Spacer(modifier = Modifier.height(PaddingMedium))
        AppTextField(
            enabled = screenState !is AuthState.Loading,
            placeHolder = stringResource(id = R.string.password),
            value = formData.password,
            onValueChange = viewModel::setPassword,
            trailingIcon = {
                IconButton(
                    modifier = Modifier.size(24.dp), onClick = viewModel::changePasswordVisibility
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = if (formData.isPasswordVisible) R.drawable.eye_icon_hidden else R.drawable.eye_icon_visible),
                        contentDescription = null,
                        tint = Dark
                    )
                }
            },
            isError = formData.passwordValidationErrorType != null,
            visualTransformation = if (formData.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(PaddingMedium))
        PrimaryButton(
            onClick = viewModel::register,
            text = stringResource(id = R.string.sign_up),
            enabled = screenState !is AuthState.Loading && signUpFormIsCorrectlyFilled,
            isLoading = screenState is AuthState.Loading
        )
        Spacer(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.height(PaddingMedium))
        ClickableText(modifier = Modifier.align(Alignment.CenterHorizontally),
            text = bottomHint,
            style = ParagraphMedium,
            onClick = { offset ->
                bottomHint.getStringAnnotations(tag = AUTH_TAG, start = offset, end = offset)
                    .firstOrNull()
                    ?.let { if (screenState !is AuthState.Loading) onNavigateToSignIn() }
            })
    }
}