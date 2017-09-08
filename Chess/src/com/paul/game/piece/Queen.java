package com.paul.game.piece;

import com.paul.game.Position;
import com.paul.game.map.Tile;
import com.paul.game.player.Player;

import java.util.ArrayList;

public class Queen extends Piece {

  public Queen(Player owner, Position p) {
    super(owner, p);
  }

  @Override
  public ArrayList<Tile> getAllowedMoves() {
    return null;
  }
}
