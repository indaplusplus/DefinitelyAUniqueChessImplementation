package com.paul.game;

import com.paul.game.map.Tile;
import com.paul.game.piece.Piece;
import com.paul.game.player.Player;

public class TurnHandler {

  private Game game;
  
  private int turn = 0;
  private Player[] players = new Player[2];

  /**
   * @param player1 Player 1 - white.
   * @param player2 Player 2 - black.
   */
  public TurnHandler(Game game, Player player1, Player player2) {
    this.game = game;
    
    this.players[0] = player1;
    this.players[1] = player2;
    
    player1.setWhite(true);
    player2.setWhite(false);
  }

  public void turn() {
    boolean valid = false;
    
    Piece piece = null;
    Tile tile = null;
    
    do {
      int[] move = this.getActive().turn();
      
      piece = game.board.getTileAt(move[0], move[1]).getPiece();
      tile = game.board.getTileAt(move[2], move[3]);
      
      valid = piece.isAllowedMove(tile)
          && !(move[0] == move[2] && move[1] == move[3])
          && piece.getOwner().equals(this.getActive());
      
    } while (!valid);
    
    piece.moveTo(tile);
    
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
