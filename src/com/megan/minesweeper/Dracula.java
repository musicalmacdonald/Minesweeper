package com.megan.minesweeper;

public class Dracula extends Cell {
  //  One mine AH AH AH!
  int count;

  Dracula() {
    super();
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
    if (this.isFlag()) {
      return "*";
    } else if (this.displayed) {
      return "" + count;
    } else {
      return ".";
    }
  }
}
