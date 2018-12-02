package com.josephbaca.rpggame;

import static org.junit.jupiter.api.Assertions.*;

import com.josephbaca.world.Context;
import com.josephbaca.world.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class GameTest {

  private Game game;

  @BeforeEach
  void setUp() {
    game = new Game();
  }

  @Test
  void testContext() {
    // Setup
    System.out.println(game.getContext().getCurrentContext());

    // Test default context is in room
    Context currentContext = game.getContext().getCurrentContext();
    assertTrue(currentContext instanceof Room);

    // Test we properly enter/exist new contexts (room (replace), battle (stack), etc)
    // Up should be different than base
    game.getWorld().movePlayerUp();
    Context newContext = game.getContext().getCurrentContext();
    assertNotEquals(currentContext, newContext);

    // Up then down should be the same
    game.getWorld().movePlayerDown();
    newContext = game.getContext().getCurrentContext();
    assertEquals(currentContext, newContext);

    // Right should be different from base
    game.getWorld().movePlayerRight();
    newContext = game.getContext().getCurrentContext();
    assertNotEquals(currentContext, newContext);

    // Right then left should be the same
    game.getWorld().movePlayerLeft();
    newContext = game.getContext().getCurrentContext();
    assertEquals(currentContext, newContext);

    // TODO test add/remove from stack
  }

}