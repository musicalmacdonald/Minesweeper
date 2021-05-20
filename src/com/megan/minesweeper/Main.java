package com.megan.minesweeper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Scanner sc = new Scanner(System.in);

        System.out.println("How many mines do you want on the field?");

        int mines = sc.nextInt();
        sc.close();

        initField(9, mines);

    }

    public static void initField(int boardDimension, int mines) {
        Integer[] minePositions = new Integer[mines];
        Random random = new Random();
        for (int i = 0; i < mines; i++) {
            Integer next = random.nextInt(81);
            if (!Arrays.asList(minePositions).contains(next)) {
                minePositions[i] = next;
            } else {
                i--;
            }
        }

        int count = 0;
        for (int i = 0; i < boardDimension; i++) {
            for (int j = 0; j < boardDimension; j++) {
                char next = Arrays.asList(minePositions).contains(count) ? 'X' : '.';
                System.out.print(next);
                count++;
            }
            System.out.println();
        }
    }
}
