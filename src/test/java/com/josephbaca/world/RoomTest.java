package com.josephbaca.world;

import com.josephbaca.rpggame.Game;
import com.josephbaca.util.LoggerUtilKt;
import io.vavr.collection.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

  private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(RoomTest.class);

  @Test
  void testRoomMovement() {
//    LoggerUtilKt.setLogLevel("DEBUG", "root");

    List<String> commands = List.of("up", "right", "left", "down");

    List<List<Boolean>> success = commands.map(
        command -> List.fill(10, () -> false).map(item -> {
          Game g = new Game();

          // Ensure not at edge of world
          g.input("up");
          g.input("right");

          String description1 = g.input("where");
          String description2 = g.input(command);

          return !description1.equals(description2);
        })
    );

    for (List<Boolean> llb : success) {
      LOG.info(llb.toString());
      assertTrue(llb.fold(false, (l, r) -> l || r));
    }
  }

}