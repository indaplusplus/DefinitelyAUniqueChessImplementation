package com.paul.game.map;

import com.paul.game.Game;
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
  
  private ArrayList<Tile> tileList = new ArrayList<>();
  private ArrayList<Piece> pieceList = new ArrayList<>();
  
  public Game game;
  
  public Board(Game game) {
    this.game = game;
    
    generateBoard();
    generatePieces();
  }
  
  private void generateBoard() {
    for (int x = 1; x <= 8; x++) {
      for (int y = 1; y <= 8; y++) {
        tileList.add(new Tile(this, x, y));
      }
    }
  }
  
  /**
   * Generates pieces for the white and black player.
   */
  public void generatePieces() {
    for (int x = 1; x <= 8; x++) {
      this.pieceList.add(new Pawn(this, game.turn.getWhite(), x, 7));
    }
    
    this.pieceList.add(new Rook(this, game.turn.getWhite(), 1, 8));
    this.pieceList.add(new Rook(this, game.turn.getWhite(), 8, 8));
    
    this.pieceList.add(new Knight(this, game.turn.getWhite(), 2, 8));
    this.pieceList.add(new Knight(this, game.turn.getWhite(), 7, 8));
    
    this.pieceList.add(new Bishop(this, game.turn.getWhite(), 3, 8));;
    this.pieceList.add(new Bishop(this, game.turn.getWhite(), 6, 8));
    
    this.pieceList.add(new Queen(this, game.turn.getWhite(), 4, 8));
    this.pieceList.add(new King(this, game.turn.getWhite(), 5, 8));
    
    for (int x = 1; x <= 8; x++) {
      this.pieceList.add(new Pawn(this, game.turn.getBlack(), x, 2));
    }
    
    this.pieceList.add(new Rook(this, game.turn.getBlack(), 1, 1));
    this.pieceList.add(new Rook(this, game.turn.getBlack(), 8, 1));
    
    this.pieceList.add(new Knight(this, game.turn.getBlack(), 2, 1));
    this.pieceList.add(new Knight(this, game.turn.getBlack(), 7, 1));
    
    this.pieceList.add(new Bishop(this, game.turn.getBlack(), 3, 1));
    this.pieceList.add(new Bishop(this, game.turn.getBlack(), 6, 1));
    
    this.pieceList.add(new Queen(this, game.turn.getBlack(), 4, 1));
    this.pieceList.add(new King(this, game.turn.getBlack(), 5, 1));
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
  
  public King getKing(Player player) {
    for (Piece p : this.getPieceList()) {
      if (p instanceof King) {
        return (King) p;
      }
    }
    
    return null;
  }
  
  public void killPiece(Piece attack, Piece victim) {
    this.getPieceList().remove(victim);
    
    this.game.callEventPieceKilled(attack, victim);
  }
}
