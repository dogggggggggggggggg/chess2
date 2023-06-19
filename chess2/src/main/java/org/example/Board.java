package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board {

    JPanel boardPanel = new JPanel();
    JFrame windowFrame = new JFrame();
    Pieces[][] boardArray = new Pieces[8][8];
    Pieces current = new None('N');
    Point selected = new Point();
    ArrayList<Point> moveSet = new ArrayList<>();
    Color brown = new Color(193, 154, 107);
    Color checkRed = new Color(255, 68, 51);
    Color movementGreen = new Color(37, 191, 37);
    int turn;
    int tempInt;
    char[] returnOnMove = new char[2];
    char[] check = new char[2];
    boolean[] kingExist = new boolean[2];
    boolean outOfCheck;

     ImageIcon rookBlack = new ImageIcon(ClassLoader.getSystemResource("Rook_B.png"));
     ImageIcon rookWhite = new ImageIcon(ClassLoader.getSystemResource("Rook_W.png"));
     ImageIcon bishopBlack = new ImageIcon(ClassLoader.getSystemResource("Bishop_B.png"));
     ImageIcon bishopWhite = new ImageIcon(ClassLoader.getSystemResource("Bishop_W.png"));
     ImageIcon kingBlack = new ImageIcon(ClassLoader.getSystemResource("King_B.png"));
     ImageIcon kingWhite = new ImageIcon(ClassLoader.getSystemResource("King_W.png"));
     ImageIcon knightBlack = new ImageIcon(ClassLoader.getSystemResource("Knight_B.png"));
     ImageIcon knightWhite = new ImageIcon(ClassLoader.getSystemResource("Knight_W.png"));
     ImageIcon pawnBlack = new ImageIcon(ClassLoader.getSystemResource("Pawn_B.png"));
     ImageIcon pawnWhite = new ImageIcon(ClassLoader.getSystemResource("Pawn_W.png"));
     ImageIcon queenBlack = new ImageIcon(ClassLoader.getSystemResource("Queen_B.png"));
     ImageIcon queenWhite = new ImageIcon(ClassLoader.getSystemResource("Queen_W.png"));

    public Board() {
        boardPanel.setLayout(new GridLayout(8,8));

        //Brett aufstellen original
        for(int col = 0; col < 8; col++, tempInt++){
            for(int row = 0; row <8; row++, tempInt++) {
                //Feld erstellen und bemalen
                JButton field = new JButton();
                field.setBorderPainted(false);
                field.setName(row + "" + col);
                if (tempInt % 2 == 0){
                    field.setBackground(brown);
                } else {
                    field.setBackground(Color.WHITE);
                }
                boardPanel.add(field);

                //Brett aufsetzen
                switch (col){
                   //Weisse Seite
                   case 0:
                       switch (row) {
                           //Türme
                           case 0, 7 -> {
                               boardArray[row][col] = new Rook('W');
                               field.setIcon(rookWhite);
                               field.addActionListener(e -> {
                                   move(field);
                                   current = new Rook('W');
                                   refresh();
                               });

                           }
                           //Springer
                           case 1, 6 -> {
                               boardArray[row][col] = new Knight('W');
                               field.setIcon(knightWhite);
                               field.addActionListener(e -> {
                                   move(field);
                                   current = new Knight('W');
                                   refresh();
                               });

                           }
                           //Läufer
                           case 2, 5 -> {
                               boardArray[row][col] = new Bishop('W');
                               field.setIcon(bishopWhite);
                               field.addActionListener(e -> {
                                   move(field);
                                   current = new Bishop('W');
                                   refresh();
                               });

                           }
                           //Königin
                           case 3 -> {
                               boardArray[row][col] = new Queen('W');
                               field.setIcon(queenWhite);
                               field.addActionListener(e -> {
                                   move(field);
                                   current = new Queen('W');
                                   refresh();
                               });
                           }
                           //König
                           case 4 -> {
                               boardArray[row][col] = new King('W');
                               field.setIcon(kingWhite);
                           }
                       }
                       break;
                   //Weisse Bauern
                   case 1:
                       boardArray[row][col] = new Pawn('W');
                       field.setIcon(pawnWhite);
                       field.addActionListener(e -> {
                           move(field);
                           current = new Pawn('W');
                           refresh();
                       });
                       break;
                   //Schwarze Bauern
                   case 6:
                       boardArray[row][col] = new Pawn('B');
                       field.setIcon(pawnBlack);
                       field.setDisabledIcon(pawnBlack);
                       field.setEnabled(false);
                       break;
                   //Schwarze Seite
                   case 7:
                       field.setEnabled(false);
                       switch (row) {
                           //Türme
                           case 0, 7 -> {
                               boardArray[row][col] = new Rook('B');
                               field.setIcon(rookBlack);
                               field.setDisabledIcon(rookBlack);
                           }
                           //Springer
                           case 1, 6 -> {
                               boardArray[row][col] = new Knight('B');
                               field.setIcon(knightBlack);
                               field.setDisabledIcon(knightBlack);
                           }
                           //Läufer
                           case 2, 5 -> {
                               boardArray[row][col] = new Bishop('B');
                               field.setIcon(bishopBlack);
                               field.setDisabledIcon(bishopBlack);
                           }
                           //Königin
                           case 3 -> {
                               boardArray[row][col] = new Queen('B');
                               field.setIcon(queenBlack);
                               field.setDisabledIcon(queenBlack);
                           }
                           //König
                           case 4 -> {
                               boardArray[row][col] = new King('B');
                               field.setIcon(kingBlack);
                               field.setDisabledIcon(kingBlack);
                           }
                       }
                       break;
                   default:
                       boardArray[row][col] = new None('N');
                       field.setEnabled(false);
                       break;
                }
            }
        }
        turn = 0;
        windowFrame.setTitle("White to move");
        windowFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        windowFrame.add(boardPanel);
        windowFrame.setSize(1024,1024);
        windowFrame.setVisible(true);
    }
    private void refresh(){
        //Altes Panel leeren
        Component[] componentList = boardPanel.getComponents();
        for (Component current : componentList){
            boardPanel.remove(current);
        }


        //Panel mit veränderungen erstellen
        for(int col = 0; col < 8; col++, tempInt++) {
            for (int row = 0; row < 8; row++, tempInt++) {
                JButton field = new JButton();
                field.setBorderPainted(false);
                field.setBackground(Color.blue);
                field.setName(row + "" + col);

                switch (boardArray[row][col].color()) {
                    //Weisse Figuren
                    case 'W'-> {
                        switch (boardArray[row][col].getClass().getName().substring(12)) {
                            case "Pawn" -> {
                                field.setIcon(pawnWhite);
                                field.setDisabledIcon(pawnWhite);
                                if (turn != 1){
                                    field.addActionListener(e -> {
                                        current = new Pawn('W');
                                        move(field);
                                        refresh();
                                    });
                                }
                            }
                            case "Knight" -> {
                                field.setIcon(knightWhite);
                                field.setDisabledIcon(knightWhite);
                                if (turn != 1){
                                    field.addActionListener(e -> {
                                        current = new Knight('W');
                                        move(field);

                                        refresh();
                                    });
                                }
                            }
                            case "Bishop" -> {
                                field.setIcon(bishopWhite);
                                field.setDisabledIcon(bishopWhite);
                                if (turn != 1) {
                                    field.addActionListener(e -> {
                                        current = new Bishop('W');
                                        move(field);
                                        refresh();
                                    });
                                }

                            }
                            case "Queen" -> {
                                field.setIcon(queenWhite);
                                field.setDisabledIcon(queenWhite);
                                if (turn != 1){
                                    field.addActionListener(e -> {
                                        current = new Queen('W');
                                        move(field);
                                        refresh();
                                    });
                                }

                            }
                            case "King" -> {
                                if (check[1] == 'W'){field.setBackground(checkRed);}
                                field.setIcon(kingWhite);
                                field.setDisabledIcon(kingWhite);
                                kingExist[1] = true;
                                if (turn != 1){
                                    field.addActionListener(e -> {
                                        current = new King('W');
                                        move(field);
                                        refresh();
                                    });
                                }

                            }
                            case "Rook" -> {
                                field.setIcon(rookWhite);
                                field.setDisabledIcon(rookWhite);
                                if (turn != 1) {
                                    field.addActionListener(e -> {
                                        current = new Rook('W');
                                        move(field);
                                        refresh();
                                    });
                                }
                            }
                            default -> {}
                        }
                    }
                    //Schwarze Figuren
                    case 'B' -> {
                        switch (boardArray[row][col].getClass().getName().substring(12)) {
                            case "Pawn" -> {
                                field.setIcon(pawnBlack);
                                field.setDisabledIcon(pawnBlack);
                                if(turn != 0){
                                    field.addActionListener(e -> {
                                        current = new Pawn('B');
                                        move(field);
                                        refresh();
                                    });
                                }
                            }
                            case "Knight" -> {
                                field.setIcon(knightBlack);
                                field.setDisabledIcon(knightBlack);
                                if(turn != 0){
                                    field.addActionListener(e -> {
                                        current = new Knight('B');
                                        move(field);
                                        refresh();
                                    });
                                }
                            }
                            case "Bishop" -> {
                                field.setIcon(bishopBlack);
                                field.setDisabledIcon(bishopBlack);
                                if(turn != 0) {
                                    field.addActionListener(e -> {
                                        current = new Bishop('B');
                                        move(field);
                                        refresh();
                                    });
                                }
                            }
                            case "Queen" -> {
                                field.setIcon(queenBlack);
                                field.setDisabledIcon(queenBlack);
                                if (turn != 0){
                                    field.addActionListener(e -> {
                                        current = new Queen('B');
                                        move(field);
                                        refresh();
                                    });
                                }
                            }
                            case "King" -> {
                                if (check[0] == 'B'){field.setBackground(checkRed);}
                                field.setIcon(kingBlack);
                                field.setDisabledIcon(kingBlack);
                                kingExist[0] = true;
                                if (turn != 0){
                                    field.addActionListener(e -> {
                                        current = new King('B');
                                        move(field);
                                        refresh();
                                    });
                                }
                            }
                            case "Rook" -> {
                                field.setIcon(rookBlack);
                                field.setDisabledIcon(rookBlack);
                                if (turn != 0) {
                                    field.addActionListener(e -> {
                                        current = new Rook('B');
                                        move(field);
                                        refresh();
                                    });
                                }
                            }
                            default -> {}
                        }
                    }
                    //Nothing
                    case 'N' -> {
                    }
                }


                //Bewegung von figur
                for (Point p : moveSet) {
                    movePiece(new Point(row,col), p, field);
                }

                //Feld farbe
                if (field.getBackground() == Color.BLUE) {
                    if (tempInt % 2 == 0) {
                        field.setBackground(brown);
                    } else {
                        field.setBackground(Color.WHITE);
                    }

                }

                field.setPreferredSize(new Dimension(74,74));
                boardPanel.add(field);
            }
        }
        if (!kingExist[1]){
            winScreen("Black");
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        if (!kingExist[0]){
            winScreen("White");
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        kingExist = new boolean[2];
        SwingUtilities.updateComponentTreeUI(boardPanel);
    }
    private void move(JButton field){
        moveSet.clear();
        int row2 = Integer.parseInt(field.getName().substring(0, 1));
        int col2 = Integer.parseInt(field.getName().substring(1, 2));
        selected.setLocation(row2,col2);
        moveSet = boardArray[row2][col2].move(selected, boardArray);
        if (check[0] == 'B' || check[1] == 'W') {
                getsOutOfCheck(selected,current);
        }
    }
    private void promotion(Point p, char color){
        JFrame promotionFrame = new JFrame("Select Promotion");
        JPanel promotionPanel = new JPanel(new GridLayout(1,4));
        ArrayList<Pieces> pieces = new ArrayList<>();
        pieces.add( new Knight(color));
        pieces.add(new Rook(color));
        pieces.add(new Bishop(color));
        pieces.add(new Queen(color));
        promotionFrame.add(promotionPanel);
        promotionFrame.setSize(512,128);
        promotionFrame.setVisible(true);

        for (Pieces piece : pieces) {
            JButton option = new JButton();
            option.setName(piece.toString());
            option.setIcon(new ImageIcon(ClassLoader.getSystemResource(option.getName().substring(12, option.getName().indexOf("@")) + "_W.png")));
            option.setBackground(brown);
            option.setBorderPainted(false);
            promotionPanel.add(option);

            option.addActionListener(e ->  {
                promotionFrame.dispose();
                boardArray[p.x][p.y] = piece;

                refresh();
            });
        }
    }
    private void movePiece(Point location,Point p, JButton field) {
        int row = location.x;
        int col = location.y;
            if (row == p.getX() && col == p.getY()){
                field.setBackground(movementGreen);
                field.addActionListener(e -> {
                    check = new char[2];
                    boardArray[p.x][p.y] = current;
                    boardArray[selected.x][selected.y] = new None('N');
                    selected = new Point();
                    current = new None('N');
                    moveSet.clear();
                    turn++;

                    returnOnMove = boardArray[p.x][p.y].onMove(p,boardArray);
                    if (returnOnMove == null) {
                        returnOnMove = new char[2];
                    }
                    if (returnOnMove[0] == 'W' || returnOnMove[0] ==  'B'){
                        switch (returnOnMove[0]){
                            case 'W' -> check[0] = 'B';
                            case 'B' -> check[1] = 'W';
                        }
                    }

                    if (boardArray[location.x][location.y].getClass() == Pawn.class) {
                        if (returnOnMove[1] == 'W' || returnOnMove[1] == 'B') {
                            promotion(location, returnOnMove[1]);
                            boardArray[location.x][location.y].onMove(location,boardArray);
                            returnOnMove[1] = 'N';
                        }
                    } else if (boardArray[location.x][location.y].getClass() == King.class) {
                        switch (boardArray[location.x][location.y].color()){
                            case 'W'-> check[1] = 'N';
                            case 'B'-> check[0] = 'N';
                        }
                        refresh();
                    }
                    if (turn == 2){
                        windowFrame.setTitle("White to move");
                        turn = 0;
                    } else if (turn == 1) {
                        windowFrame.setTitle("Black to move");
                    }
                    refresh();

                });
            }
    }
    private void getsOutOfCheck(Point oldLocation, Pieces piece){
        int checkOptions = 0;
        outOfCheck = false;
        Pieces[][] tempCopy = new Pieces[8][8];
        char[] tempChar;

        for(int col = 0; col < 8; col++) {
            for (int row = 0; row < 8; row++) {
                switch (boardArray[row][col].getClass().getName().substring(12)){
                    case "Knight" -> tempCopy[row][col] = new Knight(boardArray[row][col].color());
                    case "Bishop" -> tempCopy[row][col] = new Bishop(boardArray[row][col].color());
                    case "King" -> tempCopy[row][col] = new King(boardArray[row][col].color());
                    case "None" -> tempCopy[row][col] = new None(boardArray[row][col].color());
                    case "Pawn" -> tempCopy[row][col] = new Pawn(boardArray[row][col].color());
                    case "Queen" -> tempCopy[row][col] = new Queen(boardArray[row][col].color());
                    case "Rook" -> tempCopy[row][col] = new Rook(boardArray[row][col].color());
                }
            }
        }

        ArrayList<Point> toRemove = new ArrayList<>();
        for (Point newLocation : moveSet){
            tempCopy[oldLocation.x][oldLocation.y] = new None('N');
            switch (piece.getClass().getName().substring(12)){
                case "Knight" -> tempCopy[newLocation.x][newLocation.y] = new Knight(piece.color());
                case "Bishop" -> tempCopy[newLocation.x][newLocation.y] = new Bishop(piece.color());
                case "King" -> tempCopy[newLocation.x][newLocation.y] = new King(piece.color());
                case "None" -> tempCopy[newLocation.x][newLocation.y] = new None(piece.color());
                case "Pawn" -> tempCopy[newLocation.x][newLocation.y] = new Pawn(piece.color());
                case "Queen" -> tempCopy[newLocation.x][newLocation.y] = new Queen(piece.color());
                case "Rook" -> tempCopy[newLocation.x][newLocation.y] = new Rook(piece.color());
            }

            for(int col = 0; col < 8; col++) {
                for (int row = 0; row < 8; row++) {
                    tempChar = tempCopy[row][col].onMove(new Point(row, col), tempCopy);

                    if (tempChar[0] != 'N') {
                        System.out.println("b");
                        if (tempChar[0] != piece.color()) {
                            System.out.println("a      ");
                            outOfCheck = true;
                            checkOptions++;
                            toRemove.add(newLocation);

                        }
                    }

                }
            }
            tempCopy[newLocation.x][newLocation.y] = new None('N');
            tempCopy[oldLocation.x][oldLocation.y] = piece;

        }
        System.out.println(checkOptions + "----" + moveSet.size());
        System.out.println(toRemove);
        if (checkOptions < moveSet.size()){
            outOfCheck = true;
            for (Point a : toRemove){
                moveSet.remove(a);
            }
        } else {
            moveSet.clear();
        }
    }
    private  void winScreen(String winner){
        JFrame winFrame = new JFrame("Game over");
        JPanel winPanel = new JPanel();
        JLabel winLabel = new JLabel(winner + " won the game!");
        JButton restart = new JButton("Play again");
        restart.addActionListener(e -> {
            windowFrame.dispose();
            winFrame.dispose();
            new Board();
        });

        winPanel.add(winLabel);
        winPanel.add(restart);
        winFrame.add(winPanel);
        winFrame.setLocation(windowFrame.getX() + 387,windowFrame.getY() + 472);
        winFrame.setSize(250,80);
        winFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        winFrame.setVisible(true);
    }
    private void checkmate(char color,JButton field){
        String text = "";
        switch (color){
            case 'W'-> text = "White";
            case 'B'-> text = "Black";
        }

                    move(field);
                    System.out.println(outOfCheck);

        if (!outOfCheck){
            winScreen(text);
        }


    }
}
