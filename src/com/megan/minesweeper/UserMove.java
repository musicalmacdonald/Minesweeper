package com.megan.minesweeper;

public class UserMove {
  private int x;
  private int y;
  private Boolean free;

  UserMove(int x, int y, Boolean free) {
    this.x = x;
    this.y = y;
    this.free = free;
  }

  UserMove(String[] input) {
    this.x = Integer.parseInt(input[0]);
    this.y = Integer.parseInt(input[1]);
    this.free = input[2].matches("free");
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
