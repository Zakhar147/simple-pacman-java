package com.mygame.board;

import com.mygame.config.Config;
import com.mygame.pacman.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameMap extends JPanel implements KeyListener {
    private Game game;

    public GameMap(Game game) {
        this.game = game;

        setPreferredSize(new Dimension(Config.BOARD_WIDTH, Config.BOARD_HEIGHT));
        setBackground(Color.black);
        addKeyListener(this);
        setFocusable(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
