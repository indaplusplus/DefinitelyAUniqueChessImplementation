package com.paul.game.map;

import com.paul.game.Position;

public class Tile {

  private Position p;

  public Tile(Position p) {
    this.p = p;
  }
  
  public Position getPosition() {
    return this.p;
  }
}
