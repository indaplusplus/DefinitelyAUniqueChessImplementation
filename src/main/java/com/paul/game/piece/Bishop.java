package com.paul.game.piece;

import com.paul.game.map.Board;
import com.paul.game.map.Tile;
import com.paul.game.piece.movement.DirectionalMovement;
import com.paul.game.player.Player;
import java.util.ArrayList;

public class Bishop extends Piece {

  public Bishop(Board b, Player owner, int x, int y) {
    super(b, owner, x, y);
  }

  @Override
  public ArrayList<Tile> getAllowedMoves() {
    ArrayList<Tile> allowed = new ArrayList<>();
    
    DirectionalMovement directionalMovement = new DirectionalMovement(this, this.board);
    
    allowed.addAll(directionalMovement.getAllowedMoves(directionalMovement.NORTH_EAST));
    allowed.addAll(directionalMovement.getAllowedMoves(directionalMovement.SOUTH_EAST));
    allowed.addAll(directionalMovement.getAllowedMoves(directionalMovement.NORTH_WEST));
    allowed.addAll(directionalMovement.getAllowedMoves(directionalMovement.SOUTH_WEST));

    return allowed;
  }
}
