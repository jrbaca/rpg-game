package com.josephbaca.rpggame

import java.util.*
import kotlin.text.Charsets.UTF_8

object ConsoleInterface {

    /**
     * Runs loop to accept user interaction from a console.
     */
    @JvmStatic
    fun main(args: Array<String>) {

        val game = GameWrapper()

        // Scanner for reading player input
        val sc = Scanner(System.`in`, UTF_8.name())
        var input: String

        while (true) {
            print("> ")
            input = sc.nextLine()
            if (input == "quit") break else println(game.getGameReponseFromInput(input))
        }
    }
}