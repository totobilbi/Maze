package com.example.maze

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.maze.BackgroundMusicService.Companion.pauseMusic
import com.example.maze.BackgroundMusicService.Companion.resumeMusic
import com.example.maze.R.drawable
import com.example.maze.R.id
import java.util.*

object GameLogic {
    var context:Context? = null
    var levelRefresh = MutableLiveData<Boolean?>()

    /**---------------------------------------------------------------------
     * |  Level/World Making Methods section:
     * |
     * |
     * |
     * |
     * |
     * |
     * |
     * |
     * |
     * |
     * |
     * |
     * |
     * ------------------------------------------------------------------- */
    fun chooseLevel(activity: FragmentActivity?, worldName: String?, levelNumber: Int) {
        val fragment: Fragment = Level.newInstance(worldName, levelNumber)
        activity!!.supportFragmentManager.beginTransaction().replace(id.game_frame, fragment).addToBackStack("levelSelection").commit()
    }

    fun createLevel(inflater: LayoutInflater, container: ViewGroup?): View {
        val view = inflater.inflate(R.layout.level_template, container, false)
        var tileName = "game_tile_"
        val levelTemplate = getLevelLayout(Level.Companion.worldName, Level.Companion.levelNumber)
        for (i in levelTemplate!!.indices) {
            for (j in 0 until levelTemplate[i].count()) {
                tileName += i.toString() + "" + j
                view.findViewById<View>(context!!.resources.getIdentifier(tileName, "id", context!!.packageName)).setBackgroundResource(levelTemplate[i][j])
                tileName = "game_tile_"
            }
        }
        createPlayerSpawnPosition(view, Level.Companion.worldName, Level.Companion.levelNumber)
        return view
    }

    private fun getLevelLayout(worldName: String?, level: Int): Array<IntArray>? {
        when (worldName) {
            "Grassland" -> {
                when (level) {
                    1 -> return GameLevels.grasslandLevel1
                    2 -> return GameLevels.grasslandLevel2
                    3 -> return GameLevels.grasslandLevel3
                    4 -> return GameLevels.grasslandLevel4
                    5 -> return GameLevels.grasslandLevel5
                    6 -> return GameLevels.grasslandLevel6
                    7 -> return GameLevels.grasslandLevel7
                    8 -> return GameLevels.grasslandLevel8
                    9 -> return GameLevels.grasslandLevel9
                }
            }
            "Desert" -> when (level) {
                1 -> return GameLevels.desertLevel1
            }
        }
        return null
    }

    private fun createPlayerSpawnPosition(view: View, worldName: String?, levelNumber: Int) {
        //init playerTile
        var playerTile = ""
        when (worldName) {
            "Grassland" -> playerTile = GameLevels.grasslandPlayerSpawn[levelNumber - 1]
            "Desert" -> playerTile = GameLevels.desertPlayerSpawn[levelNumber - 1]
        }
        val mConstraintLayout = view as ConstraintLayout
        val game_player = view.findViewById<TextView>(id.game_player) //get player view
        val set = ConstraintSet() // make new set


        // get wanted player tile position indexes
        val firstNum = playerTile[playerTile.length - 2] - '0'
        val secondNum = playerTile[playerTile.length - 1] - '0'

        // get all tiles names around the wanted tile
        val bottomTile = "game_tile_" + (firstNum + 1) + "" + secondNum
        val topTile = "game_tile_" + (firstNum - 1) + "" + secondNum
        val startTile = "game_tile_" + firstNum + "" + (secondNum - 1)
        val endTile = "game_tile_" + firstNum + "" + (secondNum + 1)


        // get all tiles ID around the wanted tile
        val bottomConstraintID = getResId(bottomTile, id::class.java)
        val topConstraintID = getResId(topTile, id::class.java)
        val startConstraintID = getResId(startTile, id::class.java)
        val endConstraintID = getResId(endTile, id::class.java)
        set.connect(game_player.id, ConstraintSet.TOP, topConstraintID, ConstraintSet.BOTTOM) // make new constraint
        set.connect(game_player.id, ConstraintSet.BOTTOM, bottomConstraintID, ConstraintSet.TOP) // make new constraint
        set.connect(game_player.id, ConstraintSet.END, endConstraintID, ConstraintSet.START) // make new constraint
        set.connect(game_player.id, ConstraintSet.START, startConstraintID, ConstraintSet.END) // make new constraint
        set.applyTo(mConstraintLayout) // apply all changes
    }
    /**---------------------------------------------------------------------
     * |  General Methods section:
     * |
     * |  ArrayList<View> getUiList(View level_view)
     * |  Returns a list with all UI views (arrows, back, restart)
     * |
     * | hidePlayerUI(View level_view)
     * | Hides all the UI views
     * |
     * | showPlayerUI(View level_view
     * | Show all the UI views
     * |
     * -------------------------------------------------------------------</View> */
    /**
     * Returns a list with all UI views (arrows, back, restart)
     * level_view = current view of the level
     */
    private fun getUiList(level_view: View?): ArrayList<View> {
        val uiViewList = ArrayList<View>()
        uiViewList.add(level_view!!.findViewById(id.arrow_left))
        uiViewList.add(level_view.findViewById(id.arrow_right))
        uiViewList.add(level_view.findViewById(id.arrow_down))
        uiViewList.add(level_view.findViewById(id.arrow_up))
        uiViewList.add(level_view.findViewById(id.b_back))
        uiViewList.add(level_view.findViewById(id.b_restart))
        return uiViewList
    }

