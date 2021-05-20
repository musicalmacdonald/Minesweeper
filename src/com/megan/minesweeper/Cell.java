package com.megan.minesweeper;

public class Cell {
  Boolean mine;
  Boolean flag;
  int count;

  Cell(){
    this.mine = false;
    this.flag = false;
    this.count = 0;
  }

  public Boolean isMine() {
    return mine;
  }

  public void setMine(Boolean mine) {
    this.mine = mine;
    if (mine) {
      this.count = 0;
    }
  }

  public Boolean isFlag() {
    return flag;
  }

  public void setFlag(Boolean flag) {
    this.flag = flag;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    if (!this.isMine()) {
      this.count = count;
    }
  }

  public void addOne() {
    if (!this.isMine()) {
      this.count += 1;
    }
  }

  public Boolean isNumber() {
    return this.count > 0;
  }
}
