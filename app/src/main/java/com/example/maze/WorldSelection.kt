package com.example.maze

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.maze.R.drawable

/**
 * A simple [Fragment] subclass.
 */
class WorldSelection : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        GameLogic.context = context!!

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.world_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager: ViewPager = view.findViewById(R.id.viewPager)
        val adapter = ImageAdapter(context)
        viewPager.adapter = adapter
        view.findViewById<View>(R.id.main_settings).setOnClickListener { GameLogic.openSettings(childFragmentManager) } // open settings
        view.findViewById<View>(R.id.world_play).setOnClickListener { openWorld(viewPager) } // go into selected world
        view.findViewById<View>(R.id.b_back).setOnClickListener { activity!!.onBackPressed() } // go back
        GameLogic.updateOpenWorlds()
    }

    private fun openWorld(viewPager: ViewPager) {
        when (ImageAdapter.Companion.images[viewPager.currentItem]) {
            drawable.grassland_background -> activity!!.supportFragmentManager.beginTransaction().replace(R.id.game_frame, World.newInstance("Grassland")).addToBackStack("worldSelection").commit()
            drawable.desert_background -> activity!!.supportFragmentManager.beginTransaction().replace(R.id.game_frame, World.newInstance("Desert")).addToBackStack("worldSelection").commit()
            else -> {
                val toast = Toast.makeText(context, "World is locked", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
            }
        }
    }
}