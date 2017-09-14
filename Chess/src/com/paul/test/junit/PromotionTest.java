package com.paul.test.junit;

import com.paul.game.Game;
import com.paul.game.piece.Queen;
import com.paul.game.piece.movement.Promotion;
import com.paul.test.junit.interaction.ChessOutput;
import junit.framework.TestCase;

public class PromotionTest extends TestCase {

  private Game game;

  private ChessOutput white;
  private ChessOutput black;

  public PromotionTest() {
    super("Promotion Test");
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

  public void testPromotionWhiteQueen() {
    white.addPromotionToQueue(Promotion.QUEEN);

    white.addMoveToQueue(new int[] {1, 7, 1, 5}); //pawn to move to other side
    black.addMoveToQueue(new int[] {2, 2, 2, 4});
    white.addMoveToQueue(new int[] {1, 5, 2, 4});
    black.addMoveToQueue(new int[] {2, 1, 3, 3}); //moving a knight around, filler
    white.addMoveToQueue(new int[] {2, 4, 2, 3});
    black.addMoveToQueue(new int[] {3, 3, 2, 5}); //moving a knight around, filler
    white.addMoveToQueue(new int[] {2, 3, 2, 2});
    black.addMoveToQueue(new int[] {2, 5, 3, 3}); //moving a knight around, filler
    white.addMoveToQueue(new int[] {2, 2, 2, 1}); //pawn is on the other side

    int turns = white.getMoveQueueSize() + black.getMoveQueueSize();
    for (int i = 0; i < turns; i++) {
      game.turn.turn();
    }

    assertNotNull(game.board.getTileAt(2, 1).getPiece());
    assertTrue(game.board.getTileAt(2, 1).getPiece() instanceof Queen);
    assertEquals(31, game.board.getPieceList().size());
  }
}

