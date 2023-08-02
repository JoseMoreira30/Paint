package org.paint;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import java.io.*;
import java.util.ArrayList;

public class Functionalities implements KeyboardHandler {

    private Player player;
    private ArrayList<Rectangle> list;

    private int initialPlayerX;
    private int initialPlayerY;

    public Functionalities(Player player) {
        this.player = player;
        list = new ArrayList<>();
        keyboardInit();
    }

    private void paintRect() {

        for (int i = list.size() - 1; i >= 0; i--) {
            Rectangle rect = list.get(i);
            if (rect.getY() == player.getPosY() + 10 && rect.getX() == player.getPosX() + 10) {
                rect.delete();
                list.remove(i);
                return;
            }
        }

        Rectangle rect = new Rectangle(player.getPosX() + 10, player.getPosY() + 10, 20, 20);
        rect.setColor(Color.PINK);
        rect.draw();
        rect.fill();
        list.add(rect);
    }

    private void clearAll () {

        for (Rectangle rect : new ArrayList<>(list)) {
            rect.delete();
        }

        list.clear();

    }

    private void savePaint() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("resources/save.txt"));

        initialPlayerX = player.getPosX();
        initialPlayerY = player.getPosY();

        for (Rectangle rect : list) {
            int relativeX = rect.getX() - initialPlayerX;
            int relativeY = rect.getY() - initialPlayerY;

            bufferedWriter.write(relativeX + "," + relativeY + "," + rect.getWidth() + "," + rect.getHeight());
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }

    private void loadPaint() throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader("resources/save.txt"));

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] parts = line.split(",");

            int relativeX = Integer.parseInt(parts[0]);
            int relativeY = Integer.parseInt(parts[1]);
            int width = Integer.parseInt(parts[2]);
            int height = Integer.parseInt(parts[3]);

            int actualX = initialPlayerX + relativeX;
            int actualY = initialPlayerY + relativeY;

            Rectangle rect = new Rectangle(actualX, actualY, width, height);
            rect.setColor(Color.PINK);
            rect.draw();
            rect.fill();

            list.add(rect);
        }

        bufferedReader.close();
    }


    public void keyboardInit() {
        Keyboard keyboard = new Keyboard(this);

        KeyboardEvent spacePress = new KeyboardEvent();
        spacePress.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        spacePress.setKey(KeyboardEvent.KEY_SPACE);

        KeyboardEvent cPress = new KeyboardEvent();
        cPress.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        cPress.setKey(KeyboardEvent.KEY_C);

        KeyboardEvent sPress = new KeyboardEvent();
        sPress.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        sPress.setKey(KeyboardEvent.KEY_S);

        KeyboardEvent lPress = new KeyboardEvent();
        lPress.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        lPress.setKey(KeyboardEvent.KEY_L);


        keyboard.addEventListener(spacePress);
        keyboard.addEventListener(cPress);
        keyboard.addEventListener(sPress);
        keyboard.addEventListener(lPress);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {
            paintRect();
        }
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_C) {
            clearAll();
        }
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_S) {
            try {
                savePaint();
                System.out.println("Saved");
            } catch (IOException e) {
                e.printStackTrace();

                throw new RuntimeException(e);
            }
        }
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_L) {
            try {
                loadPaint();
                System.out.println("Loaded");
            } catch (IOException e) {
                e.printStackTrace();

                throw new RuntimeException(e);
            }
        }


    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
