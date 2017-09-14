package com.paul.test.junit;

import com.paul.game.Game;
import com.paul.game.player.Player;
import com.paul.test.junit.interaction.ChessOutput;
import junit.framework.TestCase;

public class PawnMovementTest extends TestCase {

  private Game game;

  private ChessOutput white;
  private ChessOutput black;

  public PawnMovementTest() {
    super("Pawn Movement Test");
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

  public void testPawnMovement() {
    white.addMoveToQueue(new int[] {1, 7, 1, 5});
    black.addMoveToQueue(new int[] {2, 2, 2, 3});

    game.turn.turn(); //white
    game.turn.turn(); //black

    assertNotNull(game.board.getTileAt(1, 5).getPiece());
    assertNotNull(game.board.getTileAt(2, 3).getPiece());
  }

  public void testPawnAttack() {
    testPawnMovement();

    white.addMoveToQueue(new int[]{1, 5, 1, 4});
    black.addMoveToQueue(new int[]{2, 3, 1, 4});

    game.turn.turn(); //white
    game.turn.turn(); //black

    assertNotNull(game.board.getTileAt(1, 4).getPiece());
    assertEquals(31, game.board.getPieceList().size());
  }
}
