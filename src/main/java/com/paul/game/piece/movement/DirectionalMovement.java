package com.paul.game.piece.movement;

import com.paul.game.map.Board;
import com.paul.game.map.Tile;
import com.paul.game.piece.Piece;
import java.util.ArrayList;

public class DirectionalMovement {

  public static final int[] NORTH = {0, 1};
  public static final int[] EAST = {1, 0};
  public static final int[] SOUTH = {0, -1};
  public static final int[] WEST = {-1, 0};
  public static final int[] NORTH_EAST = {1, 1};
  public static final int[] SOUTH_EAST = {1, -1};
  public static final int[] SOUTH_WEST = {-1, -1};
  public static final int[] NORTH_WEST = {-1, 1};
  
  private Piece piece;
  private Board board;
  
  public DirectionalMovement(Piece piece, Board board) {
    this.piece = piece;
    this.board = board;
  }
  
  /**
   * Gets allowed tiles in a direction.
   * @param direction An array where direction[0] is the change in X every step,
   *        and direction[1] the change in Y.
   */
  public ArrayList<Tile> getAllowedMoves(int[] direction) {
    ArrayList<Tile> allowed = new ArrayList<>();
    
    int currentX = this.piece.getX();
    int currentY = this.piece.getY();

    Tile tile = null;
    boolean continueDirectionalCheck = true;
    
    do {
      currentX += direction[0];
      currentY += direction[1];
      
      tile = this.board.getTileAt(currentX, currentY);

      if (tile != null) {
        Piece pieceAtTile = tile.getPiece();
        
        if (pieceAtTile != null) {
          continueDirectionalCheck = false;
          
          if (pieceAtTile.isWhite() != this.piece.isWhite()) {
            allowed.add(tile); //attack
          }
        } else {
          allowed.add(tile);
        }
      } else {
        continueDirectionalCheck = false;
      }
    } while (continueDirectionalCheck);
    
    return allowed;
  }
}
