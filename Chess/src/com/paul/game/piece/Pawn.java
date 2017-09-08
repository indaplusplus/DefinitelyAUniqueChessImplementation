package com.paul.game.piece;

import com.paul.game.Position;
import com.paul.game.map.Tile;
import com.paul.game.player.Player;

import java.util.ArrayList;

public class Pawn extends Piece {

  public Pawn(Player owner, Position p) {
    super(owner, p);
  }

  @Override
  public ArrayList<Tile> getAllowedMoves() {
    return null;
  }
}
