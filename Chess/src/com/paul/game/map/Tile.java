package com.paul.game.map;

import com.paul.game.piece.Piece;
import com.paul.game.player.Player;

public class Tile {

  private final Board b;
  
  private final int x;
  private final int y;

  public Tile(Board b, int x, int y) {
    this.b = b;
    
    this.x = x;
    this.y = y;
  }

  /**
   * @return Returns a piece if it exists on this position, otherwise null.
   */
  public Piece getPiece() {
    for (Piece p : b.getPieceList()) {
      if (p.getX() == this.getX() && p.getY() == this.getY()) {
        return p;
      }
    }
    
    return null;
  }
  
  /**
   * @param attacker The player who wants to attack.
   * @return Returns if there is a piece on this tile that this player can attack.
   */
  public boolean hasPieceAndAttackableBy(Player attacker) {
    return this.getPiece() != null
        && this.getPiece().getOwner().isWhite() != attacker.isWhite();
  }
  
  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }
}
