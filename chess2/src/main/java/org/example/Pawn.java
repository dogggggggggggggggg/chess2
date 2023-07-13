package org.example;

import java.awt.*;
import java.util.ArrayList;

public class Pawn implements Pieces  {
    private final char color;
    ArrayList<Point> moveSet = new ArrayList<>();
    char[] returnChar = new char[2];

    Pawn(char color){
    this.color = color;
    }
    public ArrayList<Point> move(Point location, Pieces[][] board){
        int row = (int) location.getX();
        int col = (int) location.getY();
        moveSet.clear();

        switch (color) {
            case 'W' -> {
                moveForward(row, col, 1, board);
                eat(row, col, 1, 1, board);
                eat(row, col, -1, 1, board);
            }
            case 'B' -> {
                moveForward(row, col, -1, board);
                eat(row, col, -1, -1, board);
                eat(row, col, 1, -1, board);
            }
        }
        return moveSet;
    }
    public char color() {
        return color;
    }

    private void moveForward(int row, int col, int diff, Pieces[][] board){
        if (Main.inBoard(row, col + diff) && board[row][col + diff].color() == 'N'){
            moveSet.add(new Point(row,col+diff));
            if (col == 1 && board[row][3].color() == 'N' && diff == 1 && color == 'W'){
                moveSet.add(new Point(row,col+2));
            } else if (col == 6 && board[row][4].color() == 'N' && diff == -1 && color == 'B') {
                moveSet.add(new Point(row,col-2));
            }
        }

    }
    private void eat(int row, int col, int diffRow,int diffCol, Pieces[][] board){
        if (Main.inBoard(row + diffRow, col + diffCol) && color != board[row + diffRow][col + diffCol].color() && board[row +diffRow][col +diffCol].color() != 'N'){
            moveSet.add(new Point(row +diffRow,col + diffCol));
        }

    }
    public void kingInCheck(int row, int col, int diffRow,int diffCol, Pieces[][] board){
        if (Main.inBoard(row + diffRow, col + diffCol)){
            if (board[row + diffRow][col + diffCol].getClass() == King.class && board[row + diffRow][col + diffCol].color() != color ){
                switch (color){
                    case 'B' ->  returnChar[0] = 'W';
                    case 'W' ->  returnChar[0] = 'B';
                }
            }
        }
    }
    public char[] onMove(Point location, Pieces[][] board){
        int row = (int) location.getX();
        int col = (int) location.getY();
        returnChar[0] = 'N';
        returnChar[1] = 'N';
        switch (color) {
            case 'W' -> {
                kingInCheck(row, col, 1, 1, board);
                kingInCheck(row, col, -1, 1, board);
            }
            case 'B' -> {
                kingInCheck(row, col, -1, -1, board);
                kingInCheck(row, col, 1, -1, board);
            }
        }
        //Pawn on Last row to promote
        if (col == 7 ) {
            returnChar[1] = 'W';
        } else if (col == 0) {
            returnChar[1] = 'B';
        }
        return returnChar;
    }
    public Pieces copy(){
        return new Pawn(color);
    }

}
