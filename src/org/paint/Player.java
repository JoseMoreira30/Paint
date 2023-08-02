package org.paint;


import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Player {

    private Rectangle player;
    private Grid grid;
    private int posX;
    private int posY;
    private Movement movement;


    public Player(int width, int height, Color color, Grid grid) {

        player = new Rectangle(Grid.padding, Grid.padding, width, height);
        player.setColor(color);
        player.draw();
        player.fill();
        player.grow(2,2);

        


    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public Rectangle getPlayer() {
        return player;
    }
}