package com.paul.game.player;

import java.util.ArrayList;

import com.paul.game.piece.Piece;

public abstract class Player {
  
  private Boolean white;
  
  /**
   * Can only be set once.
   * @param white Is this player playing the white side?
   */
  public final void setWhite(boolean white) {
    if (this.white == null) {
      this.white = white;
    }
  }
  
  public final boolean isWhite() {
    return this.white;
  }
  
  /**
   * Runs when it's the player's turn.
   * 
   * @return Returns an int array with coordinates from where to move.
   * {0, 0, 1, 1} would be from (0, 0) to (1, 1) 
   */
  public abstract int[] turn();
  
  public abstract int promotion();
}