    /**
     * Hides all the UI views
     * level_view = current view of the level
     */
    private fun hidePlayerUI(level_view: View?) {
        val uiViewList = getUiList(level_view)
        for (view in uiViewList) view.visibility = View.INVISIBLE
    }

    /**
     * Show all the UI views
     * level_view = current view of the level
     */
    fun showPlayerUI(level_view: View?) {
        val uiViewList = getUiList(level_view)
        for (view in uiViewList) view.visibility = View.VISIBLE
    }
    /**---------------------------------------------------------------------
     * |  Settings Methods section:
     * |
     * | openSettings(FragmentManager childFragmentManager)
     * | Open settings window
     * |
     * |
     * | musicOn(View settings_view)
     * | Turn game music on
     * |
     * |
     * | musicOff(View settings_view)
     * | Turn game music off
     * |
     * |
     * | soundOn(View settings_view)
     * | Turn sound effects on
     * |
     * |
     * | soundOff(View settings_view)
     * | Turn sound effects off
     * |
     * ------------------------------------------------------------------- */
    /**
     * Open settings window
     * childFragmentManger
     */
    fun openSettings(childFragmentManager: FragmentManager?) {
        val cdf = SettingsWindow()
        cdf.show(childFragmentManager!!, "Custom Dialog")
    }

    /**
     * Turn game music on
     * settings_view = view of settings window
     */
    fun musicOn(settings_view: View) {
        settings_view.findViewById<View>(id.button_music_on).alpha = 1f
        settings_view.findViewById<View>(id.button_music_off).alpha = 0.5f
        resumeMusic()
        BackgroundMusicService.isMuted = false
    }

    /**
     * Turn game music off
     * settings_view = view of settings window
     */
    fun musicOff(settings_view: View) {
        settings_view.findViewById<View>(id.button_music_off).alpha = 1f
        settings_view.findViewById<View>(id.button_music_on).alpha = 0.5f
        pauseMusic()
        BackgroundMusicService.isMuted = true
    }

    /**
     * Turn sound effects on
     * settings_view = view of settings window
     */
    fun soundOn(settings_view: View) {
        settings_view.findViewById<View>(id.button_sound_on).alpha = 1f
        settings_view.findViewById<View>(id.button_sound_off).alpha = 0.5f
    }

    /**
     * Turn sound effects off
     * settings_view = view of settings window
     */
    fun soundOff(settings_view: View) {
        settings_view.findViewById<View>(id.button_sound_off).alpha = 1f
        settings_view.findViewById<View>(id.button_sound_on).alpha = 0.5f
    }
    /**---------------------------------------------------------------------
     * |  World/Level section:
     * |
     * |  levelStartToast(Class c)
     * |  Type writing effect on level start displaying the: "WorldName - Level X"
     * |
     * |  updateOpenWorlds()
     * |  Update current open worlds
     * |
     * |
     * |  refreshLevel(FragmentActivity fragmentActivity, Fragment fragment)
     * |  Restart current level
     * |
     * |
     * |  getResId(String resName, Class c)
     * |  Returns resource ID by  resource name and class path
     * |
     * |
     * |  levelComplete(View level_view)
     * |  Action on level finish
     * |
     * |
     * |  getDoorView(View level_view)
     * |  Returns the view in the level_view layout that has door background
     * |
     * ------------------------------------------------------------------- */
    /**
     * Type writing effect on level start displaying the: "WorldName - Level X"
     */
    fun levelStartToast(fragment: Fragment) {
        val level_view = fragment.view // get view from fragment
        hidePlayerUI(level_view) // hide player actions
        val toastText: String = Level.Companion.worldName + " - Level " + Level.Companion.levelNumber // customize output
        val tw: TypeWriter = level_view!!.findViewById(id.tv)
        tw.text = ""
        tw.setCharacterDelay(150)
        tw.animateText(toastText)
        tw.isDone().observe(fragment, Observer { aBoolean: Boolean? ->
            if (aBoolean!!) showPlayerUI(level_view) // release player actions
        })
    }

    /**
     * Update current open worlds
     */
    fun updateOpenWorlds() {
        if (loadGame("Grassland") == 10) {
            val images: IntArray = ImageAdapter.Companion.images
            images[1] = drawable.desert_background
            ImageAdapter.Companion.images = images
        }
    }

    /**
     * Restart current level
     * fragmentActivity = current activity
     * fragment = current fragment
     */
    fun refreshLevel(fragmentActivity: FragmentActivity?, fragment: Fragment?) {
        if (!levelRefresh.value!!) levelRefresh.value = true
        fragmentActivity!!.supportFragmentManager.beginTransaction().detach(fragment!!).attach(fragment).commit()
    }

    /**
     * Get resource ID by  resource name and class
     * resName = resource name
     * c = class to find resource in (ex R.id.class)
     */
    private fun getResId(resName: String?, c: Class<*>): Int {
        return try {
            val idField = c.getDeclaredField(resName!!)
            idField.getInt(idField)
        } catch (e: Exception) {
            e.printStackTrace()
            -1
        }
    }

    /**
     * Action on level finish
     * level_view = current level layout view
     */
    private fun levelComplete() {
        val worldName: String = Level.Companion.worldName!!
        val currentLevel: Int = Level.Companion.levelNumber
        if (currentLevel == loadGame(worldName)) // if this is the level player is currently on
            saveGame(worldName) // open new level
        Toast.makeText(context!!, "WIN", Toast.LENGTH_SHORT).show()
    }

