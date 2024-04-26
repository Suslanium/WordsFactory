package com.suslanium.wordsfactory.utils

import android.media.AudioAttributes
import android.media.MediaPlayer

object AudioPlayer {

    var mediaPlayer: MediaPlayer? = null

    fun playFile(url: String) {
        mediaPlayer?.apply {
            stop()
            release()
            mediaPlayer = null
        }
        MediaPlayer().apply {
            mediaPlayer = this
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                    .build()
            )
            try {
                setDataSource(url)
                prepare()
                start()
                setOnCompletionListener {
                    stop()
                    release()
                    mediaPlayer = null
                }
            } catch (_: Exception) {
                stop()
                release()
                mediaPlayer = null
            }
        }
    }

}