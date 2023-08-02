package org.paint;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class Movement implements KeyboardHandler {

    private Player player;
    private Grid grid;
    private Game game;

    private Rectangle playerRect;
    public Movement(Player player) {

    this.player = player;
    this.playerRect = player.getPlayer();
    keyboardInit();


    }

    public void keyboardInit() {

        Keyboard keyboard = new Keyboard(this);

        KeyboardEvent rightPress = new KeyboardEvent();
        rightPress.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        rightPress.setKey(KeyboardEvent.KEY_RIGHT);

        KeyboardEvent leftPress = new KeyboardEvent();
        leftPress.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        leftPress.setKey(KeyboardEvent.KEY_LEFT);

        KeyboardEvent upPress = new KeyboardEvent();
        upPress.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        upPress.setKey(KeyboardEvent.KEY_UP);

        KeyboardEvent downPress = new KeyboardEvent();
        downPress.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        downPress.setKey(KeyboardEvent.KEY_DOWN);

        keyboard.addEventListener(rightPress);
        keyboard.addEventListener(leftPress);
        keyboard.addEventListener(upPress);
        keyboard.addEventListener(downPress);

    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_RIGHT:
                if (player.getPosX() < 360) {
                    player.setPosX(player.getPosX() + 20);
                    playerRect.translate(20, 0);
                }
                break;

            case KeyboardEvent.KEY_LEFT:
                if (player.getPosX() > 0) {
                    player.setPosX(player.getPosX() - 20);
                    playerRect.translate(-20, 0);
                }
                break;

            case KeyboardEvent.KEY_DOWN:
                if (player.getPosY() < 360) {
                    player.setPosY(player.getPosY() + 20);
                    playerRect.translate(0, 20);
                }
                break;

            case KeyboardEvent.KEY_UP:
                if (player.getPosY() > 10) {
                    player.setPosY(player.getPosY() - 20);
                    playerRect.translate(0, -20);
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
