package org.example;

import java.awt.*;
import java.util.ArrayList;

public class Queen implements Pieces {
    private final char color;
    char[] returnChar = new char[2];
    ArrayList<Point> moveSet = new ArrayList<>();
    Queen(char color){
        this.color = color;
    }
    public ArrayList<Point> move(Point location, Pieces[][] board){
        int row = (int) location.getX();
        int col = (int) location.getY();
        moveSet.clear();

        moves(row -1 ,col -1,-1,-1,board); // up/left
        moves(row -1 ,col +1,-1,+1,board); // down/left
        moves(row +1, col +1,+1,+1, board); //down/right
        moves(row +1,col -1,+1,-1,board); //up right
        moves(row,col -1,0,-1 ,board); // up
        moves(row,col +1,0,+1,board); // down
        moves(row +1, col,+1,0, board); //right
        moves(row -1, col,-1,0,board); //left

        return moveSet;
    }
    public char color() {
        return color;
    }

    public void moves(int row, int col, int diffRow,int diffCol, Pieces[][] board){
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
        int row = location.x;
        int col = location.y;
        returnChar[0] = 'N';
        returnChar[1] = 'N';
        kingInCheck(row -1 ,col -1,-1,-1,board); // up/left
        kingInCheck(row -1 ,col +1,-1,+1,board); // down/left
        kingInCheck(row +1, col +1,+1,+1, board); //down/right
        kingInCheck(row +1,col -1,+1,-1,board); //up right
        kingInCheck(row,col -1,0,-1 ,board); // up
        kingInCheck(row,col +1,0,+1,board); // down
        kingInCheck(row +1, col,+1,0, board); //right
        kingInCheck(row -1, col,-1,0,board); //left

        return returnChar;}


}
