package com.paul.game.piece;

import com.paul.game.map.Board;
import com.paul.game.map.Tile;
import com.paul.game.player.Player;

import java.util.ArrayList;

public class Knight extends Piece {

  public Knight(Board b, Player owner, int x, int y) {
    super(b, owner, x, y);
  }

  @Override
  public ArrayList<Tile> getAllowedMoves() {
    ArrayList<Tile> allowed = new ArrayList<>();
    
    addTileIfAllowed(allowed, board.getTileAt(this.getX() + 1, this.getY() + 2));
    addTileIfAllowed(allowed, board.getTileAt(this.getX() - 1, this.getY() + 2));
    
    addTileIfAllowed(allowed, board.getTileAt(this.getX() + 1, this.getY() - 2));
    addTileIfAllowed(allowed, board.getTileAt(this.getX() - 1, this.getY() - 2));

    addTileIfAllowed(allowed, board.getTileAt(this.getX() + 2, this.getY() + 1));
    addTileIfAllowed(allowed, board.getTileAt(this.getX() + 2, this.getY() - 1));
    
    addTileIfAllowed(allowed, board.getTileAt(this.getX() - 2, this.getY() + 1));
    addTileIfAllowed(allowed, board.getTileAt(this.getX() - 2, this.getY() - 1));
    
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
}
