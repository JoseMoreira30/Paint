package org.paint;

import org.academiadecodigo.simplegraphics.graphics.Line;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Grid {

    private Rectangle painter;
    private List<Line> lines;
    public static final int padding = 10;


    public Grid(int width, int height, int padding) {
        this.painter = new Rectangle(padding, padding, width - 2 * padding, height - 2 * padding);
        this.lines = new ArrayList<>();
        painter.draw();
    }
    public void drawGridLines(int spacing) {

        int maxX = painter.getX() + painter.getWidth();
        int maxY = painter.getY() + painter.getHeight();

        for (int x = painter.getX(); x < maxX; x += spacing) {
            Line line = new Line(x, painter.getY(), x, maxY);
            line.draw();
            lines.add(line);
        }

        for (int y = painter.getY(); y < maxY; y += spacing) {
            Line line = new Line(painter.getX(), y, maxX, y);
            line.draw();
            lines.add(line);
        }

    }

}