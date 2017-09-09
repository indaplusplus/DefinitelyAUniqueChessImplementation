package com.paul.game.map;

import com.paul.game.Position;
import com.paul.game.piece.Piece;

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
  
  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }
}
