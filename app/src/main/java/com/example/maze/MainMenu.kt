package com.example.maze

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.maze.R.drawable

/**
 * A simple [Fragment] subclass.
 */
class MainMenu : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        GameLogic.context = context!!

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.main_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Load the ImageView that will host the animation and
        // set its background to our AnimationDrawable XML resource.
        view.setBackgroundResource(drawable.main_background_animation)

        // Get the background, which has been compiled to an AnimationDrawable object.
        val frameAnimation = view.background as AnimationDrawable

        // Start the animation (looped playback by default).
        frameAnimation.start()
        view.findViewById<View>(R.id.main_settings).setOnClickListener { v: View? -> GameLogic.openSettings(childFragmentManager) } // open settings
        view.findViewById<View>(R.id.main_continue).setOnClickListener { v: View? -> activity!!.supportFragmentManager.beginTransaction().replace(R.id.game_frame, WorldSelection()).addToBackStack("mainMenu").commit() }
    }
}