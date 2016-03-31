package controller;

import java.awt.event.KeyListener;
import java.util.Map;
import java.awt.event.*;

public class KeyboardHandler implements KeyListener {
    Map<Integer, Runnable> keystrokes;

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {
        boolean x = (e.getKeyCode() == KeyEvent.VK_DELETE);
    }

    public void keyTyped(KeyEvent e) {
    }
}
