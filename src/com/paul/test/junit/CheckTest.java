package com.paul.test.junit;

import com.paul.game.Game;
import com.paul.test.junit.interaction.ChessOutput;
import junit.framework.TestCase;

public class CheckTest extends TestCase {

  private Game game;

  private ChessOutput white;
  private ChessOutput black;

  public CheckTest() {
    super("Check Test");
  }

  protected void setUp() throws Exception {
    super.setUp();

    white = new ChessOutput();
    black = new ChessOutput();

    this.game = new Game(white, black);
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testCheck() {
    white.addMoveToQueue(new int[] {5, 7, 5, 5});
    black.addMoveToQueue(new int[] {4, 2, 4, 3});
    white.addMoveToQueue(new int[] {6, 8, 2, 4});

    int turns = white.getMoveQueueSize() + black.getMoveQueueSize();
    for (int i = 0; i < turns; i++) {
      game.turn.turn();
    }

    assertTrue(game.board.getKing(black).isCheck());
  }
}
