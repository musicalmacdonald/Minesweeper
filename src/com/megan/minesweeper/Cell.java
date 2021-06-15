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
    if (this.isFlag()) {
      this.toggleFlag();
    }
  }

  public boolean isFlag() {
    return flag;
  }

  public void toggleFlag() {
    this.flag = !this.flag;
  }

  String getCellChar() {
    String cellStr = ".";
    if (this.isFlag()) {
      cellStr = "*";
    } else if (this.displayed) {
      cellStr = "/";
    }

    return cellStr;
  }

  boolean isCellSolved() {
    return !this.isFlag();
  }
}
