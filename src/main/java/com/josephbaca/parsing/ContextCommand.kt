package com.josephbaca.parsing

interface ContextCommand {

    val regex: Regex
    val numArgs: Int
}