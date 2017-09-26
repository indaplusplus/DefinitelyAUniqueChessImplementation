package com.paul.test;

import com.paul.game.player.Player;

import java.util.Random;
import java.util.Scanner;

public class HumanPlayer extends Player {

  /*
   * 0: input
   * 1: random
   */
  private int mode;
  
  public HumanPlayer(int mode) {
    this.mode = mode;
  }
  
  @Override
  public int[] turn() {
    switch (mode) {
      case 0:
        return input();
      case 1:
        return random();
      default:
        return input();
    }
  }
  
  @Override
  public int promotion() {
    return 0;
  }
  
  public int[] input() {
    System.out.println(this.isWhite());
    
    Scanner s = new Scanner(System.in);
    
    int[] i = new int[4];

    i[0] = s.nextInt();
    i[1] = s.nextInt();
    i[2] = s.nextInt();
    i[3] = s.nextInt();
    
    return i;
  }
  
  private Random r = new Random();
  
  public int[] random() {
    int[] i = new int[4];

    i[0] = Math.abs(r.nextInt() % 8) + 1;
    i[1] = Math.abs(r.nextInt() % 8) + 1;
    i[2] = Math.abs(r.nextInt() % 8) + 1;
    i[3] = Math.abs(r.nextInt() % 8) + 1;
    
    return i;
  }
}