    /**
     * Get the view in game that has door background
     * level_view = current level layout view
     */
    private fun getDoorView(level_view: View): View? {
        val worldName: String = Level.worldName!!.toLowerCase(Locale.ROOT)
        val doorID = getResId(worldName + "_door", drawable::class.java)
        val rootView = level_view as ViewGroup
        var v: View? = null
        for (i in 0 until rootView.childCount) {
            v = rootView.getChildAt(i)
            if (v.background != null)
                if (v.background.constantState == context!!.resources.getDrawable(doorID).constantState)
                return v
        }
        return v
    }



    /**---------------------------------------------------------------------
     * |  Save/Load Game section:
     * |
     * |  resetGame(String world)
     * |  set current world level progress to the start (developer use only)
     * |
     * |
     * |  saveGame(String world)
     * |  raise current world level progress by 1
     * |
     * |
     * |  loadGame(String world)
     * |  Return current player level progress on this world
     * |
     * ------------------------------------------------------------------- */


    /**
     * Reset game to level 1 (developer only)
     * world = current player world
     */
    fun resetGame() {
        val pref = context!!.getSharedPreferences("Grassland", Context.MODE_PRIVATE) // 0 - for private mode
        val editor = pref.edit()
        editor.putInt("currentLevel", 1)
        editor.apply()
    }

    /**
     * Raise player level progress by one (open new level)
     * world = current player world
     */
    private fun saveGame(world: String?) {
        val pref = context!!.getSharedPreferences(world, Context.MODE_PRIVATE) // 0 - for private mode
        val editor = pref.edit()
        editor.putInt("currentLevel", loadGame(world) + 1)
        editor.apply()
    }

    /**
     * Return current player level progress on this world
     * context!!
     * world = current player world
     */
    fun loadGame(world: String?): Int {
        val pref = context!!.getSharedPreferences(world, Context.MODE_PRIVATE) //
        return pref.getInt("currentLevel", 1)
    }
    /**---------------------------------------------------------------------
     * |  Player Movement section:
     * |
     * |  moveRight(View level_view, TextView game_player)
     * |  checks - game win, key acquired, bump into wall, rock push
     * |  returns boolean (if player moved)
     * |
     * |  moveLeft(View level_view, TextView game_player)
     * |  checks - game win, key acquired, bump into wall, rock push
     * |  returns boolean (if player moved)
     * |
     * |  moveUp(View level_view, TextView game_player)
     * |  checks - game win, key acquired, bump into wall, rock push
     * |  returns boolean (if player moved)
     * |
     * |  moveDown(View level_view, TextView game_player)
     * |  checks - game win, key acquired, bump into wall, rock push
     * |  returns boolean (if player moved)
     * |
     * ------------------------------------------------------------------- */
    /**
     * Move the character to the right
     * level_view = current level layout view
     * game_player = player
     */
    fun moveRight(level_view: View, game_player: TextView?): Boolean {
        var levelWon = false
        var playerMove = true
        val mConstraintLayout = level_view as ConstraintLayout // get the level layout
        val set = ConstraintSet() // make new set
        val params = game_player!!.layoutParams as ConstraintLayout.LayoutParams // get the current player position constraint params
        var newConstraintName: StringBuilder // string builder to switch value at specific char
        val worldName: String = Level.Companion.worldName!!.toLowerCase()
        game_player.setBackgroundResource(drawable.game_player_right)
        var topConstraintID = params.topToBottom // current view id of the top constraint
        var bottomConstraintID = params.bottomToTop // current view id of the bottom constraint
        var endConstraintID = params.endToStart // current view id of the end constraint
        var startConstraintID = params.startToEnd // current view id of the start constraint
        var topConstraintName = context!!.resources.getResourceEntryName(topConstraintID) // current view name of the top constraint
        var bottomConstraintName = context!!.resources.getResourceEntryName(bottomConstraintID) // current view name of the bottom constraint
        var endConstraintName = context!!.resources.getResourceEntryName(endConstraintID) // current view name of the end constraint
        var startConstraintName = context!!.resources.getResourceEntryName(startConstraintID) // current view name of the start constraint
        if (level_view.findViewById<View>(endConstraintID).background.constantState == context!!.resources.getDrawable(getResId(worldName + "_wall", drawable::class.java)).constantState) {
        } else {
            // game win
            if (level_view.findViewById<View>(endConstraintID).background.constantState == context!!.resources.getDrawable(getResId(worldName + "_win", drawable::class.java)).constantState) {
                levelComplete()
                levelWon = true
            }

            // key acquired
            if (level_view.findViewById<View>(endConstraintID).background.constantState == context!!.resources.getDrawable(getResId(worldName + "_key", drawable::class.java)).constantState) {
                getDoorView(level_view)!!.setBackgroundResource(getResId(worldName + "_win", drawable::class.java))
                level_view.findViewById<View>(endConstraintID).setBackgroundResource(getResId(worldName + "_floor", drawable::class.java))
            }

            // bump into a rock
            if (level_view.findViewById<View>(endConstraintID).background.constantState == context!!.resources.getDrawable(getResId(worldName + "_rock", drawable::class.java)).constantState) {
                if (!moveRockRight(level_view, game_player)) playerMove = false // if rock hits the wall player doesn't move
            }


            // trying to open door with no key
            if (level_view.findViewById<View>(endConstraintID).background.constantState == context!!.resources.getDrawable(getResId(worldName + "_door", drawable::class.java)).constantState) {
                Toast.makeText(context!!, "Get the key to clear the path", Toast.LENGTH_SHORT).show()
                playerMove = false // if the door is closed player doesn't move
            }

            // movement
            if (playerMove) {
                newConstraintName = StringBuilder(bottomConstraintName) // put the bottom view name in a string builder
                newConstraintName.setCharAt(bottomConstraintName.length - 1, (bottomConstraintName[bottomConstraintName.length - 1].toInt() + 1).toChar()) // increase the first index digit by 1
                bottomConstraintName = newConstraintName.toString() // store the new view name as a string
                bottomConstraintID = getResId(bottomConstraintName, id::class.java) // get new view name id
                newConstraintName = StringBuilder(topConstraintName) // put the top view name in a string builder
                newConstraintName.setCharAt(topConstraintName.length - 1, (topConstraintName[topConstraintName.length - 1].toInt() + 1).toChar()) // increase the first index digit by 1
                topConstraintName = newConstraintName.toString() // store the new view name as a string
                topConstraintID = getResId(topConstraintName, id::class.java) // get new view name id
                newConstraintName = StringBuilder(endConstraintName) // put the end view name in a string builder
                newConstraintName.setCharAt(endConstraintName.length - 1, (endConstraintName[endConstraintName.length - 1].toInt() + 1).toChar()) // increase the first index digit by 1
                endConstraintName = newConstraintName.toString() // store the new view name as a string
                endConstraintID = getResId(endConstraintName, id::class.java) // get new view name id
                newConstraintName = StringBuilder(startConstraintName) // put the start view name in a string builder
                newConstraintName.setCharAt(startConstraintName.length - 1, (startConstraintName[startConstraintName.length - 1].toInt() + 1).toChar()) // increase the first index digit by 1
                startConstraintName = newConstraintName.toString() // store the new view name as a string
                startConstraintID = getResId(startConstraintName, id::class.java) // get new view name id
                set.connect(game_player.id, ConstraintSet.TOP, topConstraintID, ConstraintSet.BOTTOM) // make new constraint
                set.connect(game_player.id, ConstraintSet.BOTTOM, bottomConstraintID, ConstraintSet.TOP) // make new constraint
                set.connect(game_player.id, ConstraintSet.END, endConstraintID, ConstraintSet.START) // make new constraint
                set.connect(game_player.id, ConstraintSet.START, startConstraintID, ConstraintSet.END) // make new constraint
                set.applyTo(mConstraintLayout) // apply all changes
            }
        }
        return levelWon
    }

