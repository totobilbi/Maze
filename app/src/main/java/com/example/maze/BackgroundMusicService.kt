package com.example.maze

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.widget.Toast
import com.example.maze.BackgroundMusicService

class BackgroundMusicService : Service(), MediaPlayer.OnErrorListener {
    private val mBinder: IBinder = ServiceBinder()

    inner class ServiceBinder : Binder() {
        val service: BackgroundMusicService
            get() = this@BackgroundMusicService
    }

    override fun onBind(arg0: Intent): IBinder? {
        return mBinder
    }

    override fun onCreate() {
        super.onCreate()
        mPlayer = MediaPlayer.create(this, R.raw.background_music)
        mPlayer?.setOnErrorListener(this)
        if (mPlayer != null) {
            mPlayer!!.isLooping = true
            mPlayer!!.setVolume(1f, 1f)
        }
        mPlayer?.setOnErrorListener(object : MediaPlayer.OnErrorListener {
            override fun onError(mp: MediaPlayer, what: Int, extra: Int): Boolean {
                onError(mPlayer!!, what, extra)
                return true
            }
        })
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        mPlayer!!.start()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mPlayer != null) {
            try {
                mPlayer!!.stop()
                mPlayer!!.release()
            } finally {
                mPlayer = null
            }
        }
    }

    override fun onError(mp: MediaPlayer, what: Int, extra: Int): Boolean {
        Toast.makeText(this, "music player failed", Toast.LENGTH_SHORT).show()
        if (mPlayer != null) {
            try {
                mPlayer!!.stop()
                mPlayer!!.release()
            } finally {
                mPlayer = null
            }
        }
        return false
    }

    companion object {
        private var mPlayer: MediaPlayer? = null
        private var length = 0
        var isMuted = false
        @JvmStatic
        fun pauseMusic() {
            if (mPlayer!!.isPlaying) {
                mPlayer!!.pause()
                length = mPlayer!!.currentPosition
            }
        }

        val isPlaying: Boolean
            get() = mPlayer!!.isPlaying

        @JvmStatic
        fun resumeMusic() {
            if (!mPlayer!!.isPlaying) {
                mPlayer!!.seekTo(length)
                mPlayer!!.start()
            }
        }

        fun stopMusic() {
            mPlayer!!.stop()
            mPlayer!!.release()
            mPlayer = null
        }

        fun startMusic() {
            mPlayer!!.start()
        }
    }
}