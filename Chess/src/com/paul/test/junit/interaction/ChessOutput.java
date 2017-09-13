package com.paul.test.junit.interaction;

import com.paul.game.player.Player;

public class ChessOutput extends Player {

    @Override public int[] turn() {
        return new int[] {0, 0, 0, 0};
    }

    @Override public int promotion() {
        return 0;
    }
}
