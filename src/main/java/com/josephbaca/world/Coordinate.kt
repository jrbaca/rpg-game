package com.josephbaca.world

/**
 * Convenience coordinate class. Allows coordinates to be expressed
 * as a single object. Immutable.
 */
data class Coordinate(internal val x: Int, internal val y: Int) {

    companion object {

        /**
         * Clean constructor for immutable coordinates.
         */
        @Deprecated(
            "Use normal constructor instead since Kotlin removes the need for this.",
            ReplaceWith("Coordinate(x, y)", "com.josephbaca.world.Coordinate")
        )
        fun of(x: Int, y: Int): Coordinate {
            return Coordinate(x, y)
        }
    }
}
