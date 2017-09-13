package com.paul.test;

import com.paul.game.listeners.EventListener;
import com.paul.game.piece.King;
import com.paul.game.piece.Piece;
import com.paul.game.player.Player;

public class ChessEvents implements EventListener {

  @Override
  public void eventPieceKilled(Piece attack, Piece victim) {
    if (victim instanceof King) {
      Main.continueGame = false;
    }
  }

  @Override
  public void eventStalemate() {
    System.out.println("Stalemate");
    System.exit(0);
  }

  @Override
  public void eventCheckmate(Player winner) {
    System.out.println("Checkmate");
    System.exit(0);
  }

  @Override
  public void eventCheck(Player victim) {
    System.out.println("Check");
  }
}
