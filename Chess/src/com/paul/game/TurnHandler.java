package com.paul.game;

import com.paul.game.player.Player;

public class TurnHandler {

  private int turn = 0;
  private Player[] players;

  /**
   * @param player1 Player 1 - white.
   * @param player2 Player 2 - black.
   */
  public TurnHandler(Player player1, Player player2) {
    this.players[0] = player1;
    this.players[1] = player2;
    
    player1.setWhite(true);
    player2.setWhite(false);
  }

  public Player turn() {
    turn = (turn + 1) % 2;
    return players[turn];
  }
}
