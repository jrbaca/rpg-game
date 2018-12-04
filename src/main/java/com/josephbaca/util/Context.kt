package com.josephbaca.util

/**
 * Interface for any class that can be used in contextManager in the parser etc.
 */
interface Context {

    fun runInput(input: String): String

    fun whereAt(): String
}
