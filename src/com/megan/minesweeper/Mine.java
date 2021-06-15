package com.megan.minesweeper;

public class Mine extends Cell {

  Mine() {
    super();
  }
  Mine(boolean flag){
    super();
    this.flag = flag;
  }

  @Override
  String getCellChar() {
    String cellStr = ".";
    if (this.isFlag()) {
      cellStr = "*";
    }
    // Needs to come 2nd for loosing scenario
    if (this.displayed) {
      cellStr = "X";
    }
    return cellStr;
  }

  @Override
  boolean isCellSolved() {
    return this.isFlag() && !this.isDisplayed();
  }
}
