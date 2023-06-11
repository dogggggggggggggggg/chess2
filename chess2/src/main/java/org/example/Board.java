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
    int turn;
    private int temp;

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
        for(int col = 0; col < 8; col++, temp++){
            for(int row = 0; row <8; row++, temp++) {
                //Feld erstellen und bemalen
                JButton field = new JButton();
                field.setBorderPainted(false);
                field.setName(row + "" + col);
                if (temp % 2 == 0){
                    field.setBackground(brown);
                } else {
                    field.setBackground(Color.WHITE);
                }
                field.setPreferredSize(new Dimension(64,64));
                boardPanel.add(field);

                //Brett aufsetzen
                switch (col){
                   //Weisse Seite
                   case 0:
                       switch (row) {
                           //Türme
                           case 0, 7 -> {
                               boardArray[row][col] = new Rook('W');
                               field.addActionListener(e -> {
                                   moveSet.clear();
                                   int row2 = Integer.parseInt(field.getName().substring(0, 1));
                                   int col2 = Integer.parseInt(field.getName().substring(1, 2));
                                   selected.setLocation(row2, col2);
                                   moveSet = boardArray[selected.x][selected.y].move(selected, boardArray);
                                   current = new Rook('W');
                                   turn++;
                                   refresh();
                               });
                               field.setIcon(rookWhite);
                           }
                           //Springer
                           case 1, 6 -> {
                               boardArray[row][col] = new Knight('W');
                               field.addActionListener(e -> {
                                   moveSet.clear();
                                   int row2 = Integer.parseInt(field.getName().substring(0, 1));
                                   int col2 = Integer.parseInt(field.getName().substring(1, 2));
                                   selected.setLocation(row2,col2);
                                   moveSet = boardArray[row2][col2].move(selected, boardArray);

                                   current = new Knight('W');
                                   turn++;
                                   refresh();
                               });
                               field.setIcon(knightWhite);
                           }
                           //Läufer
                           case 2, 5 -> {
                               boardArray[row][col] = new Bishop('W');
                               field.addActionListener(e -> {
                                   moveSet.clear();
                                   int row2 = Integer.parseInt(field.getName().substring(0, 1));
                                   int col2 = Integer.parseInt(field.getName().substring(1, 2));
                                   selected.setLocation(row2, col2);
                                   moveSet = boardArray[selected.x][selected.y].move(selected, boardArray);
                                   current = new Bishop('W');
                                   turn++;
                                   refresh();
                               });
                               field.setIcon(bishopWhite);
                           }
                           //Königin
                           case 3 -> {
                               boardArray[row][col] = new Queen('W');
                               field.setIcon(queenWhite);
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
                       //boardArray[row][col] = new None('N');
                       break;
                   //Schwarze Bauern
                   case 6:
                       boardArray[row][col] = new Pawn('B');
                       field.setIcon(pawnBlack);
                       field.setDisabledIcon(pawnBlack);
                       field.setEnabled(false);
                       //boardArray[row][col] = new None('N');
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
        for(int col = 0; col < 8; col++, temp++) {
            for (int row = 0; row < 8; row++, temp++) {
                JButton field = new JButton();
                field.setBorderPainted(false);

                switch (boardArray[row][col].color()) {
                    //Weisse Figuren
                    case 'W'-> {
                        if (turn == 1 || turn == 2 || turn == 4 ){
                            field.setEnabled(false);
                        }
                        switch (boardArray[row][col].getClass().getName().substring(12)) {
                        case "Pawn" -> {
                            field.setIcon(pawnWhite);
                            field.setDisabledIcon(pawnWhite);}
                        case "Knight" -> {
                            field.setIcon(knightWhite);
                            field.setDisabledIcon(knightWhite);
                            if (turn != 3){
                            field.addActionListener(e -> {
                                moveSet.clear();
                                int row2 = Integer.parseInt(field.getName().substring(0, 1));
                                int col2 = Integer.parseInt(field.getName().substring(1, 2));
                                selected.setLocation(row2,col2);
                                moveSet = boardArray[row2][col2].move(selected, boardArray);

                                current = new Knight('W');
                                turn++;
                                refresh();
                            });
                            }
                        }
                        case "Bishop" -> {
                            if (turn != 3) {
                                field.addActionListener(e -> {
                                    moveSet.clear();
                                    int row2 = Integer.parseInt(field.getName().substring(0, 1));
                                    int col2 = Integer.parseInt(field.getName().substring(1, 2));
                                    selected.setLocation(row2, col2);
                                    moveSet = boardArray[selected.x][selected.y].move(selected, boardArray);
                                    current = new Bishop('W');
                                    turn++;
                                    refresh();
                                });
                            }
                            field.setIcon(bishopWhite);
                            field.setDisabledIcon(bishopWhite);
                        }
                        case "Queen" -> {
                            field.setIcon(queenWhite);
                            field.setDisabledIcon(queenWhite);
                        }
                        case "King" -> {
                            field.setIcon(kingWhite);
                            field.setDisabledIcon(kingWhite);
                        }
                        case "Rook" -> {
                           if (turn != 3) {
                                field.addActionListener(e -> {
                                    moveSet.clear();
                                    int row2 = Integer.parseInt(field.getName().substring(0, 1));
                                    int col2 = Integer.parseInt(field.getName().substring(1, 2));
                                    selected.setLocation(row2, col2);
                                    moveSet = boardArray[selected.x][selected.y].move(selected, boardArray);
                                    current = new Rook('W');
                                    turn++;
                                    refresh();
                                });
                            }
                            field.setIcon(rookWhite);
                            field.setDisabledIcon(rookWhite);
                        }

                        default -> {

                        }
                    }
                    }
                    //Schwarze Figuren
                    case 'B' -> {
                        if (turn == 0  || turn == 3 || turn == 4){
                            field.setEnabled(false);
                        }
                        switch (boardArray[row][col].getClass().getName().substring(12)) {
                            case "Pawn" -> {
                                field.setIcon(pawnBlack);
                                field.setDisabledIcon(pawnBlack);
                            }
                            case "Knight" -> {
                                field.setIcon(knightBlack);
                                field.setDisabledIcon(knightBlack);
                                if(turn != 1){
                                field.addActionListener(e -> {
                                    moveSet.clear();
                                    int row2 = Integer.parseInt(field.getName().substring(0, 1));
                                    int col2 = Integer.parseInt(field.getName().substring(1, 2));
                                    selected.setLocation(row2,col2);
                                    moveSet = boardArray[row2][col2].move(selected, boardArray);

                                    current = new Knight('B');
                                    turn++;
                                    refresh();
                                });
                                }
                            }
                            case "Bishop" -> {
                                if(turn != 1) {
                                    field.addActionListener(e -> {
                                        moveSet.clear();
                                        int row2 = Integer.parseInt(field.getName().substring(0, 1));
                                        int col2 = Integer.parseInt(field.getName().substring(1, 2));
                                        selected.setLocation(row2, col2);
                                        moveSet = boardArray[selected.x][selected.y].move(selected, boardArray);
                                        current = new Bishop('B');
                                        turn++;
                                        refresh();
                                    });
                                }
                                field.setIcon(bishopBlack);
                                field.setDisabledIcon(bishopBlack);
                            }
                            case "Queen" -> {
                                field.setIcon(queenBlack);
                                field.setDisabledIcon(queenBlack);
                            }
                            case "King" -> {
                                field.setIcon(kingBlack);
                                field.setDisabledIcon(kingBlack);
                            }
                            case "Rook" -> {
                                if (turn != 1) {
                                    field.addActionListener(e -> {
                                        moveSet.clear();
                                        int row2 = Integer.parseInt(field.getName().substring(0, 1));
                                        int col2 = Integer.parseInt(field.getName().substring(1, 2));
                                        selected.setLocation(row2, col2);
                                        moveSet = boardArray[selected.x][selected.y].move(selected, boardArray);
                                        current = new Rook('B');
                                        turn++;
                                        refresh();
                                    });
                                }
                                field.setIcon(rookBlack);
                                field.setDisabledIcon(rookBlack);
                            }
                            default -> {
                            }
                        }
                    }
                    //Nothing
                    case 'N' -> {
                    }
                }

                //Bewegung von figur
                for (Point p : moveSet){
                    if (row == p.getX() && col == p.getY()){
                        field.addActionListener(e -> {
                            boardArray[(int) p.getX()][(int) p.getY()] = current;
                            boardArray[(int) selected.getX()][(int) selected.getY()] = new None('N');
                            selected = new Point();
                            current = new None('N');
                            moveSet.clear();
                            turn++;
                            if (turn == 4){
                                turn = 0;
                            }
                            refresh();
                        });
                    }
                }

                //Feld farbe
                field.setName(row + "" + col);
                if (temp % 2 == 0) {
                    field.setBackground(brown);
                } else {
                    field.setBackground(Color.WHITE);
                }
                field.setPreferredSize(new Dimension(74,74));
                boardPanel.add(field);
            }
        }

        SwingUtilities.updateComponentTreeUI(boardPanel);
    }
}


/*
swing 8x8 feld
jedes feld ein button.
button hat array liste aus feld + figur
feld hat ein array wert z.B int[x][y]
array für jede figur  z.B String[farbe][figur]
figuren bewegen sich indem man + oder - im array macht z.b Bauer + 2x

-------------------------------------------------------------------------

 */
