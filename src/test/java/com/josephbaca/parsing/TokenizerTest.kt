package com.josephbaca.parsing

import com.josephbaca.context.ContextManager
import com.josephbaca.context.Room
import com.josephbaca.util.setLogLevel
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

internal class TokenizerTest {

    private var contextManager = ContextManager()

    @BeforeEach
    internal fun setUp() {
        contextManager = ContextManager()
        setLogLevel("DEBUG")
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

    @Test
    fun tokenizeTwoTokens() {
        val context = Room(contextManager)
        val allTokens = context.contextCommands.keys

        val token1 = allTokens.random()
        val token2 = allTokens.random()

        val input = "%s %s".format(token1.toString(), token2.toString())

        val tokenList = Tokenizer.tokenizeInputWithContextCommandsRegex(input, allTokens)
        assertEquals(listOf(token1, token2), tokenList)
    }

    @Test
    fun tokenizeTwoTokensWithExtraWhitespace() {
        val context = Room(contextManager)
        val allTokens = context.contextCommands.keys

        val token1 = allTokens.random()
        val token2 = allTokens.random()

        val input1 = "%s   %s   ".format(token1.toString(), token2.toString())
        val tokenList1 = Tokenizer.tokenizeInputWithContextCommandsRegex(input1, allTokens)
        assertEquals(listOf(token1, token2), tokenList1)

        val input2 = "    %s   %s".format(token1.toString(), token2.toString())
        val tokenList2 = Tokenizer.tokenizeInputWithContextCommandsRegex(input2, allTokens)
        assertEquals(listOf(token1, token2), tokenList2)

        val input3 = "   %s   %s   ".format(token1.toString(), token2.toString())
        val tokenList3 = Tokenizer.tokenizeInputWithContextCommandsRegex(input3, allTokens)
        assertEquals(listOf(token1, token2), tokenList3)
    }

    @Test
    fun invalidStringReturnsNull() {
        val context = Room(contextManager)
        val allTokens = context.contextCommands.keys

        val input = "fakeinput"
        val tokenList = Tokenizer.tokenizeInputWithContextCommandsRegex(input, allTokens)
        assertNull(tokenList)

    }

    @Test
    fun duplicateTokensReturnsNull() {

        val allTokens = BadTokens.values().toSet()

        val input = "bad"
        val tokenList = Tokenizer.tokenizeInputWithContextCommandsRegex(input, allTokens)
        assertNull(tokenList)

    }

    enum class BadTokens(
        override val regex: Regex,
        override val numArgs: Int
    ) : ContextCommand {

        DUP1(Regex("bad"), 0),
        DUP2(Regex("bad"), 0);
    }
}