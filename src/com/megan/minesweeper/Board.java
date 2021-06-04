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

  private Boolean isMine(int x, int y) {
    return field[x][y] instanceof Mine;
  }

  private Boolean isMine(Cell cell) {
    return cell instanceof Mine;
  }

  private Boolean areAllMinesMarked() {
    Boolean allMinesMarked = true;
    for (Cell[] row : field ) {
      for (Cell cell : row) {
        if (isMine(cell) && !cell.isFlag()) {
          allMinesMarked = false;
        } else if (cell.isFlag() && !(isMine(cell))) {
          allMinesMarked = false;
        } else {
          continue;
        }
      }
    }
    return allMinesMarked;
  }

//  TODO: add wrapper to Controller
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

//  TODO: move into Controller?
  public void playGame() {
    Boolean isGameWon = false;
    do {
      int[] userMove = determineUserMove();
      int x = userMove[0];
      int y = userMove[1];
      if (field[y][x].isNumber()) {
        System.out.println("There is a number here!");
      } else if (field[y][x].isFlag()) {
        field[y][x].setFlag(false);
        printField();
      } else {
        field[y][x].setFlag(true);
        printField();
      }

      isGameWon = areAllMinesMarked();

    } while (!isGameWon);
    System.out.println("Congratulations! You found all the mines!");
  }
}
