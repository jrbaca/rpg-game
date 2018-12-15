package com.josephbaca.rpggame

import com.josephbaca.parsing.Parser

/**
 ** Acts as an interface between game clients and game state.
 */
class GameWrapper {

    var gameStateManager = GameStateManager()

    fun getGameReponseFromInput(input: String): String {
        return restartGameIfNeeded() ?: evaluateInput(input)
    }

    private fun restartGameIfNeeded(): String? {
        return if (gameStateManager.gameOver) {
            restartGame()
            getDeathString()
        } else {
            null
        }
    }

    private fun restartGame() {
        gameStateManager = GameStateManager()
    }

    private fun evaluateInput(rawInput: String): String {
        val input = rawInput.toLowerCase().trim()
        return Parser.tokenizeAndParseInput(input, gameStateManager.currentContext)
    }

    private companion object {

        private val LOG = org.slf4j.LoggerFactory.getLogger(this::class.java)

        private val deathStrings = listOf(
            "Dead men tell no tables...? So we've made you a new live one!",
            "New game started!"
        )

        private fun getDeathString(): String {
            return deathStrings.random()
        }
    }
}