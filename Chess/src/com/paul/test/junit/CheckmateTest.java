package com.paul.test.junit;

import com.paul.game.Game;
import com.paul.test.junit.interaction.ChessOutput;
import junit.framework.TestCase;

public class CheckmateTest extends TestCase {

  private Game game;

  private ChessOutput white;
  private ChessOutput black;

  public CheckmateTest() {
    super("Checkmate Test");
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

  public void testCheckmate() {
    white.addMoveToQueue(new int[] {6, 7, 6, 6});
    black.addMoveToQueue(new int[] {5, 2, 5, 4});
    white.addMoveToQueue(new int[] {7, 7, 7, 5});
    black.addMoveToQueue(new int[] {4, 1, 8, 5});

    int turns = white.getMoveQueueSize() + black.getMoveQueueSize();
    for (int i = 0; i < turns; i++) {
      game.turn.turn();
    }

    assertTrue(this.game.board.getKing(white).isCheck());
    assertTrue(this.game.board.getKing(white).isCheckmate());
  }
}
