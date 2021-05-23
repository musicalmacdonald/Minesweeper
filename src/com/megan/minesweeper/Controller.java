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

  public int[] determineUserMove() {
    int[] userInput = new int[2];
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


}
