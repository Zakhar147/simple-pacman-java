package com.mygame.pacman;

import com.mygame.map.GameMap;

import javax.swing.*;

public class Game {
    private Timer gameLoop;
    public static int score;
    public static int lives;
    public static boolean isGameOver;

    private GameMap map;

    public Game() {
        score = 0;
        lives = 3;
        isGameOver = false;
        this.map = new GameMap(this);
        gameLoop = new Timer(10, this.map);

        startGame();
    }

    public GameMap getMap() {
        return map;
    }

    private void startGame() {
        this.map.startGhostsMoving();
        gameLoop.start();
    }

    public Timer getGameLoop() {
        return gameLoop;
    }
}
