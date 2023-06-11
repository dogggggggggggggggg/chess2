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
        moveField(row -1,col -1,-1,-1,board); // up/left
        moveField(row -1,col +1,-1,+1,board); // down/left
        moveField(row +1,col +1,+1,+1, board); //down/right
        moveField(row +1,col -1,+1,-1,board); //up right
        System.out.println(moveSet);
        return moveSet;
    }
    public char color() {
        return color;
    }

    
    private void moveField(int row, int col, int diffRow,int diffCol, Pieces[][] board){
        if (row <= 7 && row >= 0 && col <= 7 && col >= 0){
            char pieceColor = board[row-diffRow][col-diffCol].color();

            for ( ;row <= 7 && row >= 0 && col <= 7 && col >= 0; row += diffRow, col += diffCol){
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
