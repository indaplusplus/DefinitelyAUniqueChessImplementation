package com.paul.game.piece;

import com.paul.game.map.Board;
import com.paul.game.map.Tile;
import com.paul.game.player.Player;

import java.util.ArrayList;

public class Queen extends Piece {

  public Queen(Board b, Player owner, int x, int y) {
    super(b, owner, x, y);
  }

  @Override
  public ArrayList<Tile> getAllowedMoves() {
    ArrayList<Tile> allowed = new ArrayList<>();

    addAllowedTilesInDirection(allowed, 1, 0);
    addAllowedTilesInDirection(allowed, -1, 0);
    addAllowedTilesInDirection(allowed, 0, 1);
    addAllowedTilesInDirection(allowed, 0, -1);
    
    addAllowedTilesInDirection(allowed, 1, 1);
    addAllowedTilesInDirection(allowed, -1, -1);
    addAllowedTilesInDirection(allowed, -1, 1);
    addAllowedTilesInDirection(allowed, 1, -1);
    
    return allowed;
  }
  
  /**
   * Adds allowed tiles in a direction.
   * @param allowed The list to add to.
   * @param dx Change in the x plane.
   * @param dy Change in the y plane.
   */
  public void addAllowedTilesInDirection(ArrayList<Tile> allowed, int dx, int dy) {
    int currentX = this.getX();
    int currentY = this.getY();
    
    Tile tile = null;
    boolean continueDirectionalCheck = true;
    
    do {
      currentX += dx;
      currentY += dy;
      
      tile = this.board.getTileAt(currentX, currentY);
      
      if (tile != null) {
        Piece pieceAtTile = tile.getPiece();
        
        if (pieceAtTile != null) {
          continueDirectionalCheck = false;
          
          if (pieceAtTile.isWhite() != this.isWhite()) {
            allowed.add(tile); //attack
          }
        } else {
          allowed.add(tile);
        }
      } else {
        continueDirectionalCheck = false;
      }
    } while (continueDirectionalCheck);
  }
}
