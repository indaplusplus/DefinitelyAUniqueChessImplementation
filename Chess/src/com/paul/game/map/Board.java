package com.paul.game.map;

import com.paul.game.Game;
import com.paul.game.Position;
import com.paul.game.piece.Bishop;
import com.paul.game.piece.King;
import com.paul.game.piece.Knight;
import com.paul.game.piece.Pawn;
import com.paul.game.piece.Piece;
import com.paul.game.piece.Queen;
import com.paul.game.piece.Rook;
import com.paul.game.player.Player;

import java.util.ArrayList;

public class Board {
  
  private ArrayList<Tile> tileList = new ArrayList<Tile>();
  private ArrayList<Piece> pieceList = new ArrayList<Piece>();
  
  private Game game;
  
  public Board(Game game) {
    this.game = game;
    
    generateBoard();
    generatePieces();
  }
  
  private void generateBoard() {
    for (int i = 8; i >= 1; i--) {
      for (int j = 1; j <= 8; j++) {
        tileList.add(new Tile(this, i, j));
      }
    }
  }
  
  /**
   * Generates pieces for the white and black player.
   */
  public void generatePieces() {
    for (int i = 1; i <= 8; i++) {
      this.pieceList.add(new Pawn(this, game.turn.getWhite(), 7, i));
    }
    
    this.pieceList.add(new Rook(this, game.turn.getWhite(), 8, 1));
    this.pieceList.add(new Rook(this, game.turn.getWhite(), 8, 8));
    
    this.pieceList.add(new Knight(this, game.turn.getWhite(), 8, 2));
    this.pieceList.add(new Knight(this, game.turn.getWhite(), 8, 7));
    
    this.pieceList.add(new Bishop(this, game.turn.getWhite(), 8, 3));
    this.pieceList.add(new Bishop(this, game.turn.getWhite(), 8, 6));
    
    this.pieceList.add(new Queen(this, game.turn.getWhite(), 8, 5));
    this.pieceList.add(new King(this, game.turn.getWhite(), 8, 4));
    
    for (int i = 1; i <= 8; i++) {
      this.pieceList.add(new Pawn(this, game.turn.getBlack(), 2, i));
    }
    
    this.pieceList.add(new Rook(this, game.turn.getBlack(), 1, 1));
    this.pieceList.add(new Rook(this, game.turn.getBlack(), 1, 8));
    
    this.pieceList.add(new Knight(this, game.turn.getBlack(), 1, 2));
    this.pieceList.add(new Knight(this, game.turn.getBlack(), 1, 7));
    
    this.pieceList.add(new Bishop(this, game.turn.getBlack(), 1, 3));
    this.pieceList.add(new Bishop(this, game.turn.getBlack(), 1, 6));
    
    this.pieceList.add(new Queen(this, game.turn.getBlack(), 1, 5));
    this.pieceList.add(new King(this, game.turn.getBlack(), 1, 4));
  }
  
  /**
   * @param x X position
   * @param y Y position
   * @return The tile at (x, y) or null if invalid.
   */
  public Tile getTileAt(int x, int y) {
    for (Tile t : this.getTileList()) {
      if (t.getX() == x && t.getY() == y) {
        return t;
      }
    }
    
    return null;
  }
  
  public ArrayList<Tile> getTileList() {
    return this.tileList;
  }
  
  public ArrayList<Piece> getPieceList() {
    return this.pieceList;
  }
  
  public void killPiece(Piece attack, Piece victim) {
    this.getPieceList().remove(victim);
    
    this.game.callEventPieceKilled(attack, victim);
  }
}
