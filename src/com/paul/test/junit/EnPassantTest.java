package com.paul.test.junit;

import com.paul.game.Game;
import com.paul.test.junit.interaction.ChessOutput;
import junit.framework.TestCase;

public class EnPassantTest extends TestCase {

  private Game game;

  private ChessOutput white;
  private ChessOutput black;

  public EnPassantTest() {
    super("En Passant Test");
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

  public void testValidEnPassantAttack() {
    white.addMoveToQueue(new int[] {1, 7, 1, 5});
    black.addMoveToQueue(new int[] {8, 2, 8, 3});
    white.addMoveToQueue(new int[] {1, 5, 1, 4});
    black.addMoveToQueue(new int[] {2, 2, 2, 4});
    white.addMoveToQueue(new int[] {1, 4, 2, 3});

    int turns = white.getMoveQueueSize() + black.getMoveQueueSize();
    for (int i = 0; i < turns; i++) {
      game.turn.turn();
    }

    assertNotNull(game.board.getTileAt(2, 3).getPiece());
    assertEquals(31, game.board.getPieceList().size());
  }

  public void testTooLateEnPassant() {
    white.addMoveToQueue(new int[] {1, 7, 1, 5});
    black.addMoveToQueue(new int[] {2, 2, 2, 4});
    white.addMoveToQueue(new int[] {8, 7, 8, 6}); //random pawn, filler
    black.addMoveToQueue(new int[] {2, 4, 2, 5});
    white.addMoveToQueue(new int[] {8, 6, 8, 5}); //random pawn, filler
    black.addMoveToQueue(new int[] {2, 5, 1, 6});

    int turns = white.getMoveQueueSize() + black.getMoveQueueSize();
    for (int i = 0; i < turns; i++) {
      game.turn.turn();
    }

    assertNotNull(game.board.getTileAt(2, 5).getPiece()); //black piece has not been allowed en passant move
    assertNotNull(game.board.getTileAt(1, 5).getPiece()); //white piece has not been killed
    assertEquals(32, game.board.getPieceList().size()); //nothing was killed
  }

  public void testEnPassantOnNonVulnPawn() {
    white.addMoveToQueue(new int[] {1, 7, 1, 6}); //non vuln
    black.addMoveToQueue(new int[] {2, 2, 2, 4});
    white.addMoveToQueue(new int[] {1, 6, 1, 5});
    black.addMoveToQueue(new int[] {2, 4, 2, 5});
    white.addMoveToQueue(new int[] {8, 7, 8, 6}); //random pawn, filler
    black.addMoveToQueue(new int[] {2, 5, 1, 6});

    int turns = white.getMoveQueueSize() + black.getMoveQueueSize();
    for (int i = 0; i < turns; i++) {
      game.turn.turn();
    }

    assertNotNull(game.board.getTileAt(2, 5).getPiece()); //black piece has not been allowed en passant move
    assertNotNull(game.board.getTileAt(1, 5).getPiece()); //white piece has not been killed
    assertEquals(32, game.board.getPieceList().size()); //nothing was killed
  }
}
