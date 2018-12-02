package com.josephbaca.rpggame;

import com.josephbaca.world.Context;

/**
 * Interprets user input and appropriately dispatches it.
 */
public class Parser {

  static String parseInput(String input, Context context) {
    return context.runInput(input);
  }
}
