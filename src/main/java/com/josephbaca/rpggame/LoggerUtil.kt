package com.josephbaca.rpggame

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.LoggerContext
import org.slf4j.LoggerFactory

fun setLogLevel(logLevel: String, packageName: String) {
    val loggerContext = LoggerFactory.getILoggerFactory() as LoggerContext
    val logger = loggerContext.getLogger(packageName)
    println(packageName + " current logger level: " + logger.level)
    println(" You entered: $logLevel")
    logger.level = Level.toLevel(logLevel)
}