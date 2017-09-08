package com.paul.game.map;

import com.paul.game.Position;
import com.paul.game.piece.Piece;

import java.util.ArrayList;

public class Board {
  
  private ArrayList<Tile> tileList = new ArrayList<Tile>();
  private ArrayList<Piece> pieceList = new ArrayList<Piece>();
  
  public Board() {
    generateBoard();
  }
  
  private void generateBoard() {
    for (int i = 8; i >= 1; i--) {
      for (int j = 1; j <= 8; j++) {
        tileList.add(new Tile(new Position(i, j)));
      }
    }
  }
  
  /**
   * @param x X position
   * @param y Y position
   * @return The tile at (x, y) or null if invalid.
   */
  public Tile getTileAt(int x, int y) {
    for (Tile t : this.getTileList()) {
      if (t.getPosition().getX() == x && t.getPosition().getY() == y) {
        return t;
      }
    }
    
    return null;
  }
  
  public ArrayList<Tile> getTileList() {
    return this.tileList;
  }
  
  public ArrayList<Piece> getPieceList() {
    return this.pieceList;
  }
}