    /**
     * Move the character to the left
     * level_view = current level layout view
     * game_player = player
     */
    fun moveLeft(level_view: View, game_player: TextView?): Boolean {
        var levelWon = false
        var playerMove = true
        val mConstraintLayout = level_view as ConstraintLayout // get the level layout
        val set = ConstraintSet() // make new set
        val params = game_player!!.layoutParams as ConstraintLayout.LayoutParams // get the current player position constraint params
        var newConstraintName: StringBuilder // string builder to switch value at specific char
        val worldName: String = Level.Companion.worldName!!.toLowerCase()
        game_player.setBackgroundResource(drawable.game_player_left)
        var topConstraintID = params.topToBottom // current view id of the top constraint
        var bottomConstraintID = params.bottomToTop // current view id of the bottom constraint
        var endConstraintID = params.endToStart // current view id of the end constraint
        var startConstraintID = params.startToEnd // current view id of the start constraint
        var topConstraintName = context!!.resources.getResourceEntryName(topConstraintID) // current view name of the top constraint
        var bottomConstraintName = context!!.resources.getResourceEntryName(bottomConstraintID) // current view name of the bottom constraint
        var endConstraintName = context!!.resources.getResourceEntryName(endConstraintID) // current view name of the end constraint
        var startConstraintName = context!!.resources.getResourceEntryName(startConstraintID) // current view name of the start constraint


        // check if next position is a wall
        if (level_view.findViewById<View>(startConstraintID).background.constantState == context!!.resources.getDrawable(getResId(worldName + "_wall", drawable::class.java)).constantState) {
        } else {
            // game win
            if (level_view.findViewById<View>(startConstraintID).background.constantState == context!!.resources.getDrawable(getResId(worldName + "_win", drawable::class.java)).constantState) {
                levelComplete()
                levelWon = true
            }


            // key acquired
            if (level_view.findViewById<View>(startConstraintID).background.constantState == context!!.resources.getDrawable(getResId(worldName + "_key", drawable::class.java)).constantState) {
                getDoorView(level_view)!!.setBackgroundResource(getResId(worldName + "_win", drawable::class.java))
                level_view.findViewById<View>(startConstraintID).setBackgroundResource(getResId(worldName + "_floor", drawable::class.java))
            }

            // bump into a rock
            if (level_view.findViewById<View>(startConstraintID).background.constantState == context!!.resources.getDrawable(getResId(worldName + "_rock", drawable::class.java)).constantState) {
                if (!moveRockLeft(level_view, game_player)) playerMove = false // if rock hits the wall player doesn't move
            }


            // trying to open door with no key
            if (level_view.findViewById<View>(startConstraintID).background.constantState == context!!.resources.getDrawable(getResId(worldName + "_door", drawable::class.java)).constantState) {
                Toast.makeText(context!!, "Get the key to clear the path", Toast.LENGTH_SHORT).show()
                playerMove = false // if the door is closed player doesn't move
            }

            // movement
            if (playerMove) {
                newConstraintName = StringBuilder(bottomConstraintName) // put the bottom view name in a string builder
                newConstraintName.setCharAt(bottomConstraintName.length - 1, (bottomConstraintName[bottomConstraintName.length - 1].toInt() - 1).toChar()) // increase the first index digit by 1
                bottomConstraintName = newConstraintName.toString() // store the new view name as a string
                bottomConstraintID = getResId(bottomConstraintName, id::class.java) // get new view name id
                newConstraintName = StringBuilder(topConstraintName) // put the top view name in a string builder
                newConstraintName.setCharAt(topConstraintName.length - 1, (topConstraintName[topConstraintName.length - 1].toInt() - 1).toChar()) // increase the first index digit by 1
                topConstraintName = newConstraintName.toString() // store the new view name as a string
                topConstraintID = getResId(topConstraintName, id::class.java) // get new view name id
                newConstraintName = StringBuilder(endConstraintName) // put the end view name in a string builder
                newConstraintName.setCharAt(endConstraintName.length - 1, (endConstraintName[endConstraintName.length - 1].toInt() - 1).toChar()) // increase the first index digit by 1
                endConstraintName = newConstraintName.toString() // store the new view name as a string
                endConstraintID = getResId(endConstraintName, id::class.java) // get new view name id
                newConstraintName = StringBuilder(startConstraintName) // put the start view name in a string builder
                newConstraintName.setCharAt(startConstraintName.length - 1, (startConstraintName[startConstraintName.length - 1].toInt() - 1).toChar()) // increase the first index digit by 1
                startConstraintName = newConstraintName.toString() // store the new view name as a string
                startConstraintID = getResId(startConstraintName, id::class.java) // get new view name id
                set.connect(game_player.id, ConstraintSet.TOP, topConstraintID, ConstraintSet.BOTTOM) // make new constraint
                set.connect(game_player.id, ConstraintSet.BOTTOM, bottomConstraintID, ConstraintSet.TOP) // make new constraint
                set.connect(game_player.id, ConstraintSet.END, endConstraintID, ConstraintSet.START) // make new constraint
                set.connect(game_player.id, ConstraintSet.START, startConstraintID, ConstraintSet.END) // make new constraint
                set.applyTo(mConstraintLayout) // apply all changes
            }
        }
        return levelWon
    }

