package com.paul.game.player;

import com.paul.game.Game;
import com.paul.game.map.Tile;
import com.paul.game.piece.Pawn;
import com.paul.game.piece.Piece;

public class PlayerMove {

  private Game game;
  private Player player;
  private int[] move;

  public PlayerMove(Game game, Player player, int[] move) {
    this.game = game;
    this.player = player;
    this.move = move;
  }

  public boolean isValidMove() {
    if (this.move[0] == this.move[2]
        && this.move[1] == this.move[3]) {
      return false;
    }

    Tile moveFromTile = game.board.getTileAt(move[0], move[1]);
    Tile moveToTile = game.board.getTileAt(move[2], move[3]);

    if (moveFromTile == null || moveToTile == null) {
      return false;
    }

    Piece piece = moveFromTile.getPiece();

    return piece != null
        && piece.isAllowedMove(moveToTile)
        && piece.getOwner().equals(this.player)
        && !game.board.getKing(this.player).isCheckWithPieceAt(piece, moveToTile);
  }

  public void executeMove() {
    Tile moveFromTile = game.board.getTileAt(move[0], move[1]);
    Tile moveToTile = game.board.getTileAt(move[2], move[3]);

    Piece piece = moveFromTile.getPiece();

    if (piece instanceof Pawn) {
      game.turn.resetHalfMove();
    }

    piece.moveTo(moveToTile);
  }
}
