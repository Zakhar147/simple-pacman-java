import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Random;
import javax.swing.*;

public class TestGame extends JPanel implements ActionListener, KeyListener {
    class Block {
        int x;
        int y;
        int width;
        int height;
        Image image;

        int startX;
        int startY;
        char direction = 'U';
        int velicityX = 0;
        int velicityY = 0;

        Block(Image image, int x, int y, int width, int height) {
            this.image = image;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.startX = x;
            this.startY = y;
        }
        void updateDirection(char direction) {
            char prevDirection = this.direction;
            this.direction = direction;
            updateVelocity();
            this.x += velicityX;
            this.y += velicityY;
            for(Block wall : walls) {
                if(collision(this, wall)) {
                    this.x -=  this.velicityX;
                    this.y -=  this.velicityY;
                    this.direction = prevDirection;
                    updateVelocity();
                }
            }

        }

        void updateVelocity() {
            if(this.direction == 'U') {
                this.velicityX = 0;
                this.velicityY = -tileSize /20;

            }
            else if(this.direction == 'D') {
                this.velicityX = 0;
                this.velicityY = tileSize /20;
            }
            else if(this.direction == 'L') {
                this.velicityX = -tileSize /20;
                this.velicityY = 0;
            }
            else if(this.direction == 'R') {
                this.velicityX = tileSize /20;
                this.velicityY = 0;
            }
        }

        void reset() {
            this.x = this.startX;
            this.y = this.startY;
        }

        public String toString() {
            return "Block{" +
                    "x=" + x +
                    ", y=" + y +
                    ", width=" + width +
                    ", height=" + height +
                    ", direction=" + direction +
                    ", velocityX=" + velicityX +
                    ", velocityY=" + velicityY +
                    '}';
        }
    }

    private int rowCount = 21;
    private int colCount = 19;
    private int tileSize = 32;
    private int boardWidth = colCount * tileSize;
    private int boardHeight = rowCount * tileSize;

    //imgs
    private Image wallImage;
    private Image blueGhostImage;
    private Image orangeGhostImage;
    private Image pinkGhostImage;
    private Image redGhostImage;
    private Image pacmanUpImage;
    private Image pacmanDownImage;
    private Image pacmanLeftImage;
    private Image pacmanRightImage;

    //X = wall, O = skip, P = pac man, ' ' = food
    //Ghosts: b = blue, o = orange, p = pink, r = red
    private String[] tileMap = {
            "XXXXXXXXXXXXXXXXXXX",
            "X        X        X",
            "X XX XXX X XXX XX X",
            "X                 X",
            "X XX X XXXXX X XX X",
            "X    X       X    X",
            "XXXX XXXX XXXX XXXX",
            "OOOX X       X XOOO",
            "XXXX X XXrXX X XXXX",
            "X       bpo       X",
            "XXXX X XXXXX X XXXX",
            "OOOX X       X XOOO",
            "XXXX X XXXXX X XXXX",
            "X        X        X",
            "X XX XXX X XXX XX X",
            "X  X     P     X  X",
            "XX X X XXXXX X X XX",
            "X    X   X   X    X",
            "X XXXXXX X XXXXXX X",
            "X                 X",
            "XXXXXXXXXXXXXXXXXXX"
    };

    HashSet<Block> walls;
    HashSet<Block> foods;
    HashSet<Block> ghosts;
    Block pacman;

    Timer gameLoop;
    char[] directions = {'U', 'D', 'L', 'R'};
    Random rand = new Random();
    int score = 0;
    int lives = 3;
    boolean gameOver = false;

