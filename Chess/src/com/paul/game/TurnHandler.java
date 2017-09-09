package com.paul.game;

import com.paul.game.player.Player;

public class TurnHandler {

  private int turn = 0;
  private Player[] players = new Player[2];

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

  public void turn() {
    boolean valid = false;
    do {
      String move = this.getActive().turn();
      
      Piece p = game.board.getTileAt(move[0]).getPiece();
      Tile tile = game.board.getTileAt(move]1]);
      valid = p.isAllowedTile(tile);
      
    } while(!valid);
    
    turn = (turn + 1) % 2;
  }
  
  public Player getActive() {
    return this.players[turn];
  }
  
  public Player getWhite() {
    return this.players[0];
  }
  
  public Player getBlack() {
    return this.players[1];
  }
}
