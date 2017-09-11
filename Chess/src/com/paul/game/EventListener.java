package com.paul.game;

import com.paul.game.piece.Piece;
import com.paul.game.player.Player;

public interface EventListener {

  /**
   * Calls when a piece is killed.
   * @param attack The killer piece.
   * @param victim The killed piece.
   */
  public void eventPieceKilled(Piece attack, Piece victim);
  
  /**
   * Calls when stalemate occurs.
   */
  public void eventStalemate();
  
  /**
   * Calls when checkmate occurs.
   * @param winner The player who won.
   */
  public void eventCheckmate(Player winner);
  
  /**
   * Calls when check occurs.
   * @param victim The victim of the attack.
   */
  public void eventCheck(Player victim);
}
