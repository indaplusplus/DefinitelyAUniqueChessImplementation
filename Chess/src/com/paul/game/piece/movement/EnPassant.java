package com.paul.game.piece.movement;

import com.paul.game.map.Board;
import com.paul.game.map.Tile;
import com.paul.game.piece.Pawn;
import com.paul.game.player.Player;

import java.util.ArrayList;

public class EnPassant {

  private ArrayList<Pawn> vulnerablePawns = new ArrayList<>();

  private Board board;

  public EnPassant(Board board) {
    this.board = board;
  }

  public void addVulnerablePawn(Pawn pawn) {
    vulnerablePawns.add(pawn);
  }

  public void removeVulnerablePawns(Player player) {
    for (Pawn pawn : (ArrayList<Pawn>) this.vulnerablePawns.clone()) {
      if (pawn.getOwner().isWhite() == player.isWhite()) {
        vulnerablePawns.remove(pawn);
      }
    }
  }

  public boolean isEnPassantVuln(Pawn pawn) {
    return this.vulnerablePawns.contains(pawn);
  }

  public ArrayList<Tile> getAllowedEnPassants(Pawn pawn) {
    ArrayList<Tile> allowed = new ArrayList<Tile>();

    addEnPassantAt(allowed, pawn, -1);
    addEnPassantAt(allowed, pawn, 1);

    return allowed;
  }

  /**
   * Adds en passant to a list, where dx is the difference in x in relation to the pawn's x.
   *
   * @param allowed The list to add the allowed tile to.
   * @param pawn The pawn to calculate from.
   * @param dx Difference in x.
   */
  private void addEnPassantAt(ArrayList<Tile> allowed, Pawn pawn, int dx) {
    int direction = pawn.isWhite() ? -1 : 1;

    Tile enPassantVictimTile = this.board.getTileAt(pawn.getX() + dx, pawn.getY());
    Tile enPassantDestinationTile = this.board.getTileAt(pawn.getX() + dx, pawn.getY() + direction);

    if (enPassantVictimTile != null
        && enPassantDestinationTile != null
        && enPassantVictimTile.hasPieceAndAttackableBy(pawn.getOwner())
        && enPassantVictimTile.getPiece() instanceof Pawn
        && (this.isEnPassantVuln((Pawn) enPassantVictimTile.getPiece()))) {
      allowed.add(enPassantDestinationTile);
    }
  }
}
