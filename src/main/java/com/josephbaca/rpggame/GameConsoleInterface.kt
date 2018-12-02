package com.josephbaca.rpggame

import com.josephbaca.world.World


/**
 * Starts loop to accept user interaction.
 */
fun main(args : Array<String>) {
    println("Hello, World!")
    val w = World()
    println(w.sum(2, 5))

}
