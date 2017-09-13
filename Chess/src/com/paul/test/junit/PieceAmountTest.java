package com.paul.test.junit;

import com.paul.game.Game;
import com.paul.test.junit.interaction.ChessOutput;
import junit.framework.TestCase;

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
}
