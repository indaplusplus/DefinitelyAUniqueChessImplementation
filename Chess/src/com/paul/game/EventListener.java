package com.paul.game;

import java.util.ArrayList;

import com.paul.game.piece.Piece;

public interface EventListener {

  /**
   * Calls when a piece is killed.
   * @param attack The killer piece.
   * @param victim The killed piece.
   */
  public void eventPieceKilled(Piece attack, Piece victim);
}
