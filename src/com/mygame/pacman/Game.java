package com.mygame.pacman;

import com.mygame.map.GameMap;

import javax.swing.*;
import java.util.Random;

public class Game {
    private Timer gameLoop;
    private Random rand = new Random();
    private int score;
    private int lives;
    private boolean isGameOver;

    private GameMap map;

    public Game() {
        this.score = 0;
        this.lives = 3;
        this.isGameOver = false;
        this.map = new GameMap(this);
        gameLoop = new Timer(10, this.map);

        startGame();
    }

    public GameMap getMap() {
        return map;
    }

    private void startGame() {
        gameLoop.start();
    }

}
