package com.paul.game.piece;

import com.paul.game.Position;
import com.paul.game.map.Tile;
import com.paul.game.player.Player;

import java.util.ArrayList;

public abstract class Piece {

  private Player owner;
  
  private Position p;

  /**
   * @param owner The player who owns this piece.
   * @param x Start position X.
   * @param y Start position Y.
   */
  public Piece(Player owner, Position p) {
    this.owner = owner;
    
    this.p = p;
  }

  public abstract ArrayList<Tile> getAllowedMoves();

  public Player getOwner() {
    return this.owner;
  }
  
  public void setOwner(Player owner) {
    this.owner = owner;
  }
  
  public Position getPosition() {
    return this.p;
  }
}
