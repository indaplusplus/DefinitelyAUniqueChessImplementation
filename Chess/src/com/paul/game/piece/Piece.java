package com.paul.game.piece;

import com.paul.game.Position;
import com.paul.game.map.Board;
import com.paul.game.map.Tile;
import com.paul.game.player.Player;

import java.util.ArrayList;

public abstract class Piece {

  private final Board b;
  private Player owner;
  
  private int x;
  private int y;

  /**
   * @param owner The player who owns this piece.
   * @param x Start position X.
   * @param y Start position Y.
   */
  public Piece(Board b, Player owner, int x, int y) {
    this.b = b;
    this.owner = owner;
    
    this.x = x;
    this.y = y;
  }

  public abstract ArrayList<Tile> getAllowedMoves();

  public boolean isAllowedTile(Tile t) {
    for (Tile allowed : this.getAllowedMoves()) {
      if (t.getX() == allowed.getX() && t.getY() == allowed.getY()) {
        return true;
      }
    }
    
    return false;
  }
  
  public void moveTo(Tile t) {
    if (this.isAllowedTile(t)) {
      Piece p = t.getPiece();
      
      this.setX(t.getX());
      this.setY(t.getY());
      
      if (p != null) {
        b.killPiece(p);
      }
    }
  }
  
  public Player getOwner() {
    return this.owner;
  }
  
  public void setOwner(Player owner) {
    this.owner = owner;
  }
  
  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }
  
  public void setX(int x) {
    this.x = x;
  }
  
  public void setY(int y) {
    this.y = y;
  }
}
