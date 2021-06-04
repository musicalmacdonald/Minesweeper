package com.megan.minesweeper;

import java.util.Random;
import java.util.Scanner;

public class Board {
  private int numberOfMines;
  private int boardDimension;
  private Cell[][] field;

  Board() {
    this.setBoardDimension(9);
    this.initField();
  }

  public int getNumberOfMines() {
    return numberOfMines;
  }

  public void setNumberOfMines(int numberOfMines) {
    this.numberOfMines = numberOfMines;
  }

  public int getBoardDimension() {
    return boardDimension;
  }

  public void setBoardDimension(int boardDimension) {
    this.boardDimension = boardDimension;
  }

  public Cell getFieldCell(int x, int y) {
    return field[x][y];
  }

  public void setFieldCell(int x, int y, Cell cell) {
    this.field[x][y] = cell;
  }

  public void initField() {
    field = new Cell[boardDimension][boardDimension];
    for (int i = 0; i < boardDimension; i++) {
      for (int j = 0; j < boardDimension; j++) {
        setFieldCell(i,j, new Cell());
      }
    }
  }

  public void setMines(int x, int y) {
    Random random = new Random();
    for (int i = 0; i < numberOfMines; i++) {
      Integer nextX = random.nextInt(boardDimension - 1);
      Integer nextY = random.nextInt(boardDimension - 1);
      if (!isMine(nextX, nextY) && !(nextX == x && nextY == y)) {
        setFieldCell(nextX,nextY, new Mine());
        addHints(nextX, nextY);
      } else {
        i--;
      }
    }
  }

  private void addHints(int x, int y) {
    for (int i = x - 1; i <= x + 1; i++) {
      if (i < 0 || i > boardDimension) continue;
      for (int j = y - 1; j <= y + 1; j++) {
        if (j < 0 || j > boardDimension || isMine(i, j)) {
          continue;
        } else {
          if (!(field[i][j] instanceof Dracula)) {
            setFieldCell(i, j, new Dracula());
          }
          ((Dracula) field[i][j]).addOne();
        }
      }
    }
  }

  public Boolean isMine(int x, int y) {
    return field[x][y] instanceof Mine;
  }

  public Boolean areAllMinesMarked() {
    boolean allMinesMarked = true;
    for (Cell[] row : field ) {
      for (Cell cell : row) {
        if (cell.isCellSolved()) {
          continue;
        } else {
          allMinesMarked = false;
        }
      }
    }
    return allMinesMarked;
  }

  public void setVisibleCells(int x, int y) {
    if (isMine(x, y)) {
      showAllMines();
      System.out.println("You stepped on a mine and failed!");
    } else {
      crawlNonMineCells(x, y);
    }
  }

  private void crawlNonMineCells(int x, int y) {
    for (int i = x - 1; i <= x + 1; i++) {
      if (i < 0 || i > boardDimension) continue;
      for (int j = y - 1; j <= y + 1; j++) {
        if (j < 0 || j > boardDimension || isMine(i, j) || field[i][j].isDisplayed()) {
          continue;
        } else {
          field[i][j].setDisplayed(true);
          if (!(field[i][j] instanceof Dracula)) {
            crawlNonMineCells(i, j);
          }
        }
      }
    }
  }

  private void showAllMines() {
    for (Cell[] row : field ) {
      for (Cell cell : row) {
        if (cell instanceof Mine) {
          cell.setDisplayed(true);
        }
      }
    }
  }

//  TODO: add wrapper to Controller?
  public void printField() {
    System.out.println(" |123456789|");
    System.out.println("-|—————————|");
    for (int i = 0; i < boardDimension; i++) {
      System.out.print((i + 1) + "|");
      for (int j = 0; j < boardDimension; j++) {
        System.out.print(field[i][j].getCellChar());
      }
      System.out.print("|");
      System.out.println();
    }
    System.out.println("-|—————————|");
  }
}
