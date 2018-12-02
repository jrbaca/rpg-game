package com.josephbaca.world;

import com.josephbaca.rpggame.ContextManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

  private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(RoomTest.class);

  @Test
  void testRoom() {
    ContextManager context = new ContextManager();
    World w = new World("the dungeon", 10, 10, context);
    Room r = new Room(5, 5, w);

  }

}