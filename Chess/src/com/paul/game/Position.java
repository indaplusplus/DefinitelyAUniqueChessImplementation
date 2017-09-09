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
}
