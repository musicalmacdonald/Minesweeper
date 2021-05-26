package com.megan.minesweeper;

public class Mine extends Cell {

  Mine() {
    super();
  }

  @Override
  String getCellChar() {
    if (this.isFlag()) {
      return "*";
    } else if (this.displayed) {
      return "X";
    } else {
      return ".";
    }
  }

}
