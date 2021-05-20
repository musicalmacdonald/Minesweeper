package com.megan.minesweeper;

import java.util.Objects;

public class Mine {
  Integer x;
  Integer y;
//  TODO: Add a strength setting

  Mine(){};
  Mine(Integer x, Integer y) {
    this.x = x;
    this.y = y;
  }


  public Integer getX() {
    return x;
  }

  public void setX(Integer x) {
    this.x = x;
  }

  public Integer getY() {
    return y;
  }

  public void setY(Integer y) {
    this.y = y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Mine)) {
      return false;
    }
    Mine mine = (Mine) o;
    return x.equals(mine.x) && y.equals(mine.y);
  }

}