    TestGame() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.black);
        addKeyListener(this);
        setFocusable(true);

        wallImage = new ImageIcon(getClass().getResource("./accets/wall.png")).getImage();
        blueGhostImage = new ImageIcon(getClass().getResource("./accets/blueGhost.png")).getImage();
        orangeGhostImage = new ImageIcon(getClass().getResource("./accets/orangeGhost.png")).getImage();
        pinkGhostImage = new ImageIcon(getClass().getResource("./accets/pinkGhost.png")).getImage();
        redGhostImage = new ImageIcon(getClass().getResource("./accets/redGhost.png")).getImage();

        pacmanUpImage = new ImageIcon(getClass().getResource("./accets/pacmanUp.png")).getImage();
        pacmanDownImage = new ImageIcon(getClass().getResource("./accets/pacmanDown.png")).getImage();
        pacmanLeftImage = new ImageIcon(getClass().getResource("./accets/pacmanLeft.png")).getImage();
        pacmanRightImage = new ImageIcon(getClass().getResource("./accets/pacmanRight.png")).getImage();

        loadMap();
        for(Block ghost : ghosts) {
            char newDirection = directions[rand.nextInt(directions.length)];
            ghost.updateDirection(newDirection);
        }
        gameLoop = new Timer(7, this);
        gameLoop.start();
    }

    public void loadMap() {
        walls = new HashSet<Block>();
        foods = new HashSet<Block>();
        ghosts = new HashSet<Block>();

        for (Block wall : walls) {
            System.out.println(walls);
        }
        for (Block food : foods) {
            System.out.println(foods);
        }
        for (Block ghost : ghosts) {
            System.out.println(ghosts);
        }

        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                String row = tileMap[r];
                char tileMapChar = row.charAt(c);

                int x = c* tileSize;
                int y = r* tileSize;

                if(tileMapChar == 'X') {
                    Block wall = new Block(wallImage, x, y, tileSize, tileSize);
                    walls.add(wall);
                }
                else if(tileMapChar == 'b') {
                    Block ghost = new Block(blueGhostImage, x, y, tileSize, tileSize);
                    ghosts.add(ghost);
                }
                else if(tileMapChar == 'o') {
                    Block ghost = new Block(orangeGhostImage, x, y, tileSize, tileSize);
                    ghosts.add(ghost);
                }
                else if(tileMapChar == 'r') {
                    Block ghost = new Block(redGhostImage, x, y, tileSize, tileSize);
                    ghosts.add(ghost);
                }
                else if(tileMapChar == 'p') {
                    Block ghost = new Block(pinkGhostImage, x, y, tileSize, tileSize);
                    ghosts.add(ghost);
                }
                else if (tileMapChar == 'P') {
                    pacman = new Block(pacmanRightImage, x, y, tileSize, tileSize);
                }
                else if (tileMapChar == ' ') {
                    Block food = new Block(null, x + 14, y + 14 , 4, 4);
                    foods.add(food);
                }
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(pacman.image, pacman.x, pacman.y, pacman.width, pacman.height, null);

        for(Block ghost : ghosts) {
            g.drawImage(ghost.image, ghost.x, ghost.y, ghost.width, ghost.height, null);
        }
        for (Block wall : walls) {
            g.drawImage(wall.image, wall.x, wall.y, wall.width, wall.height, null);
        }
        g.setColor(Color.WHITE);
        for(Block food : foods) {
            g.fillRect(food.x, food.y, food.width, food.height);
        }

        g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        if(gameOver) {
            g.drawString("Game Over: " + String.valueOf(score), tileSize/2, tileSize/2);
        }else {
            g.drawString("x" + String.valueOf(lives) + " Score: " + String.valueOf(score), tileSize/2, tileSize/2);
        }

    }

    public void move() {
        pacman.x += pacman.velicityX;
        pacman.y += pacman.velicityY;

        for(Block wall : walls) {
            if(collision(pacman, wall )) {
                pacman.x -= pacman.velicityX;
                pacman.y -= pacman.velicityY;
                break;
            }
        }

        for(Block ghost : ghosts) {
            if(collision(pacman, ghost)) {
                lives--;
                if(lives == 0) {
                    gameOver = true;
                    return;
                }
                resetPorisitons();
            }
            if(ghost.y == tileSize * 9 && ghost.direction != 'U' && ghost.direction != 'D') {
                ghost.updateDirection('U');
            }

            ghost.x += ghost.velicityX;
            ghost.y += ghost.velicityY;
            for(Block wall : walls) {
                if(collision(ghost, wall )) {
                    ghost.x -= ghost.velicityX;
                    ghost.y -= ghost.velicityY;
                    char newDirection = directions[rand.nextInt(directions.length)];
                    ghost.updateDirection(newDirection);
                    break;
                }
            }
        }

        Block foodEaten = null;
        for(Block food : foods) {
            if(collision(pacman, food)) {
                foodEaten = food;
                score += 10;
            }
        }
        foods.remove(foodEaten);

        if(foods.isEmpty()) {
            loadMap();
            resetPorisitons();
        }
    }

    public boolean collision(Block a, Block b) {
        return  a.x < b.x + b.width &&
                a.x + a.width > b.x &&
                a.y < b.y + b.height &&
                a.y + a.height > b.y;
    }

    public void resetPorisitons() {
        pacman.reset();
        pacman.velicityX = 0;
        pacman.velicityY = 0;

        for(Block ghost : ghosts) {
            ghost.reset();
            char newDirection = directions[rand.nextInt(directions.length)];
            ghost.updateDirection(newDirection);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();

        if(gameOver) {
            gameLoop.stop();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (gameOver) {
            loadMap();
            resetPorisitons();
            lives = 3;
            score = 0;
            gameOver = false;
            gameLoop.start();
        }

        if(e.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("KEY PRESSED: " + e.getKeyChar());
            pacman.updateDirection('U');
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.println("KEY PRESSED: " + e.getKeyChar());
            pacman.updateDirection('D');
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("KEY PRESSED: " + e.getKeyChar());
            pacman.updateDirection('L');

        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("KEY PRESSED: " + e.getKeyChar());
            pacman.updateDirection('R');
        }

        if(pacman.direction == 'U') {
            pacman.image = pacmanUpImage;
        }
        else if(pacman.direction == 'D') {
            pacman.image = pacmanDownImage;
        }
        else if(pacman.direction == 'L') {
            pacman.image = pacmanLeftImage;
        }
        else if(pacman.direction == 'R') {
            pacman.image = pacmanRightImage;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}