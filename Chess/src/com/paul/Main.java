package com.paul;

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

  public static void main(String[] args) {
    Game game = new Game(new HumanPlayer(), new HumanPlayer());
    
    for (int i = 8; i >= 1; i--) {
      for (int j = 1; j <= 8; j++) {
        printProper(game.board.getTileAt(i, j));
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
      System.out.print(0);
    }
  }
}
