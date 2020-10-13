package com.example.maze

/*
Each Game level is made out of 10x10 board of tiles (100 tiles total)
 */
object GameLevels {




    /*
 ██████  ██████   █████  ███████ ███████ ██       █████  ███    ██ ██████ 
██       ██   ██ ██   ██ ██      ██      ██      ██   ██ ████   ██ ██   ██ 
██   ███ ██████  ███████ ███████ ███████ ██      ███████ ██ ██  ██ ██   ██ 
██    ██ ██   ██ ██   ██      ██      ██ ██      ██   ██ ██  ██ ██ ██   ██ 
 ██████  ██   ██ ██   ██ ███████ ███████ ███████ ██   ██ ██   ████ ██████  
                                                                          
     */


    //different tiles in grassland levels
    var grassland_wall = R.drawable.grassland_wall
    var grassland_key = R.drawable.grassland_key
    var grassland_floor = R.drawable.grassland_floor
    var grassland_door = R.drawable.grassland_door
    var grassland_rock = R.drawable.grassland_rock


    // spawn locations for each level on grassland world
    @JvmField
    var grasslandPlayerSpawn = arrayOf("game_tile_11", "game_tile_11", "game_tile_81", "game_tile_45", "game_tile_54", "game_tile_13", "game_tile_44", "game_tile_88", "game_tile_88")


    //developer use
    @JvmField
    var grasslandLevelTemplate = arrayOf(
            intArrayOf(grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall),
            intArrayOf(grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall),
            intArrayOf(grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall),
            intArrayOf(grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall),
            intArrayOf(grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall),
            intArrayOf(grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall),
            intArrayOf(grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall),
            intArrayOf(grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall),
            intArrayOf(grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall),
            intArrayOf(grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall))



    @JvmField
    var grasslandLevel1 = arrayOf(
            intArrayOf(grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_wall, grassland_floor, grassland_floor, grassland_floor, grassland_wall, grassland_key, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_wall, grassland_floor, grassland_floor, grassland_floor, grassland_wall, grassland_wall, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_wall, grassland_floor, grassland_wall, grassland_floor, grassland_wall, grassland_floor, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_floor, grassland_floor, grassland_wall, grassland_floor, grassland_wall, grassland_floor, grassland_wall, grassland_wall),
            intArrayOf(grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_floor, grassland_wall, grassland_floor, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_floor, grassland_floor, grassland_floor, grassland_floor, grassland_wall, grassland_floor, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_floor, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_floor, grassland_floor, grassland_floor, grassland_floor, grassland_floor, grassland_floor, grassland_wall, grassland_wall),
            intArrayOf(grassland_wall, grassland_door, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall))

    @JvmField
    var grasslandLevel2 = arrayOf(
            intArrayOf(grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_floor, grassland_floor, grassland_floor, grassland_floor, grassland_floor, grassland_floor, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_rock, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_floor, grassland_floor, grassland_floor, grassland_floor, grassland_floor, grassland_floor, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_floor, grassland_floor, grassland_floor, grassland_floor, grassland_floor, grassland_wall, grassland_wall, grassland_wall),
            intArrayOf(grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_floor, grassland_wall, grassland_floor, grassland_floor, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_wall, grassland_key, grassland_wall, grassland_floor, grassland_wall, grassland_wall, grassland_wall, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_floor, grassland_rock, grassland_floor, grassland_wall, grassland_floor, grassland_floor, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_door, grassland_wall, grassland_wall, grassland_wall))

    @JvmField
    var grasslandLevel3 = arrayOf(
            intArrayOf(grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_wall, grassland_wall, grassland_floor, grassland_floor, grassland_floor, grassland_floor, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_floor, grassland_floor, grassland_floor, grassland_wall, grassland_wall, grassland_rock, grassland_wall, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_wall, grassland_floor, grassland_wall, grassland_wall, grassland_floor, grassland_floor, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_wall, grassland_wall, grassland_wall, grassland_floor, grassland_wall, grassland_floor, grassland_wall, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_wall, grassland_floor, grassland_floor, grassland_rock, grassland_floor, grassland_floor, grassland_wall, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_floor, grassland_floor, grassland_wall, grassland_floor, grassland_wall, grassland_floor, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_rock, grassland_wall, grassland_floor, grassland_wall, grassland_floor, grassland_wall, grassland_floor, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_rock, grassland_floor, grassland_wall, grassland_key, grassland_wall, grassland_wall, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_door, grassland_wall))

