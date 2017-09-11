package com.paul.game;

import com.paul.game.map.Tile;
import com.paul.game.piece.Pawn;
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
    Tile moveFromTile = null;
    Tile moveToTile = null;
    
    do {
      int[] move = this.getActive().turn();
      
      if (move[0] == move[2] && move[1] == move[3]) {
        continue;
      }
      
      moveFromTile = game.board.getTileAt(move[0], move[1]);
      moveToTile = game.board.getTileAt(move[2], move[3]);
      
      if (moveFromTile == null || moveToTile == null) {
        continue;
      }
      
      piece = moveFromTile.getPiece();
      
      valid = piece != null
          && piece.isAllowedMove(moveToTile)
          && piece.getOwner().equals(this.getActive());
      
    } while (!valid);
    
    piece.moveTo(moveToTile);

    if (game.board.getKing(this.getInactive()).isStalemate()) {
      game.callEventStalemate();
    } else if (game.board.getKing(this.getInactive()).isCheck()) {
      if (game.board.getKing(this.getInactive()).isCheckmate()) {
        game.callEventCheckmate(this.getActive());
      } else {
        game.callEventCheck(this.getInactive());
      }
    }
    
    turn = (turn + 1) % 2;
  }
  
  public void handleEnPassantVuln() {
    for (Piece piece : game.board.getPieceList()) {
      if (piece instanceof Pawn
          && piece.getOwner().equals(this.getActive())) {
        Pawn pawn = (Pawn) piece;
        
        pawn.setEnPassantVuln(false);
      }
    }
  }
  
  public Player getActive() {
    return this.players[turn];
  }
  
  public Player getInactive() {
    return this.players[(turn + 1) % 2];
  }
  
  public Player getWhite() {
    return this.players[0];
  }
  
  public Player getBlack() {
    return this.players[1];
  }
}
