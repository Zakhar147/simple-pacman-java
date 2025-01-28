package com.mygame.pacman;

import com.mygame.map.GameMap;

import javax.swing.*;
import java.util.Random;

public class Game {
    private Timer gameLoop;
    private Random rand = new Random();
    public static int score;
    public static int lives;
    public static boolean isGameOver;

    private GameMap map;

    public Game() {
        score = 0;
        lives = 3;
        isGameOver = false;
        this.map = new GameMap(gameLoop);
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
