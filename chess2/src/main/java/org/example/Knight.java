package org.example;

import java.awt.*;
import java.util.ArrayList;

public class Knight implements Pieces {
    private final char color;
    char[] returnChar = new char[2];
    ArrayList<Point> moveSet = new ArrayList<>();
    Knight(char color){
        this.color = color;
    }
    public ArrayList<Point> move(Point location, Pieces[][] board){
        int row = (int) location.getX();
        int col = (int) location.getY();
        
        ArrayList<Point> moveSet = new ArrayList<>();
        if(Main.inBoard(row +1,col +2)){moveSet.add( new Point(row+1,col+2));}
        if(Main.inBoard(row -1,col +2)){moveSet.add( new Point(row-1,col+2));}
        if(Main.inBoard(row +1,col -2)){moveSet.add( new Point(row+1,col-2));}
        if(Main.inBoard(row -1,col -2)){moveSet.add( new Point(row-1,col-2));}
        if(Main.inBoard(row +2,col +1)){moveSet.add( new Point(row+2,col+1));}
        if(Main.inBoard(row +2,col -1)){moveSet.add( new Point(row+2,col-1));}
        if(Main.inBoard(row -2,col +1)){moveSet.add( new Point(row-2,col+1));}
        if(Main.inBoard(row -2,col -1)){moveSet.add( new Point(row-2,col-1));}


        return checkMoves(board,moveSet);
    }
    public char color() {
        return color;
    }
    private ArrayList<Point> checkMoves(Pieces[][] board, ArrayList<Point> moveSet){
        ArrayList<Point> legalMoves = new ArrayList<>();
        for (Point point : moveSet) {
            int rowNew = (int) point.getX();
            int colNew = (int) point.getY();

            if (board[rowNew][colNew].color() != color) {
                legalMoves.add(point);
            }
        }
        return legalMoves;
    }
    public void kingInCheck(int row, int col, Pieces[][] board){
        if(Main.inBoard(row,col) && board[row][col].color() != color && board[row][col].getClass() == King.class){
            returnChar[0] = color;
        }
    }
    public char[] onMove(Point location, Pieces[][] board){
        int row = (int) location.getX();
        int col = (int) location.getY();
        returnChar[0] = 'N';
        returnChar[1] = 'N';
        moveSet.clear();

        kingInCheck(row +1,col +2, board);
        kingInCheck(row -1,col +2, board);
        kingInCheck(row +1,col -2, board);
        kingInCheck(row -1,col -2, board);
        kingInCheck(row +2,col +1, board);
        kingInCheck(row +2,col -1, board);
        kingInCheck(row -2,col +1, board);
        kingInCheck(row -2,col -1, board);
        return returnChar;
    }
}