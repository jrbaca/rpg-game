package com.josephbaca.rpggame

import java.util.*
import kotlin.text.Charsets.UTF_8
import com.josephbaca.util.setLogLevel


/**
 * Runs loop to accept user interaction from a console.
 */
fun main(args: Array<String>) {
    setLogLevel("DEBUG", "root")

    val game = Game()

    // Scanner for reading player input
    val sc = Scanner(System.`in`, UTF_8.name())
    var input: String

    while (true) {
        print("> ")
        input = sc.nextLine()
        if (input == "quit") break else println(game.input(input))
    }
}