    @JvmField
    var grasslandLevel4 = arrayOf(
            intArrayOf(grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_rock, grassland_floor, grassland_floor, grassland_floor, grassland_floor, grassland_floor, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_wall, grassland_wall, grassland_floor, grassland_floor, grassland_floor, grassland_wall, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_floor, grassland_floor, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_rock, grassland_floor, grassland_floor, grassland_rock, grassland_floor, grassland_floor, grassland_floor, grassland_rock, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_wall, grassland_floor, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_rock, grassland_wall, grassland_wall, grassland_key, grassland_wall, grassland_wall, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_floor, grassland_floor, grassland_wall, grassland_floor, grassland_wall, grassland_floor, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_wall, grassland_floor, grassland_floor, grassland_floor, grassland_wall, grassland_floor, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_door, grassland_wall, grassland_wall))

    @JvmField
    var grasslandLevel5 = arrayOf(
            intArrayOf(grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall),
            intArrayOf(grassland_wall, grassland_wall, grassland_floor, grassland_floor, grassland_floor, grassland_rock, grassland_floor, grassland_wall, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_wall, grassland_rock, grassland_rock, grassland_floor, grassland_floor, grassland_floor, grassland_rock, grassland_rock, grassland_wall),
            intArrayOf(grassland_wall, grassland_key, grassland_floor, grassland_floor, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_wall, grassland_rock, grassland_floor, grassland_wall, grassland_rock, grassland_wall, grassland_floor, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_wall, grassland_floor, grassland_wall, grassland_floor, grassland_floor, grassland_wall, grassland_floor, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_floor, grassland_wall, grassland_wall, grassland_rock, grassland_wall, grassland_rock, grassland_rock, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_floor, grassland_rock, grassland_floor, grassland_floor, grassland_floor, grassland_floor, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_wall, grassland_wall, grassland_floor, grassland_floor, grassland_wall, grassland_wall, grassland_wall, grassland_wall),
            intArrayOf(grassland_wall, grassland_door, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall))

    @JvmField
    var grasslandLevel6 = arrayOf(
            intArrayOf(grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_door, grassland_wall, grassland_wall, grassland_wall, grassland_wall),
            intArrayOf(grassland_wall, grassland_wall, grassland_wall, grassland_floor, grassland_wall, grassland_floor, grassland_floor, grassland_floor, grassland_wall, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_rock, grassland_floor, grassland_wall, grassland_wall, grassland_floor, grassland_floor, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_floor, grassland_wall, grassland_floor, grassland_rock, grassland_floor, grassland_rock, grassland_wall, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_rock, grassland_floor, grassland_rock, grassland_floor, grassland_rock, grassland_floor, grassland_rock, grassland_wall),
            intArrayOf(grassland_wall, grassland_wall, grassland_floor, grassland_wall, grassland_floor, grassland_wall, grassland_floor, grassland_rock, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_wall, grassland_floor, grassland_rock, grassland_floor, grassland_wall, grassland_floor, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_floor, grassland_rock, grassland_floor, grassland_rock, grassland_floor, grassland_rock, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_key, grassland_wall, grassland_floor, grassland_wall, grassland_floor, grassland_wall, grassland_floor, grassland_wall, grassland_wall),
            intArrayOf(grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall))

    @JvmField
    var grasslandLevel7 = arrayOf(
            intArrayOf(grassland_wall, grassland_wall, grassland_door, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall),
            intArrayOf(grassland_wall, grassland_wall, grassland_floor, grassland_floor, grassland_wall, grassland_floor, grassland_wall, grassland_floor, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_wall, grassland_floor, grassland_floor, grassland_wall, grassland_floor, grassland_floor, grassland_floor, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_wall, grassland_wall, grassland_rock, grassland_wall, grassland_rock, grassland_wall, grassland_rock, grassland_wall, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_floor, grassland_rock, grassland_floor, grassland_rock, grassland_floor, grassland_floor, grassland_wall, grassland_wall),
            intArrayOf(grassland_wall, grassland_wall, grassland_wall, grassland_rock, grassland_rock, grassland_rock, grassland_wall, grassland_floor, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_wall, grassland_floor, grassland_wall, grassland_wall, grassland_floor, grassland_floor, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_floor, grassland_floor, grassland_rock, grassland_floor, grassland_wall, grassland_rock, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_key, grassland_floor, grassland_wall, grassland_floor, grassland_floor, grassland_floor, grassland_rock, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall))

