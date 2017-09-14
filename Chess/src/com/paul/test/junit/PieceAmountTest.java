package com.paul.test.junit;

import com.paul.game.Game;
import com.paul.game.piece.Piece;
import com.paul.test.junit.interaction.ChessOutput;
import junit.framework.TestCase;

import java.util.ArrayList;

public class PieceAmountTest extends TestCase {

    private Game game;

    public PieceAmountTest() {
        super("Piece Amount Test");
    }

    protected void setUp() throws Exception {
        super.setUp();

        this.game = new Game(new ChessOutput(), new ChessOutput());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testPieceAmount() {
        assertEquals(32, game.board.getPieceList().size());
    }

    public void testPieceAmountBlack() {
        ArrayList<Piece> piecesBlack = new ArrayList<>();

        for (Piece piece : game.board.getPieceList()) {
            if (!piece.isWhite()) {
                piecesBlack.add(piece);
            }
        }

        assertEquals(16, piecesBlack.size());
    }

    public void testPieceAmountWhite() {
        ArrayList<Piece> piecesWhite = new ArrayList<>();

        for (Piece piece : game.board.getPieceList()) {
            if (piece.isWhite()) {
                piecesWhite.add(piece);
            }
        }

        assertEquals(16, piecesWhite.size());
    }
}
