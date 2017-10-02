package com.paul.game.piece.movement;

import com.paul.game.map.Board;
import com.paul.game.piece.Bishop;
import com.paul.game.piece.Knight;
import com.paul.game.piece.Pawn;
import com.paul.game.piece.Piece;
import com.paul.game.piece.Queen;
import com.paul.game.piece.Rook;
import com.paul.game.player.Player;
import java.util.ArrayList;

public class Promotion {

  public static final int QUEEN = 0;
  public static final int KNIGHT = 1;
  public static final int ROOK = 2;
  public static final int BISHOP = 3;

  private Board board;

  public Promotion(Board board) {
    this.board = board;
  }

  public void handlePromotions(Player player) {
    for (Piece piece : (ArrayList<Piece>) this.board.getPieceList().clone())
      if (piece instanceof Pawn
          && piece.isWhite() == player.isWhite()
          && isInRightEnd((Pawn) piece)) {
        Piece newPiece = null;
        switch (player.promotion()) {
          case QUEEN:
            newPiece = new Queen(board, player, piece.getX(), piece.getY());
            break;
          case KNIGHT:
            newPiece = new Knight(board, player, piece.getX(), piece.getY());
            break;
          case ROOK:
            newPiece = new Rook(board, player, piece.getX(), piece.getY());
            break;
          case BISHOP:
            newPiece = new Bishop(board, player, piece.getX(), piece.getY());
            break;
        }
        this.board.addPiece(newPiece);
        this.board.getPieceList().remove(piece);
        this.board.game.callEventPromotion(newPiece);
      }
  }

  public boolean isInRightEnd(Pawn pawn) {
    return (pawn.isWhite() && pawn.getY() == 1) || (!pawn.isWhite() && pawn.getY() == 8);
  }
}
