package com.paul;

import java.util.Scanner;

import com.paul.game.player.Player;

public class HumanPlayer extends Player {

  @Override
  public int[] turn() {
    System.out.println(this.isWhite());
    
    Scanner s = new Scanner(System.in);
    
    int[] i = new int[4];

    i[0] = s.nextInt();
    i[1] = s.nextInt();
    i[2] = s.nextInt();
    i[3] = s.nextInt();
    
    return i;
  }
}
