package com.josephbaca.world

import com.josephbaca.rpggame.ContextManager

/**
 * Contains all the geography about the world the player is in. Specifically,
 * it contains the coordinates of every [Tile] and the current location
 * of the player.
 */
class World
/**
 * Creates a world.
 *
 * @param name of the world
 * @param maxx coordinate of the world
 * @param maxy coordinate of the world
 */
(private val name: String, maxx: Int, maxy: Int, private val context: ContextManager) {

    /**
     * Player coordinates.
     */
    private var playerCoords: Coordinate? = null
    /**
     * Internal representation of the world.
     */
    private val grid: CoordinateGrid<Room>

    private val currentRoom: Room
        get() = grid.getCoordinate(playerCoords)

    init {

        this.grid = CoordinateGrid(maxx, maxy)
        this.playerCoords = Coordinate.of(0, 0)

        generateWorld()
        context.addContextLayer(currentRoom) // Add current room to context

        LOG.info("Created world logger")
    }

    private fun setPlayerCoords(c: Coordinate) {
        if (c.x >= 0 && c.x < grid.sizeX() && c.y > 0 && c.y < grid.sizeY()) {
            playerCoords = c
            context.replaceContextLayer(currentRoom)
        } else {
            LOG.info("Player tried to walk off the world.")
        }
    }

    /**
     * Move the player up one unit.
     */
    fun movePlayerUp() {
        setPlayerCoords(Coordinate.of(playerCoords!!.x, playerCoords!!.y + 1))
    }

    /**
     * Move the player down one unit.
     */
    fun movePlayerDown() {
        setPlayerCoords(Coordinate.of(playerCoords!!.x, playerCoords!!.y - 1))
    }

    /**
     * Move the player right one unit.
     */
    fun movePlayerRight() {
        setPlayerCoords(Coordinate.of(playerCoords!!.x + 1, playerCoords!!.y))
    }

    /**
     * Move the player left one unit.
     */
    fun movePlayerLeft() {
        setPlayerCoords(Coordinate.of(playerCoords!!.x - 1, playerCoords!!.y))
    }

    /**
     * World generator function.
     */
    private fun generateWorld() {
        for (x in 0 until grid.sizeX()) {
            for (y in 0 until grid.sizeY()) {
                val r = Room(5, 5, this)
                grid.setCoordinate(Coordinate.of(x, y), r)
            }
        }
    }

    override fun toString(): String {
        return grid.toString()
    }

    /**
     * Returns a print friendly version of the world.
     */
    fun toDisplayString(): String {
        return grid.toDisplayString()
    }

    companion object {

        private val LOG = org.slf4j.LoggerFactory.getLogger(World::class.java)
    }
}
