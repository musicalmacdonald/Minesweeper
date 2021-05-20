package com.megan.minesweeper;

import java.util.Random;
import java.util.Scanner;

public class GameBoard {
  int numberOfMines;
  int boardDimension;
  Cell[][] field;

  private void determineBoardDimension() {
    Scanner sc = new Scanner(System.in);
    System.out.println("How big should the field be?");
    String input = sc.nextLine();
    if (input.matches("\\d+")) {
      this.boardDimension = Integer.parseInt(input);
    } else {
      System.out.println("You must provide a valid integer.");
    }
//        sc.close();
  }

  private void determineNumberOfMines() {
    Scanner sc = new Scanner(System.in);
    System.out.println("How many mines do you want on the field?");
    String input = sc.nextLine();
    if (input.matches("\\d+") && Integer.parseInt(input) < boardDimension * boardDimension) {
      this.numberOfMines = Integer.parseInt(input);
    } else {
      System.out.println("You must provide a valid integer.");
    }
//        Closing this disallows input later in the program, why?
//        sc.close();
  }

  private int[] determineUserMove() {
    int[] userInput = new int[2];
    Scanner sc = new Scanner(System.in);
    System.out.println("Set/delete mines marks (x and y coordinates):");
    String input = sc.nextLine();
    if (input.matches("\\d+ \\d+")) {
      String s[]= input.split(" ");
      for (int i =0; i < s.length; i++) {
        userInput[i]= Integer.parseInt(s[i]) - 1;
      }
    } else {
      System.out.println("You must provide 2 valid integers separated by a space.");
      determineUserMove();
    }
    return userInput;
  }

  public void initField() {
    // TODO: ask the user how big the field should be
    // this.determineBoardDimension();
    boardDimension = 9;
    determineNumberOfMines();
    field = new Cell[boardDimension][boardDimension];
    for (int i = 0; i < boardDimension; i++) {
      for (int j = 0; j < boardDimension; j++) {
        field[i][j] = new Cell();
      }
    }

    Random random = new Random();
    for (int i = 0; i < numberOfMines; i++) {
      Integer nextX = random.nextInt(boardDimension - 1);
      Integer nextY = random.nextInt(boardDimension - 1);
      if (!isMine(nextX, nextY)) {
        field[nextX][nextY].setMine(true);
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
          field[i][j].addOne();
        }
      }
    }
  }

  private Boolean isMine(int x, int y) {
    return field[x][y].isMine();
  }

  private Boolean areAllMinesMarked() {
    Boolean allMinesMarked = true;
    for (Cell[] row : field ) {
      for (Cell cell : row) {
        if (cell.isMine() && !cell.isFlag()) {
          allMinesMarked = false;
        } else if (cell.isFlag() && !cell.isMine()) {
          allMinesMarked = false;
        } else {
          continue;
        }
      }
    }
    return allMinesMarked;
  }

  public void printField() {
    System.out.println(" |123456789|");
    System.out.println("-|—————————|");
    for (int i = 0; i < boardDimension; i++) {
      System.out.print((i + 1) + "|");
      for (int j = 0; j < boardDimension; j++) {
        if (field[i][j].isFlag()){
          System.out.print('*');
        } else if (  field[i][j].count > 0) {
          System.out.print(field[i][j].count);
        } else {
          System.out.print('.');
        }
      }
      System.out.print("|");
      System.out.println();
    }
    System.out.println("-|—————————|");
  }

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