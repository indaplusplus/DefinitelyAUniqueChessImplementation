package com.paul.game;

import com.paul.game.player.Player;

public class Game {

  public TurnHandler turn;

  public Game(Player player1, Player player2) {
    this.turn = new TurnHandler(player1, player2);
  }
}
