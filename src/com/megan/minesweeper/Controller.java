package com.megan.minesweeper;

import java.util.Scanner;

public class Controller {
  Scanner sc;
  Board game;

  Controller() {
    this.sc = new Scanner(System.in);
    this.game = new Board();
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

    System.out.println("Set/unset mines marks or claim a cell as free:");
    String input = sc.nextLine();
    if (input.matches("\\d+ \\d+ [a-z]+")) {
      userInput = input.split(" ");
    } else {
      System.out.println("Please provide 2 valid integers separated by a space and either the word 'mine' or 'free'.");
      determineUserMove();
    }
    return new UserMove(userInput);
  }

  public void beginGame() {
    determineNumberOfMines();
    game.printField();
    UserMove firstMove = determineUserMove();
    game.setMines(firstMove.getX(), firstMove.getY());
  }

  public void playGame() {

  }


}
