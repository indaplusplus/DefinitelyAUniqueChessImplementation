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
        && (tile.getPiece() == null || tile.hasPieceAndAttackableBy(this.getOwner()));
  }
  
  /**
   * @return Returns if the position of the king is causing a check.
   */
  public boolean isCheck() {
    Tile kingTile = this.board.getTileAt(this.getX(), this.getY());
    
    for (Piece piece : this.board.getPieceList()) {
      if (piece.isWhite() != this.isWhite()
          && piece.isEnabled()
          && piece.getAllowedMoves().contains(kingTile)) {
        return true;
      }
    }
    
    return false;
  }

  /**
   * @param piece The piece to move
   * @param tile The target tile
   * @return Returns if there is a check with the king at a specific tile
   */
  public boolean isCheckWithPieceAt(Piece piece, Tile tile) {
    int oldX = piece.getX();
    int oldY = piece.getY();

    piece.setX(tile.getX());
    piece.setY(tile.getY());
    
    boolean stalemate = this.isCheck();
    
    piece.setX(oldX);
    piece.setY(oldY);
    
    return stalemate;
  }
  
  public boolean isCheckmate() {
    return this.isCheck() && !this.playerHasLegalMoves();
  }
  
  public boolean isStalemate() {
    return !this.isCheck() && !this.playerHasLegalMoves();
  }
  
  /**
   * @return Returns if the owner of this king can move any piece legally.
   */
  private boolean playerHasLegalMoves() {
    for (Piece piece : board.getPieceList()) {
      if (piece.isWhite() == this.isWhite()) {
        ArrayList<Tile> allowedMoves = piece.getAllowedMoves();

        int oldX = piece.getX();
        int oldY = piece.getY();
        
        for (Tile tile : allowedMoves) {
          boolean hasEnemyPiece = tile.hasPieceAndAttackableBy(this.getOwner());
          if (hasEnemyPiece) {
            tile.getPiece().setEnabled(false);
          }
          
          piece.setX(tile.getX());
          piece.setY(tile.getY());
          
          boolean check = this.isCheck();
          
          if (hasEnemyPiece) {
            tile.getPiece().setEnabled(true);
          }
          
          if (!check) {
            piece.setX(oldX);
            piece.setY(oldY);
            return true;
          }
        }
        
        piece.setX(oldX);
        piece.setY(oldY);
      }
    }
    
    return false;
  }
}
