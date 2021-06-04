package com.megan.minesweeper;

public class Cell {
  boolean displayed;
  boolean flag;

  Cell(){
    this.displayed = false;
    this.flag = false;
  }

  public boolean isDisplayed() {
    return displayed;
  }

  public void setDisplayed(Boolean displayed) {
    this.displayed = displayed;
  }

  public boolean isFlag() {
    return flag;
  }

  public void setFlag(boolean flag) {
    this.flag = flag;
  }

  String getCellChar() {
    String cellStr = ".";
    if (this.displayed) {
      cellStr = "/";
    }
    if (this.isFlag()) {
      cellStr = "*";
    }
    return cellStr;
  }

  boolean isCellSolved() {
    return !this.isFlag();
  }
}
