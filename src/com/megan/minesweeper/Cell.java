package com.megan.minesweeper;

public class Cell {
  Boolean displayed;
  Boolean flag;
//  TODO: remove this
  int count;

  Cell(){
    this.displayed = true;
    this.flag = false;
    this.count = 0;
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

  public void setFlag(Boolean flag) {
    this.flag = flag;
  }

//  TODO: move this to Board & check class
   public Boolean isNumber() {
    return this.count > 0;
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
}
