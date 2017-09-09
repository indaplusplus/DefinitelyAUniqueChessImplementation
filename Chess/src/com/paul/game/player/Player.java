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
  
  public final boolean isWhite() {
    return this.white;
  }
  
  /**
   * Runs when it's the player's turn.
   * 
   * @return Return a string of from where to where to move. I.e. "a2 a4"
   */
  public abstract String turn();
}
