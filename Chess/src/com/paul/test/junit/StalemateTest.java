package com.paul.test.junit;

import com.paul.game.Game;
import com.paul.test.junit.interaction.ChessOutput;
import junit.framework.TestCase;

public class StalemateTest extends TestCase {

  private Game game;

  private ChessOutput white;
  private ChessOutput black;

  public StalemateTest() {
    super("Stalemate Test");
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

  public void testStalemate() {
    white.addMoveToQueue(new int[] {3, 7, 3, 5}); //white pawn
    black.addMoveToQueue(new int[] {8, 2, 8, 4}); //black pawn
    white.addMoveToQueue(new int[] {8, 7, 8, 5}); //white pawn
    black.addMoveToQueue(new int[] {1, 2, 1, 4}); //black pawn
    white.addMoveToQueue(new int[] {4, 8, 1, 5}); //white queen
    black.addMoveToQueue(new int[] {1, 1, 1, 3}); //black rook
    white.addMoveToQueue(new int[] {1, 5, 1, 4}); //white queen
    black.addMoveToQueue(new int[] {1, 3, 8, 3}); //black rook
    white.addMoveToQueue(new int[] {1, 4, 3, 2}); //white queen
    black.addMoveToQueue(new int[] {6, 2, 6, 3}); //black pawn
    white.addMoveToQueue(new int[] {3, 2, 4, 2}); //white queen
    black.addMoveToQueue(new int[] {5, 1, 6, 2}); //black king
    white.addMoveToQueue(new int[] {4, 2, 2, 2}); //white queen
    black.addMoveToQueue(new int[] {4, 1, 4, 6}); //black queen
    white.addMoveToQueue(new int[] {2, 2, 2, 1}); //white queen
    black.addMoveToQueue(new int[] {4, 6, 8, 2}); //black queen
    white.addMoveToQueue(new int[] {2, 1, 3, 1}); //white queen
    black.addMoveToQueue(new int[] {6, 2, 7, 3}); //black king
    white.addMoveToQueue(new int[] {3, 1, 5, 3}); //white queen

    int turns = white.getMoveQueueSize() + black.getMoveQueueSize();
    for (int i = 0; i < turns; i++) {
      game.turn.turn();
    }

    assertTrue(game.board.getKing(black).isStalemate());
  }
}
