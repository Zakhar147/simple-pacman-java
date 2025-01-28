package com.mygame.map;

import com.mygame.config.Config;
import com.mygame.entity.base.DynamicEntity;
import com.mygame.entity.base.Entity;
import com.mygame.entity.base.FixedEntity;
import com.mygame.entity.dynamic.Ghost;
import com.mygame.entity.dynamic.Pacman;
import com.mygame.entity.fixed.Food;
import com.mygame.entity.fixed.Wall;
import com.mygame.pacman.Game;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GameMap extends JPanel implements KeyListener, ActionListener {
    private Set<Entity> walls;
    private Set<Ghost> ghosts;
    private Set<Food> foods;
    private Pacman pacman;

    private Timer gameloop;

    private Map<String, Image> images;

    public GameMap(Timer gameLoop) {
        this.gameloop = gameLoop;

        setPreferredSize(new Dimension(Config.BOARD_WIDTH, Config.BOARD_HEIGHT));
        setBackground(Color.black);
        addKeyListener(this);
        setFocusable(true);

        createImages();
        createSets();

        loadMap();
    }

    public void loadMap() {
        for (int r = 0; r < Config.ROW_COUNT; r++) {
            for (int c = 0; c < Config.COLUMN_COUNT; c++) {
                String row = Config.TILE_MAP[r];
                char tileMapChar = row.charAt(c);

                int x = c* Config.TILE_SIZE;
                int y = r* Config.TILE_SIZE;

                switch (tileMapChar) {
                    case 'X' -> walls.add(new Wall(x, y, Config.TILE_SIZE, Config.TILE_SIZE, images.get("wall")));
                    case 'b' -> ghosts.add(new Ghost(x, y, Config.TILE_SIZE, Config.TILE_SIZE, images.get("blueGhost"), 0, 0, 'U'));
                    case 'o' -> ghosts.add(new Ghost(x, y, Config.TILE_SIZE, Config.TILE_SIZE, images.get("orangeGhost"), 0, 0, 'U'));
                    case 'r' -> ghosts.add(new Ghost(x, y, Config.TILE_SIZE, Config.TILE_SIZE, images.get("redGhost"), 0, 0, 'U'));
                    case 'p' -> ghosts.add(new Ghost(x, y, Config.TILE_SIZE, Config.TILE_SIZE, images.get("pinkGhost"), 0, 0, 'U'));
                    case 'P' -> pacman = new Pacman(x, y, Config.TILE_SIZE, Config.TILE_SIZE, images.get("pacmanRight"), 0, 0, 'R');
                    case ' ' -> foods.add(new Food(x + 14, y + 14, Config.TILE_FOOD_SIZE , Config.TILE_FOOD_SIZE, images.get("food")));
                }
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawEntities(g);
    }

    public void drawEntities(Graphics g) {
        g.drawImage(pacman.getImage(), pacman.getX(), pacman.getY(), pacman.getWidth(), pacman.getHeight(), null);

        for(Ghost ghost : ghosts) {
            g.drawImage(ghost.getImage(), ghost.getX(), ghost.getY(), ghost.getWidth(), ghost.getHeight(), null);
        }
        for(Entity wall : walls) {
            g.drawImage(wall.getImage(), wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight(), null);
        }
        g.setColor(Color.WHITE);
        for(Food food : foods) {
            g.fillRect(food.getX(), food.getY(), food.getWidth(), food.getHeight());
        }

        g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        if(Game.isGameOver) {
            g.drawString("Game Over: " + String.valueOf(Game.score), Config.TILE_SIZE/2, Config.TILE_SIZE/2);
        }else {
            g.drawString("x" + String.valueOf(Game.lives) + " Score: " + String.valueOf(Game.score), Config.TILE_SIZE/2, Config.TILE_SIZE/2);
        }
    }

    public void moveEntities() {
        pacman.setX(pacman.getX() + pacman.getVelocityX());
        pacman.setY(pacman.getY() + pacman.getVelocityY());
        for(Entity wall : walls) {
            if(wall.collision(pacman, wall )) {
                pacman.setX(pacman.getX() - pacman.getVelocityX());
                pacman.setY(pacman.getY() - pacman.getVelocityY());
                break;
            }
        }

        for(Ghost ghost : ghosts) {
            if(ghost.collision(pacman, ghost)) {
                Game.lives--;
                if(Game.lives == 0) {
                    Game.isGameOver = true;
                    return;
                }
                resetPorisitons();
            }
            if(ghost.getY() == Config.TILE_SIZE * 9 && ghost.getDirection() != 'U' && ghost.getDirection() != 'D') {
                ghost.updateDirection('U', walls);
            }

            ghost.setX(ghost.getX() + ghost.getVelocityX());
            ghost.setY(ghost.getY() + ghost.getVelocityY());
            for(Entity wall : walls) {
                if(wall.collision(ghost, wall)) {
                    System.out.println("Ghost collision");
                    ghost.setX(ghost.getX() - ghost.getVelocityX());
                    ghost.setY(ghost.getY() - ghost.getVelocityY());
                    ghost.setNewDirection(walls);
                    break;
                }
            }
        }

        Food foodEaten = null;
        for(Food food : foods) {
            if(food.collision(pacman, food)) {
                foodEaten = food;
                Game.score += 10;
            }
        }
        foods.remove(foodEaten);

        if(foods.isEmpty()) {
            loadMap();
            resetPorisitons();
        }
    }

    public void startGhostsMoving() {
        for(Ghost ghost : ghosts) {
            ghost.setNewDirection(walls);
        }
    }

    public void resetPorisitons() {
        pacman.reset();
        pacman.setVelocityX(0);
        pacman.setVelocityY(0);

        for(Ghost ghost : ghosts) {
            ghost.reset(walls);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        moveEntities();
        repaint();

        if(Game.isGameOver) {
            gameloop.stop();;
        }
    }

    private void createSets() {
        walls = new HashSet<>();
        ghosts = new HashSet<>();
        foods = new HashSet<>();
    }
    private void createImages() {
        images = new HashMap<>();
        images.put("wall", new ImageIcon(getClass().getResource("/accets/wall.png")).getImage());
        images.put("blueGhost", new ImageIcon(getClass().getResource("/accets/blueGhost.png")).getImage());
        images.put("orangeGhost", new ImageIcon(getClass().getResource("/accets/orangeGhost.png")).getImage());
        images.put("pinkGhost", new ImageIcon(getClass().getResource("/accets/pinkGhost.png")).getImage());
        images.put("redGhost", new ImageIcon(getClass().getResource("/accets/redGhost.png")).getImage());
        images.put("pacmanUp", new ImageIcon(getClass().getResource("/accets/pacmanUp.png")).getImage());
        images.put("pacmanDown", new ImageIcon(getClass().getResource("/accets/pacmanDown.png")).getImage());
        images.put("pacmanLeft", new ImageIcon(getClass().getResource("/accets/pacmanLeft.png")).getImage());
        images.put("pacmanRight", new ImageIcon(getClass().getResource("/accets/pacmanRight.png")).getImage());
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (Game.isGameOver) {
            loadMap();
            resetPorisitons();
            Game.lives = 3;
            Game.score = 0;
            Game.isGameOver = false;
            gameloop.start();;
        }

        if(e.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("KEY PRESSED: " + e.getKeyChar());
            pacman.updateDirection('U', walls);
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.println("KEY PRESSED: " + e.getKeyChar());
            pacman.updateDirection('D', walls);
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("KEY PRESSED: " + e.getKeyChar());
            pacman.updateDirection('L', walls);
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("KEY PRESSED: " + e.getKeyChar());
            pacman.updateDirection('R', walls);
        }

        if(pacman.getDirection() == 'U') {
            pacman.setImage(images.get("pacmanUp"));
        }
        else if(pacman.getDirection() == 'D') {
            pacman.setImage(images.get("pacmanDown"));
        }
        else if(pacman.getDirection() == 'L') {
            pacman.setImage(images.get("pacmanLeft"));
        }
        else if(pacman.getDirection() == 'R') {
            pacman.setImage(images.get("pacmanRight"));
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
}