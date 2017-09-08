package com.paul.game.player;

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
  
  /**
   * Runs when it's the player's turn.
   */
  public abstract void turn();
}
