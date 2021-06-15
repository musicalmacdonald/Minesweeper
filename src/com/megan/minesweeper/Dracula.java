package com.megan.minesweeper;

public class Dracula extends Cell {
  //  One mine AH AH AH!
  int count;

  Dracula() {
    super();
    this.count = 0;
  }
  Dracula(boolean flag) {
    super();
    this.flag = flag;
    this.count = 0;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }


  public void addOne() {
    this.count += 1;
  }

  @Override
  String getCellChar() {
    String cellStr = ".";
    if (this.displayed) {
      cellStr = "" + count;
    }
    if (this.isFlag()) {
      cellStr = "*";
    }
    return cellStr;
  }
}