    /**
     * Move the character upwards
     * level_view = current level layout view
     * game_player = player
     */
    fun moveUp(level_view: View, game_player: TextView?): Boolean {
        var levelWon = false
        var playerMove = true
        val mConstraintLayout = level_view as ConstraintLayout // get the level layout
        val set = ConstraintSet() // make new set
        val params = game_player!!.layoutParams as ConstraintLayout.LayoutParams // get the current player position constraint params
        var newConstraintName: StringBuilder // string builder to switch value at specific char
        val worldName: String = Level.Companion.worldName!!.toLowerCase()
        game_player.setBackgroundResource(drawable.game_player_up)
        var topConstraintID = params.topToBottom // current view id of the top constraint
        var bottomConstraintID = params.bottomToTop // current view id of the bottom constraint
        var endConstraintID = params.endToStart // current view id of the end constraint
        var startConstraintID = params.startToEnd // current view id of the start constraint
        var topConstraintName = context!!.resources.getResourceEntryName(topConstraintID) // current view name of the top constraint
        var bottomConstraintName = context!!.resources.getResourceEntryName(bottomConstraintID) // current view name of the bottom constraint
        var endConstraintName = context!!.resources.getResourceEntryName(endConstraintID) // current view name of the end constraint
        var startConstraintName = context!!.resources.getResourceEntryName(startConstraintID) // current view name of the start constraint


        // check if next position is a wall
        if (level_view.findViewById<View>(topConstraintID).background.constantState == context!!.resources.getDrawable(getResId(worldName + "_wall", drawable::class.java)).constantState) {
        } else {
            // game win
            if (level_view.findViewById<View>(topConstraintID).background.constantState == context!!.resources.getDrawable(getResId(worldName + "_win", drawable::class.java)).constantState) {
                levelComplete()
                levelWon = true
            }

            // key acquired
            if (level_view.findViewById<View>(topConstraintID).background.constantState == context!!.resources.getDrawable(getResId(worldName + "_key", drawable::class.java)).constantState) {
                getDoorView(level_view)!!.setBackgroundResource(getResId(worldName + "_win", drawable::class.java))
                level_view.findViewById<View>(topConstraintID).setBackgroundResource(getResId(worldName + "_floor", drawable::class.java))
            }

            // bump into a rock
            if (level_view.findViewById<View>(topConstraintID).background.constantState == context!!.resources.getDrawable(getResId(worldName + "_rock", drawable::class.java)).constantState) {
                if (!moveRockUp(level_view, game_player)) playerMove = false // if rock hits the wall player doesn't move
            }


            // trying to open door with no key
            if (level_view.findViewById<View>(topConstraintID).background.constantState == context!!.resources.getDrawable(getResId(worldName + "_door", drawable::class.java)).constantState) {
                Toast.makeText(context!!, "Get the key to clear the path", Toast.LENGTH_SHORT).show()
                playerMove = false // if the door is closed player doesn't move
            }

            // movement
            if (playerMove) {
                newConstraintName = StringBuilder(bottomConstraintName) // put the bottom view name in a string builder
                newConstraintName.setCharAt(bottomConstraintName.length - 2, (bottomConstraintName[bottomConstraintName.length - 2].toInt() - 1).toChar()) // increase the first index digit by 1
                bottomConstraintName = newConstraintName.toString() // store the new view name as a string
                bottomConstraintID = getResId(bottomConstraintName, id::class.java) // get new view name id
                newConstraintName = StringBuilder(topConstraintName) // put the top view name in a string builder
                newConstraintName.setCharAt(topConstraintName.length - 2, (topConstraintName[topConstraintName.length - 2].toInt() - 1).toChar()) // increase the first index digit by 1
                topConstraintName = newConstraintName.toString() // store the new view name as a string
                topConstraintID = getResId(topConstraintName, id::class.java) // get new view name id
                newConstraintName = StringBuilder(endConstraintName) // put the end view name in a string builder
                newConstraintName.setCharAt(endConstraintName.length - 2, (endConstraintName[endConstraintName.length - 2].toInt() - 1).toChar()) // increase the first index digit by 1
                endConstraintName = newConstraintName.toString() // store the new view name as a string
                endConstraintID = getResId(endConstraintName, id::class.java) // get new view name id
                newConstraintName = StringBuilder(startConstraintName) // put the start view name in a string builder
                newConstraintName.setCharAt(startConstraintName.length - 2, (startConstraintName[startConstraintName.length - 2].toInt() - 1).toChar()) // increase the first index digit by 1
                startConstraintName = newConstraintName.toString() // store the new view name as a string
                startConstraintID = getResId(startConstraintName, id::class.java) // get new view name id
                set.connect(game_player.id, ConstraintSet.TOP, topConstraintID, ConstraintSet.BOTTOM) // make new constraint
                set.connect(game_player.id, ConstraintSet.BOTTOM, bottomConstraintID, ConstraintSet.TOP) // make new constraint
                set.connect(game_player.id, ConstraintSet.END, endConstraintID, ConstraintSet.START) // make new constraint
                set.connect(game_player.id, ConstraintSet.START, startConstraintID, ConstraintSet.END) // make new constraint
                set.applyTo(mConstraintLayout) // apply all changes
            }
        }
        return levelWon
    }

