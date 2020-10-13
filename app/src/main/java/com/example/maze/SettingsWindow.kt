package com.example.maze

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.constraintlayout.widget.ConstraintLayout

/**
 * A simple [Fragment] subclass.
 */
class SettingsWindow : AppCompatDialogFragment() {
    private val btn: Button? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.settings_window, container, false)
        val widthPixels = (resources.displayMetrics.widthPixels * 0.8).toInt() // get screen width
        val heightPixels = (resources.displayMetrics.heightPixels * 0.8).toInt() // get screen height
        val layoutParams = ConstraintLayout.LayoutParams(widthPixels, heightPixels) // set width and height into params
        v.layoutParams = layoutParams // set dialog params


        // highlight current status on window open : playing, not playing
        if (BackgroundMusicService.isPlaying) v.findViewById<View>(R.id.button_music_off).alpha = 0.5f else v.findViewById<View>(R.id.button_music_on).alpha = 0.5f
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog!!.window!!.setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE) // remove ui
        view.findViewById<View>(R.id.button_music_on).setOnClickListener {GameLogic.musicOn(view) } // turn music on
        view.findViewById<View>(R.id.button_music_off).setOnClickListener {  GameLogic.musicOff(view) } // turn music off
        view.findViewById<View>(R.id.button_sound_on).setOnClickListener { GameLogic.soundOn(view) } // turn sound effects on
        view.findViewById<View>(R.id.button_sound_off).setOnClickListener {  GameLogic.soundOff(view) } // turn sound effects off
        view.findViewById<View>(R.id.button_cheat).setOnClickListener { GameLogic.resetGame() }
        view.findViewById<View>(R.id.button_done).setOnClickListener { dismiss() }
    }
}