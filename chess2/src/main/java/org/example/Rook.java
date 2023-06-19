package org.example;

import java.awt.*;
import java.util.ArrayList;

public class Rook implements Pieces {
    private final char color;
    char[] returnChar = new char[2];
    ArrayList<Point> moveSet = new ArrayList<>();
    Rook(char color) {
        this.color = color;
    }
    public ArrayList<Point> move(Point location, Pieces[][] board){
        int row = (int) location.getX();
        int col = (int) location.getY();
        moveSet.clear();

        moveRow(row,col -1,0,-1,board,0); // up
        moveRow(row,col +1,0,+1,board,0); // down
        moveRow(row +1,col,+1,0,board,1); //right
        moveRow(row -1,col,-1,0,board,1); //left

        return moveSet;
    }
    public char color() {
        return color;
    }


    private void moveRow(int row, int col, int diffRow,int diffCol, Pieces[][] board, int i){
        switch (i) {
            case 1 -> {
                for (; row <= 7 && row >= 0; row += diffRow) {

                    if (board[row][col].color() == 'N') {
                        moveSet.add(new Point(row, col));

                    } else if (board[row][col].color() == color) {
                        break;
                    } else if (board[row][col].color() != color) {
                        moveSet.add(new Point(row, col));
                        break;
                    }
                }
            }
            case 0 ->{
                for (; col <=7 && col >= 0; col += diffCol){

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
    }
    public void kingInCheck(int row, int col, int diffRow,int diffCol, Pieces[][] board,int i){
        switch (i){
            case 1->{
                for ( ;Main.inBoard(row,col); row += diffRow){

                    if(board[row][col].color() == 'N'){
                        moveSet.add(new Point(row,col));
                    } else if (board[row][col].color() == color) {
                        break;
                    } else if (board[row][col].color() != color && board[row][col].getClass() == King.class ) {
                        returnChar[0] = color;
                        moveSet.add(new Point(row,col));
                    } else if (board[row][col].color() != color){
                        moveSet.add(new Point(row,col));
                        break;
                    }
                }
            }
            case 0->{
                for (;Main.inBoard(row,col); col += diffCol){

                    if(board[row][col].color() == 'N'){
                        moveSet.add(new Point(row,col));
                    } else if (board[row][col].color() == color) {
                        break;
                    } else if (board[row][col].color() != color && board[row][col].getClass() == King.class ) {
                        returnChar[0] = color;
                        moveSet.add(new Point(row,col));
                    } else if (board[row][col].color() != color){
                        moveSet.add(new Point(row,col));
                        break;
                    }
                }
            }
        }

    }
    public ArrayList<Point> checkForKing(Point location, Pieces[][] board){
        runKingInCheck(location, board);
        return moveSet;
    }
    public char[] onMove(Point location, Pieces[][] board){
        runKingInCheck(location, board);
        return returnChar;
    }
    private void runKingInCheck(Point location, Pieces[][] board){
        int row = (int) location.getX();
        int col = (int) location.getY();
        returnChar[1] = 'N';
        returnChar[0] = 'N';
        moveSet.clear();
        kingInCheck(row,col -1,0,-1,board,0); // up
        kingInCheck(row,col +1,0,+1,board,0); // down
        kingInCheck(row +1,col,+1,0,board,1); //right
        kingInCheck(row -1,col,-1,0,board,1); //left

    }
}
