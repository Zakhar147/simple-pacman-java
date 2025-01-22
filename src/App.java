import javax.swing.JFrame;

public class App {
    public static void main(String[] args) {
        int rowCount = 21;
        int colCount = 19;
        int tailSize = 32;
        int boardWidth = colCount * tailSize;
        int boardHeight = rowCount * tailSize;

        JFrame frame = new JFrame("Pac Man");
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        PacMan pacmanGame = new PacMan();
        frame.add(pacmanGame);
        frame.pack();
        pacmanGame.requestFocus();
        frame.setVisible(true);
    }
}