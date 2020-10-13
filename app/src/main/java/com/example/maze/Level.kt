package com.example.maze

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

/**
 * A simple [Fragment] subclass.
 */
class Level : Fragment() {
    private var gamePlayer: TextView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        GameLogic.context = context!!

        if (GameLogic.levelRefresh.value == null)
            GameLogic.levelRefresh.value = false

        worldName = arguments!!.getString("worldName")
        levelNumber = arguments!!.getInt("levelNumber")
        return GameLogic.createLevel(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!GameLogic.levelRefresh.value!!) GameLogic.levelStartToast(this)
        gamePlayer = view.findViewById(R.id.game_player) // get game player
        view.findViewById<View>(R.id.b_back).setOnClickListener { activity!!.onBackPressed() } // go back
        view.findViewById<View>(R.id.b_restart).setOnClickListener { GameLogic.refreshLevel(activity, this) } // restart level
        view.findViewById<View>(R.id.arrow_left).setOnTouchListener(RepeatListener(200, 200, View.OnClickListener { if (GameLogic.moveLeft(view, gamePlayer)) activity!!.onBackPressed() }))
        view.findViewById<View>(R.id.arrow_down).setOnTouchListener(RepeatListener(200, 200, View.OnClickListener { if (GameLogic.moveDown(view, gamePlayer)) activity!!.onBackPressed() }))
        view.findViewById<View>(R.id.arrow_right).setOnTouchListener(RepeatListener(200, 200, View.OnClickListener { if (GameLogic.moveRight(view, gamePlayer)) activity!!.onBackPressed() }))
        view.findViewById<View>(R.id.arrow_up).setOnTouchListener(RepeatListener(200, 200, View.OnClickListener { if (GameLogic.moveUp(view, gamePlayer)) activity!!.onBackPressed() }))
    }

    companion object {
        var worldName: String? = null
        var levelNumber = 0
        fun newInstance(worldName: String?, level: Int): Level {
            val frag = Level()
            val args = Bundle()
            args.putString("worldName", worldName)
            args.putInt("levelNumber", level)
            frag.arguments = args
            return frag
        }
    }
}