package com.megan.minesweeper;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class GameBoard {
  int numberOfMines;
  int boardDimension;
  Mine[] mines;
  char[][] field;


  public void determineBoardDimension() {
    Scanner sc = new Scanner(System.in);
    System.out.println("How big should the field be?");
    String input = sc.nextLine();
    if (input.matches("\\d+")) {
      this.boardDimension = Integer.parseInt(input);
    } else {
      System.out.println("You must provide a valid integer.");
    }
    sc.close();
  }

  public void determineNumberOfMines() {
    Scanner sc = new Scanner(System.in);
    System.out.println("How many mines do you want on the field?");
    String input = sc.nextLine();
    if (input.matches("\\d+")) {
      this.numberOfMines = Integer.parseInt(input);
    } else {
      System.out.println("You must provide a valid integer.");
    }
    sc.close();
  }

  public void initField() {
    // TODO: ask the user how big the field should be
    // this.determineBoardDimension();
    this.boardDimension = 9;
    this.determineNumberOfMines();
    this.mines = new Mine[numberOfMines];
    this.field = new char[boardDimension][boardDimension];

    Random random = new Random();
    for (int i = 0; i < numberOfMines; i++) {
      Integer nextX = random.nextInt(boardDimension);
      Integer nextY = random.nextInt(boardDimension);
      if (!isMine(nextX, nextY)) {
        mines[i] = new Mine(nextX, nextY);
      } else {
        i--;
      }
    }

    for (int i = 0; i < boardDimension; i++) {
      for (int j = 0; j < boardDimension; j++) {
        int count = determineSurroundingMines(i, j);
        char next;
        if (count == 0) {
          next = '.';
        } else if (count == 9) {
          next = 'X';
        } else {
          next = Character.forDigit(count,10);
        }
        this.field[i][j] = next;
      }
    }
  }


  // Attempt #1 - Gross.
  private int determineSurroundingMines(int x, int y) {
    if (isMine(x, y)){
      return 9;
    }
    int count = 0;
    if (x == 0) {
      if (y == 0) {
//                no x-1, y-1
        count += isMine(x, y+1) ? 1 : 0;
        count += isMine(x+1, y) ? 1 : 0;
        count += isMine(x+1, y+1) ? 1 : 0;
      } else if (y == this.boardDimension) {
//                no x-1, y+1
        count += isMine(x, y-1) ? 1 : 0;
        count += isMine(x+1, y-1) ? 1 : 0;
        count += isMine(x+1, y) ? 1 : 0;
      } else {
//                no x-1
        count += isMine(x, y-1) ? 1 : 0;
        count += isMine(x, y+1) ? 1 : 0;
        count += isMine(x+1, y-1) ? 1 : 0;
        count += isMine(x+1, y) ? 1 : 0;
        count += isMine(x+1, y+1) ? 1 : 0;
      }
    } else if (x == this.boardDimension) {
      if (y == 0) {
//                no x+1, y-1
        count += isMine(x, y+1) ? 1 : 0;
        count += isMine(x-1, y) ? 1 : 0;
        count += isMine(x-1, y+1) ? 1 : 0;
      } else if (y == this.boardDimension) {
//                no x+1, y+1
        count += isMine(x, y-1) ? 1 : 0;
        count += isMine(x-1, y-1) ? 1 : 0;
        count += isMine(x-1, y) ? 1 : 0;
      } else {
//                no x+1
        count += isMine(x, y-1) ? 1 : 0;
        count += isMine(x, y+1) ? 1 : 0;
        count += isMine(x-1, y-1) ? 1 : 0;
        count += isMine(x-1, y) ? 1 : 0;
        count += isMine(x-1, y+1) ? 1 : 0;
      }
    } else {
      if (y == 0) {
//                no y-1
        count += isMine(x, y+1) ? 1 : 0;
        count += isMine(x-1, y) ? 1 : 0;
        count += isMine(x-1, y+1) ? 1 : 0;
        count += isMine(x+1, y) ? 1 : 0;
        count += isMine(x+1, y+1) ? 1 : 0;

      } else if (y == this.boardDimension) {
//                no y+1
        count += isMine(x, y-1) ? 1 : 0;
        count += isMine(x-1, y-1) ? 1 : 0;
        count += isMine(x-1, y) ? 1 : 0;
        count += isMine(x+1, y-1) ? 1 : 0;
        count += isMine(x+1, y) ? 1 : 0;
      } else {
//                all surrounding squares
        count += isMine(x, y-1) ? 1 : 0;
        count += isMine(x, y+1) ? 1 : 0;
        count += isMine(x-1, y-1) ? 1 : 0;
        count += isMine(x-1, y) ? 1 : 0;
        count += isMine(x-1, y+1) ? 1 : 0;
        count += isMine(x+1, y-1) ? 1 : 0;
        count += isMine(x+1, y) ? 1 : 0;
        count += isMine(x+1, y+1) ? 1 : 0;
      }
    }
    return count;
  }

  private Boolean isMine(int x, int y) {
    return Arrays.asList(mines).contains(new Mine(x, y));
  }

  public void printField() {
    for (int i = 0; i < boardDimension; i++) {
      for (int j = 0; j < boardDimension; j++) {
        System.out.print(field[i][j]);
      }
      System.out.println();
    }
  }
}
