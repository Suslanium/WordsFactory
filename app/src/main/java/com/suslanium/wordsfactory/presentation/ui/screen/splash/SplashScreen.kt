package com.suslanium.wordsfactory.presentation.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.suslanium.wordsfactory.R
import com.suslanium.wordsfactory.presentation.state.StartupEvent
import com.suslanium.wordsfactory.presentation.ui.common.ObserveAsEvents
import com.suslanium.wordsfactory.presentation.ui.theme.Dark
import com.suslanium.wordsfactory.presentation.ui.theme.HeadingH2
import com.suslanium.wordsfactory.presentation.ui.theme.PaddingMedium
import com.suslanium.wordsfactory.presentation.viewmodel.SplashViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(onNavigateAuthorized: () -> Unit, onNavigateUnauthorized: () -> Unit) {
    val viewModel: SplashViewModel = koinViewModel()

    ObserveAsEvents(flow = viewModel.startupEvents) { event ->
        when(event) {
            StartupEvent.Authorized -> onNavigateAuthorized()
            StartupEvent.Unauthorized -> onNavigateUnauthorized()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = PaddingMedium),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp),
            painter = painterResource(id = R.drawable.splash_image),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(PaddingMedium))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.app_name),
            style = HeadingH2,
            color = Dark,
            textAlign = TextAlign.Center
        )
    }
}