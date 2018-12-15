package com.josephbaca.parsing

import com.josephbaca.rpggame.GameStateManager
import com.josephbaca.context.Room
import com.josephbaca.util.setLogLevel
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

internal class TokenizerTest {

    private var contextManager = GameStateManager()

    @BeforeEach
    internal fun setUp() {
        contextManager = GameStateManager()
        setLogLevel("DEBUG")
    }

    @Test
    fun tokenizeOneToken() {
        val context = Room(contextManager)
        val allTokens = context.localVerbTokens.keys

        allTokens.forEach {
            val token = it
            val input = token.toString()

            val tokenList = Tokenizer.tokenizeInput(input, allTokens)
            assertEquals(listOf(token), tokenList)
        }
    }

    @Test
    fun tokenizeTwoTokens() {
        val context = Room(contextManager)
        val allTokens = context.localVerbTokens.keys

        val token1 = allTokens.random()
        val token2 = allTokens.random()

        val input = "%s %s".format(token1.toString(), token2.toString())

        val tokenList = Tokenizer.tokenizeInput(input, allTokens)
        assertEquals(listOf(token1, token2), tokenList)
    }

    @Test
    fun tokenizeTwoTokensWithExtraWhitespace() {
        val context = Room(contextManager)
        val allTokens = context.localVerbTokens.keys

        val token1 = allTokens.random()
        val token2 = allTokens.random()

        val input1 = "%s   %s   ".format(token1.toString(), token2.toString())
        val tokenList1 = Tokenizer.tokenizeInput(input1, allTokens)
        assertEquals(listOf(token1, token2), tokenList1)

        val input2 = "    %s   %s".format(token1.toString(), token2.toString())
        val tokenList2 = Tokenizer.tokenizeInput(input2, allTokens)
        assertEquals(listOf(token1, token2), tokenList2)

        val input3 = "   %s   %s   ".format(token1.toString(), token2.toString())
        val tokenList3 = Tokenizer.tokenizeInput(input3, allTokens)
        assertEquals(listOf(token1, token2), tokenList3)
    }

    @Test
    fun invalidStringReturnsNull() {
        val context = Room(contextManager)
        val allTokens = context.localVerbTokens.keys

        val input = "fakeinput"
        val tokenList = Tokenizer.tokenizeInput(input, allTokens)
        assertNull(tokenList)

    }

    @Test
    fun duplicateTokensReturnsNull() {

        val allTokens = BadTokens.values().toSet()

        val input = "bad"
        val tokenList = Tokenizer.tokenizeInput(input, allTokens)
        assertNull(tokenList)

    }

    enum class BadTokens(
        override val regex: Regex,
        override val numArgs: Int,
        override val helpUsage: String,
        override val helpString: String
    ) : VerbToken {

        DUP1(Regex("bad"), 0, "", ""),
        DUP2(Regex("bad"), 0, "", "");
    }
}