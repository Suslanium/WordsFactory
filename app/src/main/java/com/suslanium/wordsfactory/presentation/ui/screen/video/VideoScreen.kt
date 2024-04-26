package com.suslanium.wordsfactory.presentation.ui.screen.video

import android.annotation.SuppressLint
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

private const val BASE_URL = "https://dictionary.cambridge.org/"

class VideoViewClient : WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        return !request?.url.toString().startsWith(BASE_URL)
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun VideoScreen(client: WebViewClient = VideoViewClient()) {
    AndroidView(modifier = Modifier
        .fillMaxSize(), factory = { context ->
        WebView(context).apply {
            webViewClient = client
            settings.javaScriptEnabled = true
            loadUrl(BASE_URL)
        }
    })

}