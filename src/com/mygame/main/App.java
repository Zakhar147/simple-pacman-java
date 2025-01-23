package com.mygame.main;

import com.mygame.board.GameMap;
import com.mygame.config.Config;
import com.mygame.pacman.Game;

import javax.swing.JFrame;

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Pacman");
        Game pacman = new Game();
        GameMap pacmanMap = new GameMap(pacman);

        createAndShowGUI(frame, pacmanMap);
    }

    private static void createAndShowGUI(JFrame frame, GameMap gameMap) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Config.BOARD_WIDTH, Config.BOARD_HEIGHT);
        frame.add(gameMap);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        gameMap.requestFocus();
    }
}