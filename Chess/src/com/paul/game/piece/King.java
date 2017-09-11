package com.paul.game.piece;

import com.paul.game.map.Board;
import com.paul.game.map.Tile;
import com.paul.game.player.Player;

import java.util.ArrayList;

public class King extends Piece {

  public King(Board board, Player owner, int x, int y) {
    super(board, owner, x, y);
  }

  @Override
  public ArrayList<Tile> getAllowedMoves() {
    ArrayList<Tile> allowed = new ArrayList<>();
    
    addTileIfAllowed(allowed, board.getTileAt(this.getX() + 1, this.getY()));
    addTileIfAllowed(allowed, board.getTileAt(this.getX() + 1, this.getY() + 1));
    addTileIfAllowed(allowed, board.getTileAt(this.getX(), this.getY() + 1));
    addTileIfAllowed(allowed, board.getTileAt(this.getX() - 1, this.getY() + 1));
    addTileIfAllowed(allowed, board.getTileAt(this.getX() - 1, this.getY()));
    addTileIfAllowed(allowed, board.getTileAt(this.getX() - 1, this.getY() - 1));
    addTileIfAllowed(allowed, board.getTileAt(this.getX(), this.getY() - 1));
    addTileIfAllowed(allowed, board.getTileAt(this.getX() + 1, this.getY() - 1));
    
    return allowed;
  }
  
  public void addTileIfAllowed(ArrayList<Tile> allowed, Tile tile) {
    if (this.isTileAllowed(tile)) {
      allowed.add(tile);
    }
  }
  
  public boolean isTileAllowed(Tile tile) {
    return tile != null
        && (tile.getPiece() == null || tile.hasPieceAndAttackableBy(this.getOwner()))
        && (board.game.turn.getActive().equals(this.getOwner()) && !isCheckAt(tile));
  }
  
  /**
   * @return Returns if the position of the king is causing a check.
   */
  public boolean isCheck() {
    Tile kingTile = this.board.getTileAt(this.getX(), this.getY());
    
    for (Piece piece : this.board.getPieceList()) {
      if (piece.isWhite() != this.isWhite() && piece.getAllowedMoves().contains(kingTile)) {
        return true;
      }
    }
    
    return false;
  }
  
  //none of the check/checkmate/stalemate methods account for the possibility of taking out enemies
  
  /**
   * @param x X coordinate for the king
   * @param y Y coordinate for the king
   * @return Returns if there is a check with the king at a specific tile
   */
  public boolean isCheckAt(Tile tile) {
    int oldX = this.getX();
    int oldY = this.getY();

    this.setX(tile.getX());
    this.setY(tile.getY());
    
    boolean stalemate = this.isCheck();
    
    this.setX(oldX);
    this.setY(oldY);
    
    return stalemate;
  }
  
  public boolean isCheckmate() {
    for (Piece piece : this.board.getPieceList()) { //does any possible move affect the check?
      if (piece.isWhite() == this.isWhite()) {
        ArrayList<Tile> allowedMoves = piece.getAllowedMoves();

        int oldX = piece.getX();
        int oldY = piece.getY();
        
        for (Tile tile : allowedMoves) {
          piece.setX(tile.getX());
          piece.setY(tile.getY());
          
          boolean check = this.isCheck();
          
          if (!check) {
            piece.setX(oldX);
            piece.setY(oldY);
            return false;
          }
        }
        
        piece.setX(oldX);
        piece.setY(oldY);
      }
    }
    
    return this.isCheck();
  }
  
  public boolean isStalemate() {
    if (this.isCheck()) {
      return false;
    } else {
      ArrayList<Tile> kingTiles = this.getAllowedMoves();
      int oldKingX = this.getX();
      int oldKingY = this.getY();
      
      for (Tile kingTile : kingTiles) {
        this.setX(kingTile.getX());
        this.setY(kingTile.getY());
        
        for (Piece piece : board.getPieceList()) {
          if (piece.isWhite() == this.isWhite() && !(piece instanceof King)) {
            ArrayList<Tile> allowedMoves = piece.getAllowedMoves();

            int oldX = piece.getX();
            int oldY = piece.getY();
            
            for (Tile tile : allowedMoves) {
              piece.setX(tile.getX());
              piece.setY(tile.getY());
              
              boolean check = this.isCheck();
              
              if (!check) {
                piece.setX(oldX);
                piece.setY(oldY);
                this.setX(oldKingX);
                this.setY(oldKingY);
                return false;
              }
            }
            
            piece.setX(oldX);
            piece.setY(oldY);
          }
        }
      }
      
      this.setX(oldKingX);
      this.setY(oldKingY);
    }
    
    return true;
  }
}
