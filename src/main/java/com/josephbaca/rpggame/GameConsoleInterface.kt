package com.josephbaca.rpggame

import java.util.*
import kotlin.text.Charsets.UTF_8
import ch.qos.logback.classic.Level
import ch.qos.logback.classic.LoggerContext
import org.slf4j.LoggerFactory


/**
 * Runs loop to accept user interaction from a console.
 */
fun main(args : Array<String>) {
    setLogLevel("INFO", "root")

    val game = Game()

    // Scanner for reading player input
    val sc = Scanner(System.`in`, UTF_8.name())
    var input: String

    while (true) {
        print("> ")
        input = sc.nextLine()
        if (input == "quit" ) break else println(game.input(input))
    }
}

private fun setLogLevel(logLevel: String, packageName: String) {
    val loggerContext = LoggerFactory.getILoggerFactory() as LoggerContext
    val logger = loggerContext.getLogger(packageName)
    println(packageName + " current logger level: " + logger.level)
    println(" You entered: $logLevel")
    logger.level = Level.toLevel(logLevel)
}