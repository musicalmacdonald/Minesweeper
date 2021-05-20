package com.megan.minesweeper;

public class Main {

    public static void main(String[] args) {
        // write your code here
        initField(9,10);
    }
    public static void initField(int boardDimension, int mines) {
        int mineCount = 0;
        for (int i = 0; i < boardDimension; i++) {
            for (int j = 0; j < boardDimension; j++) {
                char next = '.';
                double rand = Math.random();
                if (rand > .9 && mineCount <= mines) {
                    next = 'X';
                    mineCount++;
                }
                System.out.print(next);
            }
            System.out.println();
        }
    }
}
