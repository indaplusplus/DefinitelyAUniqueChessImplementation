package com.paul.test;

import com.paul.game.Game;
import com.paul.game.map.Tile;
import com.paul.game.piece.Bishop;
import com.paul.game.piece.King;
import com.paul.game.piece.Knight;
import com.paul.game.piece.Pawn;
import com.paul.game.piece.Piece;
import com.paul.game.piece.Queen;
import com.paul.game.piece.Rook;

public class Main {
  
  public static boolean continueGame = true;
  
  public static Game game = new Game(new HumanPlayer(0), new HumanPlayer(1));
  
  public static void main(String[] args) {
    game.addEventListener(new ChessEvents());
    
    while (continueGame) {
      print();
      game.turn.turn();
    }
    
    for (int i = 0; i < 200; i++) {
      print();
      game.turn.turn();
    }
  }
  
  public static void print() {
    System.out.println();
    for (int y = 1; y <= 8; y++) {
      for (int x = 1; x <= 8; x++) {
        printProper(game.board.getTileAt(x, y));
      }
      System.out.println();
    }
  }
  
  public static void printProper(Tile t) {
    Piece p = t.getPiece();

    if (p != null) {
      if (p instanceof Pawn) {
        System.out.print("P");
      } else if (p instanceof Bishop) {
        System.out.print("B");
      } else if (p instanceof Rook) {
        System.out.print("R");
      } else if (p instanceof Knight) {
        System.out.print("N");
      } else if (p instanceof Queen) {
        System.out.print("Q");
      } else if (p instanceof King) {
        System.out.print("K");
      }
    } else {
      System.out.print(".");
    }
  }
}
