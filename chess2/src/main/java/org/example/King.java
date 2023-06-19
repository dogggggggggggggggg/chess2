package org.example;

import java.awt.*;
import java.util.ArrayList;

public class King implements Pieces {
    private final char color;
    ArrayList<Point> moveSet = new ArrayList<>();
    ArrayList<Point> moveSet2 = new ArrayList<>();
    int tempInt;
    char[] returnChar = new char[2];
    King(char color){
        this.color = color;
    }


    public ArrayList<Point> move(Point location, Pieces[][] board){
        int row = (int) location.getX();
        int col = (int) location.getY();
        moveSet.clear();
        moveSet2.clear();

        moves(row+1,col,board);
        moves(row+1,col+1,board);
        moves(row,col+1,board);
        moves(row-1,col+1,board);
        moves(row-1,col,board);
        moves(row-1,col-1,board);
        moves(row,col-1,board);
        moves(row+1,col-1,board);


        return checkMoves(board);
        //return moveSet;
    }
    private void moves(int row,int col, Pieces[][] board){

        if (Main.inBoard(row,col) && board[row][col].color() != color){
            moveSet.add(new Point(row,col));
        }
    }
    public ArrayList<Point> checkForKing(Point p, Pieces[][] board){
        moveSet.clear();
        return moveSet;
    }
   private ArrayList<Point>  checkMoves(Pieces[][] board){

        for(int col = 0; col < 8; col++, tempInt++) {
            for (int row = 0; row < 8; row++, tempInt++) {
                if (board[row][col].color() != color){
                    Point point = new Point(row,col);
                    if (board[row][col].getClass() != King.class && board[row][col].move(point,board) != null){
                        if (board[row][col].getClass() == Knight.class){
                            moveSet2.addAll(moveSet2.size(), board[row][col].move(point,board));
                        } else {

                            moveSet2.addAll( board[row][col].checkForKing(point, board));
                        }

                    }

                }

            }
        }

        for (Point a : moveSet2){
            moveSet.remove(a);
        }
        return moveSet;
    }
    public char color() {
        return color;
    }
    public char[] onMove(Point location, Pieces[][] board){
        returnChar[1] = 'N';
        returnChar[0] = 'N';
        return returnChar;}
}
