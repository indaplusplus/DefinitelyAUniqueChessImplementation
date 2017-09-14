package com.paul.game.piece.movement;

import com.paul.game.map.Board;
import com.paul.game.map.Tile;
import com.paul.game.piece.King;
import com.paul.game.piece.Piece;
import com.paul.game.piece.Rook;
import java.util.ArrayList;

public class Castling {

  private Board board;

  public Castling(Board board) {
    this.board = board;
  }

  public ArrayList<Tile> getAllowedCastlingMoves(King king) {
    ArrayList<Tile> allowed = new ArrayList<>();

    if (!king.getHasMoved() && !king.isCheck()) {
      for (Piece piece : this.board.getPieceList()) {
        if (piece instanceof Rook
            && piece.isWhite() == king.isWhite()
            && this.isCastlingAllowed(king, (Rook) piece)) {

          int displacementDirection = (int) Math.signum(piece.getX() - king.getX());
          int targetTileX = king.getX() + (2 * displacementDirection);

          allowed.add(this.board.getTileAt(targetTileX, king.getY()));
        }
      }
    }

    return allowed;
  }

  public void moveComplementaryRook(King king, Tile kingCastlingMove) {
    Rook rook = null;

    for (Piece piece : this.board.getPieceList()) {
      if (piece instanceof Rook
          && piece.isWhite() == king.isWhite()) {

        if (rook == null
            || Math.abs(piece.getX() - kingCastlingMove.getX()) < Math.abs(rook.getX() - kingCastlingMove.getX())) {
          rook = (Rook) piece;
        }
      }
    }

    int displacementDirection = (int) Math.signum(kingCastlingMove.getX() - rook.getX());

    rook.moveTo(this.board.getTileAt(
        kingCastlingMove.getX() + displacementDirection,
        kingCastlingMove.getY()));
  }

  public boolean isCastlingAllowed(King king, Rook rook) {
    return !king.getHasMoved()
        && !rook.getHasMoved()
        && !isPathBlockedOrCheck(king, rook);
  }

  public boolean isPathBlockedOrCheck(King king, Rook rook) {
    int searchDistanceX = Math.abs(rook.getX() - king.getX());
    int searchDirection = (int) Math.signum(rook.getX() - king.getX());

    if (searchDirection == 0) {
      return true;
    }

    for (int x = king.getX() + searchDirection;
        x != king.getX() + (searchDirection * searchDistanceX);
        x += searchDirection) {

      Tile tile = this.board.getTileAt(x, king.getY());

      if (tile.getPiece() != null
          && !king.isCheckWithPieceAt(king, tile)) {
        return true;
      }
    }

    return false;
  }
}
