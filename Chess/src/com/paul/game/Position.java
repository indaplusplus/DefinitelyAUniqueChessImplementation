package com.paul.game;

public class Position {

  private static final char[] positions = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
  
  public static int getXfromLetter(char c) {
    c = Character.toLowerCase(c);
    
    for (int i = 0; i < positions.length; i++) {
      if (positions[i] == c) {
        return i + 1;
      }
    }
    
    return -1;
  }
  
  public static String getLetteringFromCoordinates(int x, int y) {
    return positions[x - 1] + String.valueOf(y);
  }
  
  /**
   * @param position Chess notation for position.
   * @return Element 0 is the x coordinate, element 1 is the y coordinate.
   */
  public static int[] getCoordinatesFromLettering(String position) {
    int x = getXfromLetter(position.charAt(0));
    int y = Integer.valueOf(position.charAt(1));
    return new int[] {x, y};
  }
  
  private int x;
  private int y;
  
  public Position(String position) {
    int[] coordinates = getCoordinatesFromLettering(position);
    
    this.x = coordinates[0];
    this.y = coordinates[1];
  }
  
  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void setFromlettering(String position) {
    this.x = getXfromLetter(position.charAt(0));
    this.y = Integer.valueOf(position.charAt(1));
  }
  
  public String getlettering() {
    return getLetteringFromCoordinates(this.x, this.y);
  }
  
  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }
}
