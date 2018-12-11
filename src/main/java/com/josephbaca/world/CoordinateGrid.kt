package com.josephbaca.world

/**
 * Easy to use class for representing things that follow a cartesian coordinate grid.
 */
class CoordinateGrid<T : Mappable> internal constructor(val sizeX: Int, val sizeY: Int) {

    /**
     * Internal storage as y lists of x coords
     */
    private val grid: MutableList<MutableList<T?>> = MutableList(sizeY) { MutableList<T?>(sizeX) { null } }


    fun getCoordinate(c: Coordinate): T {
        return grid[c.y][c.x]!!
    }

    fun setCoordinate(c: Coordinate, t: T) {
        grid[c.y][c.x] = t
    }

    override fun toString(): String {
        return grid.toString()
    }

    /**
     * Returns a print friendly version of the grid formatted as a standard cartesian coordinate grid.
     */
    fun toDisplayString(): String {

        val sb = StringBuilder(sizeX * sizeY)

        for (j in sizeY - 1 downTo 0) { // Iterate down the Ys to order the list on a grid
            for (i in 0 until sizeX) {
                sb.append(getCoordinate(Coordinate(i, j)).icon)
                sb.append(" ")
            }
            sb.deleteCharAt(sb.length - 1)
            sb.append("\n")
        }
        sb.deleteCharAt(sb.length - 1)
        return sb.toString()
    }
}
