package com.megan.minesweeper;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Controller minesweeper = new Controller();

        minesweeper.determineNumberOfMines();
        minesweeper.game.setMines();

        minesweeper.game.printField();
        minesweeper.game.playGame();


    }
}
