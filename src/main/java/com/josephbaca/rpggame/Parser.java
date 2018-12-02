package com.josephbaca.rpggame;

import com.josephbaca.world.Context;

/**
 * Interprets user input and appropriately dispatches it.
 */
public class Parser {

  private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(Parser.class);

  static String parseInput(String input, Context context) {

    LOG.info(String.format("Parsing \"%s\"", input));
    return context.runInput(input);
  }
}