    /**
     * Move the character downwards
     * level_view = current level layout view
     * game_player = player
     */
    fun moveDown(level_view: View, game_player: TextView?): Boolean {
        var levelWon = false
        var playerMove = true
        val mConstraintLayout = level_view as ConstraintLayout // get the level layout
        val set = ConstraintSet() // make new set
        val params = game_player!!.layoutParams as ConstraintLayout.LayoutParams // get the current player position constraint params
        var newConstraintName: StringBuilder // string builder to switch value at specific char
        val worldName: String = Level.worldName!!.toLowerCase(Locale.ROOT)
        game_player.setBackgroundResource(drawable.game_player)
        var topConstraintID = params.topToBottom // current view id of the top constraint
        var bottomConstraintID = params.bottomToTop // current view id of the bottom constraint
        var endConstraintID = params.endToStart // current view id of the end constraint
        var startConstraintID = params.startToEnd // current view id of the start constraint
        var topConstraintName = context!!.resources.getResourceEntryName(topConstraintID) // current view name of the top constraint
        var bottomConstraintName = context!!.resources.getResourceEntryName(bottomConstraintID) // current view name of the bottom constraint
        var endConstraintName = context!!.resources.getResourceEntryName(endConstraintID) // current view name of the end constraint
        var startConstraintName = context!!.resources.getResourceEntryName(startConstraintID) // current view name of the start constraint


        // check if next position is a wall
        if (level_view.findViewById<View>(bottomConstraintID).background.constantState == context!!.resources.getDrawable(getResId(worldName + "_wall", drawable::class.java)).constantState) {
        } else {
            // game win
            if (level_view.findViewById<View>(bottomConstraintID).background.constantState == context!!.resources.getDrawable(getResId(worldName + "_win", drawable::class.java)).constantState) {
                levelComplete()
                levelWon = true
            }

            // key acquired
            if (level_view.findViewById<View>(bottomConstraintID).background.constantState == context!!.resources.getDrawable(getResId(worldName + "_key", drawable::class.java)).constantState) {
                getDoorView(level_view)!!.setBackgroundResource(getResId(worldName + "_win", drawable::class.java)) // open door
                level_view.findViewById<View>(bottomConstraintID).setBackgroundResource(getResId(worldName + "_floor", drawable::class.java)) // remove key from layout
            }

            // bump into a rock
            if (level_view.findViewById<View>(bottomConstraintID).background.constantState == context!!.resources.getDrawable(getResId(worldName + "_rock", drawable::class.java)).constantState) {
                if (!moveRockDown(level_view, game_player)) playerMove = false // if rock hits the wall player doesn't move
            }


            // trying to open door with no key
            if (level_view.findViewById<View>(bottomConstraintID).background.constantState == context!!.resources.getDrawable(getResId(worldName + "_door", drawable::class.java)).constantState) {
                Toast.makeText(context!!, "Get the key to clear the path", Toast.LENGTH_SHORT).show()
                playerMove = false // if the door is closed player doesn't move
            }

            // movement
            if (playerMove) {
                newConstraintName = StringBuilder(bottomConstraintName) // put the bottom view name in a string builder
                newConstraintName.setCharAt(bottomConstraintName.length - 2, (bottomConstraintName[bottomConstraintName.length - 2].toInt() + 1).toChar()) // increase the first index digit by 1
                bottomConstraintName = newConstraintName.toString() // store the new view name as a string
                bottomConstraintID = getResId(bottomConstraintName, id::class.java) // get new view name id
                newConstraintName = StringBuilder(topConstraintName) // put the top view name in a string builder
                newConstraintName.setCharAt(topConstraintName.length - 2, (topConstraintName[topConstraintName.length - 2].toInt() + 1).toChar()) // increase the first index digit by 1
                topConstraintName = newConstraintName.toString() // store the new view name as a string
                topConstraintID = getResId(topConstraintName, id::class.java) // get new view name id
                newConstraintName = StringBuilder(endConstraintName) // put the end view name in a string builder
                newConstraintName.setCharAt(endConstraintName.length - 2, (endConstraintName[endConstraintName.length - 2].toInt() + 1).toChar()) // increase the first index digit by 1
                endConstraintName = newConstraintName.toString() // store the new view name as a string
                endConstraintID = getResId(endConstraintName, id::class.java) // get new view name id
                newConstraintName = StringBuilder(startConstraintName) // put the start view name in a string builder
                newConstraintName.setCharAt(startConstraintName.length - 2, (startConstraintName[startConstraintName.length - 2].toInt() + 1).toChar()) // increase the first index digit by 1
                startConstraintName = newConstraintName.toString() // store the new view name as a string
                startConstraintID = getResId(startConstraintName, id::class.java) // get new view name id
                set.connect(game_player.id, ConstraintSet.TOP, topConstraintID, ConstraintSet.BOTTOM) // make new constraint
                set.connect(game_player.id, ConstraintSet.BOTTOM, bottomConstraintID, ConstraintSet.TOP) // make new constraint
                set.connect(game_player.id, ConstraintSet.END, endConstraintID, ConstraintSet.START) // make new constraint
                set.connect(game_player.id, ConstraintSet.START, startConstraintID, ConstraintSet.END) // make new constraint
                set.applyTo(mConstraintLayout) // apply all changes
            }
        }
        return levelWon
    }
    /**---------------------------------------------------------------------
     * |  Rock Movement section:
     * |
     * |  moveRockRight(View level_view, TextView game_player)
     * |  checks - bump into wall, bump into another rock, bump into a door
     * |  returns boolean (if rock moved)
     * |
     * |  moveRockLeft(View level_view, TextView game_player)
     * |  checks - bump into wall, bump into another rock, bump into a door
     * |  returns boolean (if rock moved)
     * |
     * |  moveRockUp(View level_view, TextView game_player)
     * |  checks - bump into wall, bump into another rock, bump into a door
     * |  returns boolean (if rock moved)
     * |
     * |  moveRockDown(View level_view, TextView game_player)
     * |  checks - bump into wall, bump into another rock, bump into a door
     * |  returns boolean (if rock moved)
     * |
     * ------------------------------------------------------------------- */
    /**
     * Move the rock downwards
     * level_view = current level layout view
     * game_player = player
     */
    fun moveRockDown(level_view: View, game_player: TextView?): Boolean {
        val params = game_player!!.layoutParams as ConstraintLayout.LayoutParams // game player position params
        val newConstraintName: StringBuilder // string builder to switch value at specific char
        val worldName: String = Level.Companion.worldName!!.toLowerCase()
        val bottomConstraintID = params.bottomToTop // view ID of where the rock is
        val bottomConstraintName = context!!.resources.getResourceEntryName(bottomConstraintID) // view name of where the rock is
        newConstraintName = StringBuilder(bottomConstraintName) // put the rock view name in a string builder
        newConstraintName.setCharAt(bottomConstraintName.length - 2, (bottomConstraintName[bottomConstraintName.length - 2].toInt() + 1).toChar()) // increase the first index digit by 1
        val newBottomConstraintName = newConstraintName.toString() // store the new view name as a string
        val newBottomConstraintID = getResId(newBottomConstraintName, id::class.java) // get new view name id
        return if (level_view.findViewById<View>(newBottomConstraintID).background.constantState == context!!.resources.getDrawable(getResId(worldName + "_wall", drawable::class.java)).constantState || level_view.findViewById<View>(newBottomConstraintID).background.constantState == context!!.resources.getDrawable(getResId(worldName + "_rock", drawable::class.java)).constantState || level_view.findViewById<View>(newBottomConstraintID).background.constantState == context!!.resources.getDrawable(getResId(worldName + "_door", drawable::class.java)).constantState) {
            false
        } else {
            level_view.findViewById<View>(newBottomConstraintID).setBackgroundResource(getResId(worldName + "_rock", drawable::class.java)) // change rock position to game floor
            level_view.findViewById<View>(bottomConstraintID).setBackgroundResource(getResId(worldName + "_floor", drawable::class.java)) // change rock position to game floor
            true
        }
    }

