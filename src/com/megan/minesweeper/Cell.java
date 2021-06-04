package com.megan.minesweeper;

public class Cell {
  boolean displayed;
  boolean flag;

  Cell(){
    this.displayed = false;
    this.flag = false;
  }

  public Boolean isDisplayed() {
    return displayed;
  }

  public void setDisplayed(Boolean displayed) {
    this.displayed = displayed;
  }

  public Boolean isFlag() {
    return flag;
  }

  public void setFlag() {
    this.flag = !this.flag;
  }

  String getCellChar() {
    if (this.isFlag()) {
      return "*";
    } else if (this.displayed) {
      return "/";
    } else {
      return ".";
    }
  }

  boolean isCellSolved() {
    return !this.isFlag();
  }
}
