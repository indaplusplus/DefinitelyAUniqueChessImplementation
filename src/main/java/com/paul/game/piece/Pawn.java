package com.paul.game.piece;

import com.paul.game.map.Board;
import com.paul.game.map.Tile;
import com.paul.game.player.Player;
import java.util.ArrayList;

public class Pawn extends Piece {
  
  public Pawn(Board board, Player owner, int x, int y) {
    super(board, owner, x, y);
  }

  @Override
  public ArrayList<Tile> getAllowedMoves() {
    ArrayList<Tile> allowed = new ArrayList<>();
    
    //if the owner is white, the pawn moves up the board, otherwise down
    int direction = this.isWhite() ? -1 : 1; 
    
    addAllowedBasicMovement(allowed, direction);
    addAllowedBasicAttacks(allowed, direction);
    allowed.addAll(this.board.enPassant.getAllowedEnPassants(this));
    
    return allowed;
  }

  @Override
  public void moveTo(Tile t) {
    int prevX = this.getX();
    int prevY = this.getY();
    
    if (this.isAllowedMove(t)) {
      if (this.board.enPassant.getAllowedEnPassants(this).contains(t)) {
        Tile vulnPieceTile = board.getTileAt(t.getX(), this.getY());
        this.killPiece(vulnPieceTile.getPiece());
      } else {
        Piece p = t.getPiece();
        
        if (p != null && p.isWhite() != this.isWhite()) {
          this.killPiece(p);
        }
      }
      
      this.setX(t.getX());
      this.setY(t.getY());
    }
    
    if (this.getX() == prevX && Math.abs(this.getY() - prevY) == 2) {
      this.board.enPassant.addVulnerablePawn(this);
    }
  }
  
  private void addAllowedBasicMovement(ArrayList<Tile> allowed, int direction) {
    Tile forwardOne = this.board.getTileAt(this.getX(), this.getY() + direction);
    
    if (forwardOne != null && forwardOne.getPiece() == null) {
      allowed.add(forwardOne);
      
      if ((this.isWhite() && this.getY() == 7) || (!this.isWhite() && this.getY() == 2)) {
        Tile forwardTwo = this.board.getTileAt(this.getX(), this.getY() + (direction * 2));
        
        if (forwardTwo != null && forwardTwo.getPiece() == null) {
          allowed.add(forwardTwo);
        }
      }
    }
  }
  
  private void addAllowedBasicAttacks(ArrayList<Tile> allowed, int direction) {
    Tile forwardLeft = this.board.getTileAt(this.getX() - 1, this.getY() + direction);
    
    if (forwardLeft != null
        && forwardLeft.hasPieceAndAttackableBy(this.getOwner())) {
      allowed.add(forwardLeft);
    }

    Tile forwardRight = this.board.getTileAt(this.getX() + 1, this.getY() + direction);
    
    if (forwardRight != null
        && forwardRight.hasPieceAndAttackableBy(this.getOwner())) {
      allowed.add(forwardRight);
    }
  }
}