    /**
     * Move the rock upwards
     * context!!
     * level_view = current level layout view
     * game_player = player
     */
    fun moveRockUp(level_view: View, game_player: TextView?): Boolean {
        val params = game_player!!.layoutParams as ConstraintLayout.LayoutParams // game player position params
        val newConstraintName: StringBuilder // string builder to switch value at specific char
        val worldName: String = Level.Companion.worldName!!.toLowerCase()
        val topConstraintID = params.topToBottom // view ID of where the rock is
        val topConstraintName = context!!.resources.getResourceEntryName(topConstraintID) // view name of where the rock is
        newConstraintName = StringBuilder(topConstraintName) // put the rock view name in a string builder
        newConstraintName.setCharAt(topConstraintName.length - 2, (topConstraintName[topConstraintName.length - 2].toInt() - 1).toChar()) // increase the first index digit by 1
        val newTopConstraintName = newConstraintName.toString() // store the new view name as a string
        val newTopConstraintID = getResId(newTopConstraintName, id::class.java) // get new view name id
        return if (level_view.findViewById<View>(newTopConstraintID).background.constantState == context!!.resources.getDrawable(getResId(worldName + "_wall", drawable::class.java)).constantState || level_view.findViewById<View>(newTopConstraintID).background.constantState == context!!.resources.getDrawable(getResId(worldName + "_rock", drawable::class.java)).constantState || level_view.findViewById<View>(newTopConstraintID).background.constantState == context!!.resources.getDrawable(getResId(worldName + "_door", drawable::class.java)).constantState) {
            false
        } else {
            level_view.findViewById<View>(newTopConstraintID).setBackgroundResource(getResId(worldName + "_rock", drawable::class.java)) // change rock position to game floor
            level_view.findViewById<View>(topConstraintID).setBackgroundResource(getResId(worldName + "_floor", drawable::class.java)) // change rock position to game floor
            true
        }
    }

