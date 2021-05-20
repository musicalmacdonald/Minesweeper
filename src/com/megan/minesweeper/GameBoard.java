package com.megan.minesweeper;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class GameBoard {
  int numberOfMines;
  int boardDimension;
  Mine[] mines;
  int[][] field;


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
    boardDimension = 9;
    determineNumberOfMines();
    mines = new Mine[numberOfMines];
    field = new int[boardDimension][boardDimension];


    Random random = new Random();
    for (int i = 0; i < numberOfMines; i++) {
      Integer nextX = random.nextInt(boardDimension - 1);
      Integer nextY = random.nextInt(boardDimension - 1);
      if (!isMine(nextX, nextY)) {
        mines[i] = new Mine(nextX, nextY);
      } else {
        i--;
      }
    }

    addMinesAndHints();
  }

  private void addMinesAndHints() {
    for (Mine mine : mines) {
      field[mine.getX()][mine.getY()] = boardDimension;
      addHints(mine.getX(), mine.getY());
    }
  }

  private void addHints(int x, int y) {
    for (int i = x - 1; i <= x + 1; i++) {
      if (i < 0 || i > boardDimension) continue;
      for (int j = y - 1; j <= y + 1; j++) {
        if (j < 0 || j > boardDimension || isMine(i, j)) continue;
        field[i][j] += 1;
      }
    }
  }


  private Boolean isMine(int x, int y) {
    return Arrays.asList(mines).contains(new Mine(x, y));
  }

  public void printField() {
    for (int i = 0; i < boardDimension; i++) {
      for (int j = 0; j < boardDimension; j++) {
        if (field[i][j] == 0){
          System.out.print('.');
        } else if (field[i][j] == boardDimension) {
          System.out.print('X');
        } else {
          System.out.print(field[i][j]);
        }
      }
      System.out.println();
    }
  }
}