    @JvmField
    var grasslandLevel8 = arrayOf(
            intArrayOf(grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_floor, grassland_floor, grassland_wall, grassland_floor, grassland_floor, grassland_floor, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_wall, grassland_floor, grassland_rock, grassland_floor, grassland_floor, grassland_wall, grassland_floor, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_rock, grassland_floor, grassland_wall, grassland_wall, grassland_floor, grassland_rock, grassland_rock, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_rock, grassland_floor, grassland_rock, grassland_floor, grassland_wall, grassland_wall, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_wall, grassland_floor, grassland_rock, grassland_key, grassland_floor, grassland_rock, grassland_floor, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_wall, grassland_rock, grassland_wall, grassland_rock, grassland_rock, grassland_floor, grassland_floor, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_floor, grassland_wall, grassland_floor, grassland_wall, grassland_floor, grassland_rock, grassland_rock, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_floor, grassland_wall, grassland_floor, grassland_wall, grassland_floor, grassland_rock, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_door, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall))

    @JvmField
    var grasslandLevel9 = arrayOf(
            intArrayOf(grassland_wall, grassland_door, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_floor, grassland_floor, grassland_floor, grassland_floor, grassland_rock, grassland_floor, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_rock, grassland_floor, grassland_floor, grassland_wall, grassland_rock, grassland_floor, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_wall, grassland_rock, grassland_wall, grassland_wall, grassland_wall, grassland_rock, grassland_rock, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_wall, grassland_floor, grassland_floor, grassland_rock, grassland_rock, grassland_floor, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_floor, grassland_floor, grassland_floor, grassland_wall, grassland_floor, grassland_floor, grassland_wall, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_rock, grassland_wall, grassland_wall, grassland_wall, grassland_rock, grassland_rock, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_floor, grassland_floor, grassland_floor, grassland_floor, grassland_wall, grassland_floor, grassland_floor, grassland_wall, grassland_wall),
            intArrayOf(grassland_wall, grassland_wall, grassland_rock, grassland_floor, grassland_key, grassland_wall, grassland_wall, grassland_floor, grassland_floor, grassland_wall),
            intArrayOf(grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall, grassland_wall))




    var desert_wall = R.drawable.desert_wall
    var desert_key = R.drawable.desert_key
    var desert_floor = R.drawable.desert_floor
    var desert_door = R.drawable.desert_door
    var desert_rock = R.drawable.desert_rock

    @JvmField
    var desertPlayerSpawn = arrayOf("game_tile_11", "game_tile_11", "game_tile_81", "game_tile_45", "game_tile_54", "game_tile_13", "game_tile_44", "game_tile_88", "game_tile_88")

    var desertLevelTemplate = arrayOf(
            intArrayOf(desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall),
            intArrayOf(desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall),
            intArrayOf(desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall),
            intArrayOf(desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall),
            intArrayOf(desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall),
            intArrayOf(desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall),
            intArrayOf(desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall),
            intArrayOf(desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall),
            intArrayOf(desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall),
            intArrayOf(desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall))

    @JvmField
    var desertLevel1 = arrayOf(
            intArrayOf(desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall),
            intArrayOf(desert_wall, desert_floor, desert_floor, desert_floor, desert_floor, desert_floor, desert_floor, desert_floor, desert_floor, desert_wall),
            intArrayOf(desert_wall, desert_floor, desert_floor, desert_floor, desert_floor, desert_floor, desert_floor, desert_floor, desert_floor, desert_door),
            intArrayOf(desert_wall, desert_rock, desert_floor, desert_floor, desert_floor, desert_rock, desert_floor, desert_floor, desert_floor, desert_wall),
            intArrayOf(desert_wall, desert_floor, desert_floor, desert_floor, desert_floor, desert_floor, desert_floor, desert_floor, desert_floor, desert_wall),
            intArrayOf(desert_wall, desert_floor, desert_floor, desert_floor, desert_floor, desert_floor, desert_floor, desert_floor, desert_floor, desert_wall),
            intArrayOf(desert_wall, desert_floor, desert_floor, desert_floor, desert_floor, desert_floor, desert_floor, desert_key, desert_floor, desert_wall),
            intArrayOf(desert_wall, desert_floor, desert_floor, desert_floor, desert_rock, desert_floor, desert_floor, desert_floor, desert_floor, desert_wall),
            intArrayOf(desert_wall, desert_floor, desert_floor, desert_floor, desert_floor, desert_floor, desert_floor, desert_floor, desert_floor, desert_wall),
            intArrayOf(desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall, desert_wall))
}