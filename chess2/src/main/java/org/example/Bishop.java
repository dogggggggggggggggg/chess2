package org.example;

import java.awt.*;
import java.util.ArrayList;

public class Bishop implements Pieces {
    private final char color;
    char[] returnChar = new char[2];
    ArrayList<Point> moveSet = new ArrayList<>();
    Bishop(char color){
        this.color = color;
    }
    
    public ArrayList<Point> move(Point location, Pieces[][] board){
        int row = (int) location.getX();
        int col = (int) location.getY();
        moveSet.clear();
        
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

            for ( ;Main.inBoard(row,col); row += diffRow, col += diffCol){
                if(board[row][col].color() == 'N'){
                    moveSet.add(new Point(row,col));
                } else if (board[row][col].color() == color) {
                    break;
                } else if (board[row][col].color() != color) {
                    moveSet.add(new Point(row,col));
                    break;
                }
            }
        }
    }
    public void kingInCheck(int row, int col, int diffRow,int diffCol, Pieces[][] board){
        if (Main.inBoard(row,col)){
            for ( ;Main.inBoard(row,col); row += diffRow, col += diffCol){
                if (board[row][col].color() == color) {
                    break;
                } else if (board[row][col].color() != color && board[row][col].getClass() == King.class ) {
                    returnChar[0] = color;
                } else if (board[row][col].color() != color && board[row][col].color() != 'N'){
                    break;
                }
            }
        }
    }
    public char[] onMove(Point location, Pieces[][] board){
        int row = (int) location.getX();
        int col = (int) location.getY();
        returnChar[0] = 'N';
        returnChar[1] = 'N';
        kingInCheck(row -1,col -1,-1,-1,board); // up/left
        kingInCheck(row -1,col +1,-1,+1,board); // down/left
        kingInCheck(row +1,col +1,+1,+1, board); //down/right
        kingInCheck(row +1,col -1,+1,-1,board); //up right
        return returnChar;
    }
    public Pieces copy(){
        return new Bishop(color);
    }
}
