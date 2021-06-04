package com.megan.minesweeper;

public class UserMove {
  private int x;
  private int y;
  private Boolean free;

  UserMove(int x, int y, Boolean free) {
    // subtract 1 to account for 0 based array index
    this.setX(x - 1);
    this.setY(y - 1);
    this.setFree(free);
  }

  UserMove(String[] input) {
    // subtract 1 to account for 0 based array index
    this.setX(Integer.parseInt(input[0]) - 1);
    this.setY(Integer.parseInt(input[1]) - 1);
    this.setFree(input[2].matches("free"));
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

  public Boolean isFree() {
    return free;
  }

  public void setFree(Boolean free) {
    this.free = free;
  }
}
