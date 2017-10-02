package com.paul.test;

import com.paul.game.Game;
import com.paul.game.piece.King;
import com.paul.game.piece.Rook;
import com.paul.test.interaction.ChessOutput;
import junit.framework.TestCase;

public class CastlingTest extends TestCase {

  private Game game;

  private ChessOutput white;
  private ChessOutput black;

  public CastlingTest() {
    super("Castling Test");
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

  public void testShortCastling() {
    white.addMoveToQueue(new int[] {7, 8, 8, 6}); //knight out of the way
    black.addMoveToQueue(new int[] {1, 2, 1, 3}); //filler
    white.addMoveToQueue(new int[] {5, 7, 5, 6}); //pawn making room for bishop
    black.addMoveToQueue(new int[] {1, 3, 1, 4}); //filler
    white.addMoveToQueue(new int[] {6, 8, 5, 7}); //bishop out of the way
    black.addMoveToQueue(new int[] {1, 4, 1, 5}); //filler
    white.addMoveToQueue(new int[] {5, 8, 7, 8}); //short castling

    int turns = white.getMoveQueueSize() + black.getMoveQueueSize();
    for (int i = 0; i < turns; i++) {
      game.turn.turn();
    }

    assertTrue(game.board.getTileAt(7, 8).getPiece() instanceof King);
    assertTrue(game.board.getTileAt(6, 8).getPiece() instanceof Rook);
  }

  public void testLongCastling() {
    white.addMoveToQueue(new int[] {2, 8, 1, 6}); //knight out of the way
    black.addMoveToQueue(new int[] {1, 2, 1, 3}); //filler
    white.addMoveToQueue(new int[] {4, 7, 4, 5}); //pawn making room for bishop
    black.addMoveToQueue(new int[] {1, 3, 1, 4}); //filler
    white.addMoveToQueue(new int[] {3, 8, 8, 3}); //bishop out of the way
    black.addMoveToQueue(new int[] {1, 4, 1, 5}); //filler
    white.addMoveToQueue(new int[] {4, 8, 4, 6}); //queen out of the way
    black.addMoveToQueue(new int[] {2, 2, 2, 3}); //filler
    white.addMoveToQueue(new int[] {5, 8, 3, 8}); //short castling

    int turns = white.getMoveQueueSize() + black.getMoveQueueSize();
    for (int i = 0; i < turns; i++) {
      game.turn.turn();
    }

    assertTrue(game.board.getTileAt(3, 8).getPiece() instanceof King);
    assertTrue(game.board.getTileAt(4, 8).getPiece() instanceof Rook);
  }

  public void testShortCastlingButKingHasMoved() {
    white.addMoveToQueue(new int[] {7, 8, 8, 6}); //knight out of the way
    black.addMoveToQueue(new int[] {1, 2, 1, 3}); //filler
    white.addMoveToQueue(new int[] {5, 7, 5, 6}); //pawn making room for bishop
    black.addMoveToQueue(new int[] {1, 3, 1, 4}); //filler
    white.addMoveToQueue(new int[] {6, 8, 5, 7}); //bishop out of the way
    black.addMoveToQueue(new int[] {1, 4, 1, 5}); //filler
    white.addMoveToQueue(new int[] {5, 8, 6, 8}); //moving king one step
    black.addMoveToQueue(new int[] {2, 2, 2, 3}); //filler
    white.addMoveToQueue(new int[] {6, 8, 5, 8}); //moving king back
    black.addMoveToQueue(new int[] {2, 3, 2, 4}); //filler
    white.addMoveToQueue(new int[] {5, 8, 7, 8}); //short castling attempt

    int turns = white.getMoveQueueSize() + black.getMoveQueueSize();
    for (int i = 0; i < turns; i++) {
      game.turn.turn();
    }

    assertTrue(game.board.getTileAt(5, 8).getPiece() instanceof King);
    assertTrue(game.board.getTileAt(8, 8).getPiece() instanceof Rook);
    assertTrue(game.board.getKing(white).getHasMoved());
  }
}
