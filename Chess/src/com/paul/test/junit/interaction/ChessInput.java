package com.paul.test.junit.interaction;

import com.paul.game.listeners.EventListener;
import com.paul.game.piece.Piece;
import com.paul.game.player.Player;

public class ChessInput implements EventListener {


  @Override
  public void eventPieceKilled(Piece attack, Piece victim) {

  }

  @Override
  public void eventStalemate() {

  }

  @Override
  public void eventCheckmate(Player winner) {

  }

  @Override
  public void eventCheck(Player victim) {

  }

  @Override
  public void eventPromotion(Piece piece) {

  }
}
