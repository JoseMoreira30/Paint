package org.paint;

import org.academiadecodigo.simplegraphics.graphics.Color;

public class Game {

    public void startGame () {
        Grid grid = new Grid(400,400,10);
        grid.drawGridLines(20);
        Player player = new Player(20,20, Color.BLUE, grid);
        Movement movement = new Movement(player);
        Functionalities paint = new Functionalities(player);


    }
}
