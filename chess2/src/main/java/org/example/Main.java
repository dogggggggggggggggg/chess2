package org.example;

public class Main {

    public static boolean inBoard(int row, int col){
        return row <= 7 && row >= 0 && col <= 7 && col >= 0;
    }
    public static void main(String[] args) {
        new Board();

    }
}