package com.mygame.map;

import com.mygame.config.Config;
import com.mygame.entity.dynamic.Ghost;
import com.mygame.entity.dynamic.Pacman;
import com.mygame.entity.fixed.Food;
import com.mygame.entity.fixed.Wall;
import com.mygame.pacman.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GameMap extends JPanel implements KeyListener {
    private Set<Wall> walls;
    private Set<Ghost> ghosts;
    private Set<Food> foods;
    private Pacman pacman;

    private Map<String, Image> images;

    public GameMap(Game game) {
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
        draw(g);
    }
    public void draw(Graphics g) {
        g.drawImage(pacman.getImage(), pacman.getX(), pacman.getY(), pacman.getWidth(), pacman.getHeight(), null);

        for(Ghost ghost : ghosts) {
            g.drawImage(ghost.getImage(), ghost.getX(), ghost.getY(), ghost.getWidth(), ghost.getHeight(), null);
        }
        for(Wall wall : walls) {
            g.drawImage(wall.getImage(), wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight(), null);
        }
        g.setColor(Color.WHITE);
        for(Food food : foods) {
            g.fillRect(food.getX(), food.getY(), food.getWidth(), food.getHeight());
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

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
