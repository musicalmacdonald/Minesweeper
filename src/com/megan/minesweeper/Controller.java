package com.megan.minesweeper;

import java.util.Scanner;

public class Controller {
  Scanner sc;
  Board game;

  Controller() {
    this.sc = new Scanner(System.in);
    this.game = new Board(true);
  }

//  TODO: add feature to set board size too
//  public void determineBoardDimension() {
//    System.out.println("How big should the field be?");
//    String input = sc.nextLine();
//    if (input.matches("\\d+")) {
//      this.game.setBoardDimension(Integer.parseInt(input));
//    } else {
//      System.out.println("You must provide a valid integer.");
//    }
//  }

  public void determineNumberOfMines() {
    int boardDim = this.game.getBoardDimension();
    System.out.println("How many mines do you want on the field?");
    String input = sc.nextLine();
    if (input.matches("\\d+") && Integer.parseInt(input) < boardDim * boardDim) {
      this.game.setNumberOfMines(Integer.parseInt(input));
    } else {
      System.out.println("You must provide a valid integer.");
    }
  }

  public UserMove determineUserMove() {
    String[] userInput = new String[3];

    this.game.printField();
    System.out.println("Set/unset mines marks or claim a cell as free:");
    String input = sc.nextLine();
    if (input.matches("\\d+ \\d+ [a-z]+")) {
      userInput = input.split(" ");
    } else {
      System.out.println("Please provide 2 valid integers separated by a space and either the word 'mine' or 'free'.");
      input = "";
      determineUserMove();
    }
    return new UserMove(userInput);
  }

  public void beginGame() {
    determineNumberOfMines();
    UserMove firstMove = determineUserMove();
    while (!firstMove.isFree()) {
      this.game.getFieldCell(firstMove.getY(), firstMove.getX()).toggleFlag();
      firstMove = determineUserMove();
    }
    this.game.setMines(firstMove.getY(), firstMove.getX());
    this.game.setVisibleCells(firstMove.getX(), firstMove.getY());
  }

  public boolean playARound() {
    boolean notExploded = true;
    UserMove nextMove = determineUserMove();
    Cell targetCell = this.game.getFieldCell(nextMove.getY(), nextMove.getX());
    if (nextMove.isFree()) {
      notExploded = this.game.setVisibleCells(nextMove.getY(), nextMove.getX());
    } else {
      targetCell.toggleFlag();
    }
    return notExploded;
  }

  public void playGame() {
    beginGame();
    boolean isGameWon = false;
    boolean notExploded = false;
    do {
      notExploded = playARound();
      isGameWon = this.game.areAllMinesMarked();
    } while (!isGameWon && notExploded);

    if (!notExploded) {
      System.out.println("You stepped on a mine and failed!");
    } else if (isGameWon) {
      System.out.println("Congratulations! You found all the mines!");
    } else {
      System.out.println("An error occurred.  Please play again later.");
    }

  }


}
