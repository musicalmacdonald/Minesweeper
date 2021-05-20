package com.megan.minesweeper;

public class Main {

    public static void main(String[] args) {
        // write your code here
        GameBoard game = new GameBoard();

        game.initField();
        game.printField();
        game.playGame();
    }
}