    /**
     * Move the rock left
     * level_view = current level layout view
     * game_player = player
     */
    fun moveRockLeft(level_view: View, game_player: TextView?): Boolean {
        val params = game_player!!.layoutParams as ConstraintLayout.LayoutParams // game player position params
        val newConstraintName: StringBuilder // string builder to switch value at specific char
        val worldName: String = Level.Companion.worldName!!.toLowerCase()
        val startConstraintID = params.startToEnd // view ID of where the rock is
        val startConstraintName = context!!.resources.getResourceEntryName(startConstraintID) // view name of where the rock is
        newConstraintName = StringBuilder(startConstraintName) // put the rock view name in a string builder
        newConstraintName.setCharAt(startConstraintName.length - 1, (startConstraintName[startConstraintName.length - 1].toInt() - 1).toChar())
        val newStartConstraintName = newConstraintName.toString() // store the new view name as a string
        val newStartConstraintID = getResId(newStartConstraintName, id::class.java) // get new view name id
        return if (level_view.findViewById<View>(newStartConstraintID).background.constantState == context!!.resources.getDrawable(getResId(worldName + "_wall", drawable::class.java)).constantState || level_view.findViewById<View>(newStartConstraintID).background.constantState == context!!.resources.getDrawable(getResId(worldName + "_rock", drawable::class.java)).constantState || level_view.findViewById<View>(newStartConstraintID).background.constantState == context!!.resources.getDrawable(getResId(worldName + "_door", drawable::class.java)).constantState) {
            false
        } else {
            level_view.findViewById<View>(newStartConstraintID).setBackgroundResource(getResId(worldName + "_rock", drawable::class.java)) // change push position to rock
            level_view.findViewById<View>(startConstraintID).setBackgroundResource(getResId(worldName + "_floor", drawable::class.java)) // change rock position to game floor
            true
        }
    }

    /**
     * Move the rock right
     * level_view = current level layout view
     * game_player = player
     */
    fun moveRockRight(level_view: View, game_player: TextView?): Boolean {
        val params = game_player!!.layoutParams as ConstraintLayout.LayoutParams // game player position params
        val newConstraintName: StringBuilder // string builder to switch value at specific char
        val worldName: String = Level.Companion.worldName!!.toLowerCase()
        val endConstraintID = params.endToStart // view ID of where the rock is
        val endConstraintName = context!!.resources.getResourceEntryName(endConstraintID) // view name of where the rock is
        newConstraintName = StringBuilder(endConstraintName) // put the rock view name in a string builder
        newConstraintName.setCharAt(endConstraintName.length - 1, (endConstraintName[endConstraintName.length - 1].toInt() + 1).toChar())
        val newEndConstraintName = newConstraintName.toString() // store the new view name as a string
        val newEndConstraintID = getResId(newEndConstraintName, id::class.java) // get new view name id
        return if (level_view.findViewById<View>(newEndConstraintID).background.constantState == context!!.resources.getDrawable(getResId(worldName + "_wall", drawable::class.java)).constantState || level_view.findViewById<View>(newEndConstraintID).background.constantState == context!!.resources.getDrawable(getResId(worldName + "_rock", drawable::class.java)).constantState || level_view.findViewById<View>(newEndConstraintID).background.constantState == context!!.resources.getDrawable(getResId(worldName + "_door", drawable::class.java)).constantState) {
            false // rock hits the wall or another rock
        } else {
            level_view.findViewById<View>(newEndConstraintID).setBackgroundResource(getResId(worldName + "_rock", drawable::class.java)) // change push position to rock
            level_view.findViewById<View>(endConstraintID).setBackgroundResource(getResId(worldName + "_floor", drawable::class.java)) // change rock position to game floor
            true // rock moves
        }
    }
}