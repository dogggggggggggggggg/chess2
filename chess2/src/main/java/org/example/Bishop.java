package org.example;

import java.awt.*;
import java.util.ArrayList;

public class Bishop implements Pieces {
    private final char color;
    ArrayList<Point> moveSet = new ArrayList<>();
    Bishop(char color){
        this.color = color;
    }
    
    public ArrayList<Point> move(Point location, Pieces[][] board){
        int row = (int) location.getX();
        int col = (int) location.getY();
        moveDiagonal(row -1,col -1,-1,-1,board); // up/left
        moveDiagonal(row -1,col +1,-1,+1,board); // down/left
        moveDiagonal(row +1,col +1,+1,+1, board); //down/right
        moveDiagonal(row +1,col -1,+1,-1,board); //up right

        return moveSet;
    }
    public char color() {
        return color;
    }

    
    private void moveDiagonal(int row, int col, int diffRow,int diffCol, Pieces[][] board){
        if (Main.inBoard(row,col)){
            char pieceColor = board[row-diffRow][col-diffCol].color();

            for ( ;Main.inBoard(row,col); row += diffRow, col += diffCol){
                if(board[row][col].color() == 'N'){
                    moveSet.add(new Point(row,col));
                } else if (board[row][col].color() == pieceColor) {
                    break;
                } else if (board[row][col].color() != pieceColor) {
                    moveSet.add(new Point(row,col));
                    break;
                }
            }
        }
    }

}
