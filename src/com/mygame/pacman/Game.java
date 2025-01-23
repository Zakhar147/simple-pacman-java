package com.mygame.pacman;

import javax.swing.*;
import java.util.Random;

public class Game {
    private Timer gameLoop;
    private Random rand = new Random();
    private int score;
    private int lives;
    private boolean isGameOver;

    public Game() {
        this.score = 0;
        this.lives = 3;
        this.isGameOver = false;
    }
}
