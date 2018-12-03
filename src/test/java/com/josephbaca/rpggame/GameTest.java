package com.josephbaca.rpggame;

import static org.junit.jupiter.api.Assertions.*;

import com.josephbaca.util.Context;
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

    // Test default contextManager is in room
    Context currentContext = game.getContextManager().getCurrentContext();
    assertTrue(currentContext instanceof Room);

    // Test we properly enter/exist new contexts (room (replace), battle (stack), etc)
    // Up should be different than base
    game.getWorld().movePlayerUp();
    Context newContext = game.getContextManager().getCurrentContext();
    assertNotEquals(currentContext, newContext);

    // Up then down should be the same
    game.getWorld().movePlayerDown();
    newContext = game.getContextManager().getCurrentContext();
    assertEquals(currentContext, newContext);

    // Right should be different from base
    game.getWorld().movePlayerRight();
    newContext = game.getContextManager().getCurrentContext();
    assertNotEquals(currentContext, newContext);

    // Right then left should be the same
    game.getWorld().movePlayerLeft();
    newContext = game.getContextManager().getCurrentContext();
    assertEquals(currentContext, newContext);

    // Can't walk off world
    game.getWorld().movePlayerDown();
    game.getWorld().movePlayerLeft();
    newContext = game.getContextManager().getCurrentContext();
    assertEquals(currentContext, newContext);

    // TODO test add/remove from stack
  }

}