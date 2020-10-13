package com.example.maze

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.maze.R.drawable
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class World : Fragment() {
    var levelButton: MutableList<Int> = ArrayList()
    private var worldName: String? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        GameLogic.context = context!!

        if (GameLogic.levelRefresh.value != null)
            if (GameLogic.levelRefresh.value!!)
                GameLogic.levelRefresh.value = false

        val rootView = inflater.inflate(R.layout.world, container, false) as ViewGroup
        for (i in 0 until rootView.childCount) {
            val v = rootView.getChildAt(i)
            if (v is Button) {
                levelButton.add(v.getId())
            }
        }
        worldName = arguments!!.getString("worldName")
        return inflater.inflate(R.layout.world, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<View>(R.id.b_back).setOnClickListener { activity!!.onBackPressed() }
        view.findViewById<View>(R.id.main_settings).setOnClickListener {GameLogic.openSettings(childFragmentManager) } // open settings
        view.findViewById<View>(R.id.b_level1).setOnClickListener { GameLogic.chooseLevel(activity, worldName, 1) }
        view.findViewById<View>(R.id.b_level2).setOnClickListener { GameLogic.chooseLevel(activity, worldName, 2) }
        view.findViewById<View>(R.id.b_level3).setOnClickListener { GameLogic.chooseLevel(activity, worldName, 3) }
        view.findViewById<View>(R.id.b_level4).setOnClickListener { GameLogic.chooseLevel(activity, worldName, 4) }
        view.findViewById<View>(R.id.b_level5).setOnClickListener { GameLogic.chooseLevel(activity, worldName, 5) }
        view.findViewById<View>(R.id.b_level6).setOnClickListener { GameLogic.chooseLevel(activity, worldName, 6) }
        view.findViewById<View>(R.id.b_level7).setOnClickListener { GameLogic.chooseLevel(activity, worldName, 7) }
        view.findViewById<View>(R.id.b_level8).setOnClickListener { GameLogic.chooseLevel(activity, worldName, 8) }
        view.findViewById<View>(R.id.b_level9).setOnClickListener { GameLogic.chooseLevel(activity, worldName, 9) }
        designWorld(view)
    }

    private fun designWorld(view: View) {
        val currentLevel = GameLogic.loadGame(worldName) // get current level progress from storage
        val level_locked = drawable.level_locked
        var level_finished = 0
        var level_open = 0
        when (worldName) {
            "Grassland" -> {
                level_finished = drawable.grassland_level_finished
                level_open = drawable.grassland_level_open
                view.setBackgroundResource(drawable.grassland_background)
            }
            "Desert" -> {
                level_finished = drawable.desert_level_finished
                level_open = drawable.desert_level_open
                view.setBackgroundResource(drawable.desert_background)
            }
        }
        for (buttonID in levelButton) { // running on all view buttons
            val buttonName = resources.getResourceEntryName(buttonID) // get button name by id
            val b = view.findViewById<Button>(buttonID) // get button view by ID
            val levelNum = buttonName[buttonName.length - 1] - '0' // get level number from button name
            if (buttonName.contains("b_level")) { // check if button is level related
                if (levelNum > currentLevel) { // locked level
                    b.isClickable = false
                    b.setBackgroundResource(level_locked)
                    b.text = null
                } else if (levelNum < currentLevel) { // level already won
                    b.setBackgroundResource(level_finished)
                } else { // current level
                    b.setBackgroundResource(level_open)
                }
            }
        }
    }

    companion object {
        fun newInstance(worldName: String?): World {
            val frag = World()
            val args = Bundle()
            args.putString("worldName", worldName)
            frag.arguments = args
            return frag
        }
    }
}