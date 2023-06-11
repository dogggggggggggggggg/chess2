package org.example;

import java.awt.*;
import java.util.ArrayList;

public record None(char color) implements Pieces {

    public ArrayList<Point> move(Point location, Pieces[][] board) {
        return null;
    }


}
