package com.josephbaca.world;

/**
 * Interface for any class that can be used in contextManager in the parser etc.
 */
public interface Context {

  String runInput(String input);

  String whereAt();
}
