package com.josephbaca.parsing

/**
 * A token that can be attached to a function.
 */
interface VerbToken : Token {

    val numArgs: Int
}