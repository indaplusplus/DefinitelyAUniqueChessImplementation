package com.paul.game.piece;

import com.paul.game.Position;
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
    return null;
  }
}
