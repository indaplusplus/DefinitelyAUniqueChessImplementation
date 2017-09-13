package com.paul.game.piece;

import com.paul.game.map.Board;
import com.paul.game.map.Tile;
import com.paul.game.player.Player;

import java.util.ArrayList;

public abstract class Piece {

  protected final Board board;
  private Player owner;
  
  private boolean enabled;
  private int x;
  private int y;

  /**
   * @param owner The player who owns this piece.
   * @param x Start position X.
   * @param y Start position Y.
   */
  public Piece(Board board, Player owner, int x, int y) {
    this.board = board;
    this.owner = owner;

    this.enabled = true;
    this.x = x;
    this.y = y;
  }

  public abstract ArrayList<Tile> getAllowedMoves();
  
  /**
   * @param t Which tile to move to.
   * @return Returns if this piece can move there.
   */
  public boolean isAllowedMove(Tile t) {
    for (Tile allowed : this.getAllowedMoves()) {
      if (t.getX() == allowed.getX() && t.getY() == allowed.getY()) {
        return true;
      }
    }
    
    return false;
  }
  
  /**
   * Moves the piece if the move is allowed.
   * @param t Which tile to move to.
   */
  public void moveTo(Tile t) {
    if (this.isEnabled()
        && this.isAllowedMove(t)) {
      Piece p = t.getPiece();
      
      this.setX(t.getX());
      this.setY(t.getY());
      
      if (p != null && p.isWhite() != this.isWhite()) {
        this.killPiece(p);
      }
    }
  }
  
  public void killPiece(Piece p) {
    this.board.killPiece(this, p);
  }
  
  public boolean isWhite() {
    return this.getOwner().isWhite();
  }
  
  public Player getOwner() {
    return this.owner;
  }
  
  public void setOwner(Player owner) {
    this.owner = owner;
  }
  
  public boolean isEnabled() {
    return this.enabled;
  }
  
  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
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
