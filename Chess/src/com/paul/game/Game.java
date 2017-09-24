package com.paul.game;

import com.paul.game.listeners.EventListener;
import com.paul.game.map.Board;
import com.paul.game.piece.Piece;
import com.paul.game.player.Player;

import java.util.ArrayList;

public class Game {

  public static int width = 8;
  public static int height = 8;
  
  private ArrayList<EventListener> eventListeners = new ArrayList<>();
  
  public TurnHandler turn;
  public Board board;

  public Game(Player player1, Player player2) {
    this.turn = new TurnHandler(this, player1, player2);
    this.board = new Board(this);
  }
  
  public void addEventListener(EventListener el) {
    this.eventListeners.add(el);
  }
  
  public void callEventPieceKilled(Piece attack, Piece victim) {
    for (EventListener el : eventListeners) {
      el.eventPieceKilled(attack, victim);
    }
  }
  
  public void callEventCheck(Player victim) {
    for (EventListener el : eventListeners) {
      el.eventCheck(victim);
    }
  }
  
  public void callEventCheckmate(Player winner) {
    for (EventListener el : eventListeners) {
      el.eventCheckmate(winner);
    }
  }
  
  public void callEventStalemate() {
    for (EventListener el : eventListeners) {
      el.eventStalemate();
    }
  }

  public void callEventPromotion(Piece piece) {
    for (EventListener el : eventListeners) {
      el.eventPromotion(piece);
    }
  }
}
