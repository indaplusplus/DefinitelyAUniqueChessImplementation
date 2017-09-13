package com.paul.game.map;

import com.paul.game.Game;
import com.paul.game.piece.*;
import com.paul.game.piece.movement.EnPassant;
import com.paul.game.player.Player;

import java.util.ArrayList;

public class Board {
  
  private ArrayList<Tile> tileList = new ArrayList<>();
  private ArrayList<Piece> pieceList = new ArrayList<>();
  
  public Game game;
  public EnPassant enPassant;
  
  public Board(Game game) {
    this.game = game;
    
    this.enPassant = new EnPassant(this);
    
    generateBoard();
    generatePieces();
  }
  
  private void generateBoard() {
    for (int x = 1; x <= Game.width; x++) {
      for (int y = 1; y <= Game.height; y++) {
        tileList.add(new Tile(this, x, y));
      }
    }
  }
  
  /**
   * Generates pieces for the white and black player.
   */
  public void generatePieces() {
    for (int x = 1; x <= Game.width; x++) {
      this.pieceList.add(new Pawn(this, game.turn.getWhite(), x, Game.height - 1));
    }
    
    this.pieceList.add(new Rook(this, game.turn.getWhite(), 1, Game.height));
    this.pieceList.add(new Rook(this, game.turn.getWhite(), 8, Game.height));
    
    this.pieceList.add(new Knight(this, game.turn.getWhite(), 2, Game.height));
    this.pieceList.add(new Knight(this, game.turn.getWhite(), 7, Game.height));
    
    this.pieceList.add(new Bishop(this, game.turn.getWhite(), 3, Game.height));;
    this.pieceList.add(new Bishop(this, game.turn.getWhite(), 6, Game.height));
    
    this.pieceList.add(new Queen(this, game.turn.getWhite(), 4, Game.height));
    this.pieceList.add(new King(this, game.turn.getWhite(), 5, Game.height));
    
    for (int x = 1; x <= Game.width; x++) {
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
