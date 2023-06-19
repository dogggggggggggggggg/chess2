package org.example;

import java.awt.*;
import java.util.ArrayList;

public interface Pieces {
    ArrayList<Point> move(Point location, Pieces[][] board);
    ArrayList<Point> checkForKing(Point p, Pieces[][] board);
    char color();
    char[] onMove(Point location, Pieces[][] board);
}
