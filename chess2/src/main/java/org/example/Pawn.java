package org.example;

import java.awt.*;
import java.util.ArrayList;

public class Pawn implements Pieces  {
    private final char color;
    ArrayList<Point> moveSet = new ArrayList<>();

    Pawn(char color){
    this.color = color;

    }
    public ArrayList<Point> move(Point location, Pieces[][] board){
        int row = (int) location.getX();
        int col = (int) location.getY();
        System.out.println("a");

        switch (board[row][col].color()){
            case 'W':
                moveForward(row,col,1,board);
                eat(row,col,1,1,board);
                eat(row,col,-1,1,board);
            case 'B':
                moveForward(row,col,-1,board);
                eat(row,col,-1,-1,board);
                eat(row,col,1,-1,board);
        }


        return moveSet;
    }
    public char color() {
        return color;
    }

    private void moveForward(int row, int col, int diff, Pieces[][] board){
        if (Main.inBoard(row, col + diff) && board[row][col + diff].color() == 'N'){
            moveSet.add(new Point(row,col+diff));
            if (col == 1 && board[row][3].color() == 'N' && diff == 1){
                moveSet.add(new Point(row,col+2));
            } else if (col == 6 && board[row][4].color() == 'N' && diff == -1) {
                moveSet.add(new Point(row,col-2));
            }
        }

    }

    private void eat(int row, int col, int diffRow,int diffCol, Pieces[][] board){
        if (Main.inBoard(row + diffRow, col + diffCol) && board[row][col].color() != board[row + diffRow][col + diffCol].color() && board[row +diffRow][col +diffCol].color() != 'N'){
            moveSet.add(new Point(row +diffRow,col + diffCol));
        }

    }

}
