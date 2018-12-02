package com.josephbaca.world;

/**
 * Interface for any class that can be used in context in the parser etc.
 */
public interface Context {

//  Runnable getTokenToFunction(Token<? extends TokenPatterns> t);

  String whereAt();
}
