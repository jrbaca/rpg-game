package com.josephbaca.parsing

import com.josephbaca.context.Battle
import com.josephbaca.context.ContextManager
import com.josephbaca.context.Room
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

internal class TokenizerTest {

    private var contextManager = ContextManager()

    @BeforeEach
    internal fun setUp() {
        contextManager = ContextManager()
    }

    @Test
    fun tokenizeOneToken() {
        val context = Room(contextManager)
        val allTokens = context.contextCommands.keys

        allTokens.forEach {
            val token = it
            val input = token.toString()

            val tokenList = Tokenizer.tokenizeInputWithContextCommandsRegex(input, allTokens)
            assertEquals(listOf(token), tokenList)
        }
    }
}