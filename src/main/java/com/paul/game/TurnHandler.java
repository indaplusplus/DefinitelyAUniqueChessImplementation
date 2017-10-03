package com.paul.game;

import com.paul.game.piece.Pawn;
import com.paul.game.piece.Piece;
import com.paul.game.player.Player;
import com.paul.game.player.PlayerMove;

public class TurnHandler {

  private Game game;

  private int turn = 0;
  private Player[] players = new Player[2];

  private boolean halfMoveJustReset = false;
  private int halfMove = 0;
  private int fullMove = 1;

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
    PlayerMove playerMove = new PlayerMove(game,
        this.getActive(),
        this.getActive().turn());

    if (playerMove.isValidMove()) {
      handleEnPassantVuln();

      playerMove.executeMove();
      this.game.board.promotion.handlePromotions(this.getActive());

      handleMateVariants();

      if (!halfMoveJustReset) {
        halfMove++;
      } else {
        halfMoveJustReset = false;
      }

      if (!this.getActive().isWhite()) {
        fullMove++;
      }

      turn = (turn + 1) % 2;
    }
  }

  /**
   * Removes en passant vulnerable pawns from active player.
   */
  public void handleEnPassantVuln() {
    for (Piece piece : game.board.getPieceList()) {
      if (piece instanceof Pawn
          && piece.getOwner().equals(this.getActive())) {
        Pawn pawn = (Pawn) piece;

        game.board.enPassant.removeVulnerablePawns(this.getActive());
      }
    }
  }

  public void handleMateVariants() {
    if (game.board.getKing(this.getInactive()).isStalemate()) {
      game.callEventStalemate();
    } else if (game.board.getKing(this.getInactive()).isCheck()) {
      if (game.board.getKing(this.getInactive()).isCheckmate()) {
        game.callEventCheckmate(this.getActive());
      } else {
        game.callEventCheck(this.getInactive());
      }
    }
  }

  public void resetHalfMove() {
    halfMove = 0;
    halfMoveJustReset = true;
  }

  public int getHalfMove() {
    return halfMove;
  }

  public int getFullMove() {
    return fullMove;
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
