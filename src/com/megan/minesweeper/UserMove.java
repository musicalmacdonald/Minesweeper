package com.megan.minesweeper;

public class UserMove {
  private int column;
  private int row;
  private Boolean free;

  UserMove(int x, int y, Boolean free) {
    // subtract 1 to account for 0 based array index
    this.setColumn(x - 1);
    this.setRow(y - 1);
    this.setFree(free);
  }

  UserMove(String[] input) {
    // subtract 1 to account for 0 based array index
    this.setColumn(Integer.parseInt(input[0]) - 1);
    this.setRow(Integer.parseInt(input[1]) - 1);
    this.setFree(input[2].matches("free"));
  }

  public int getColumn() {
    return column;
  }

  public void setColumn(int column) {
    this.column = column;
  }

  public int getRow() {
    return row;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public Boolean isFree() {
    return free;
  }

  public void setFree(Boolean free) {
    this.free = free;
  }
}
