// Imports
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Game extends JPanel implements ActionListener, KeyListener {

    // KeyListener Components
    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_W) {
            wPressed = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_A) {
            aPressed = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_S) {
            sPressed = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_D) {
            dPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_W) {
            wPressed = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_A) {
            aPressed = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_S) {
            sPressed = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_D) {
            dPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    // Warning Window Name
    JFrame warning = new JFrame("GET BACK NOW");

    // Game Timer
    Timer timer;

    // Warning Timer
    Timer warningTimer;

    // Player Variables
    int playerX = 100;
    int playerY = 100;
    int playerL = 50;
    int playerW = 50;

    // Movement Variables
    boolean wPressed = false;
    boolean aPressed = false;
    boolean sPressed = false;
    boolean dPressed = false;

    // WarningShowing boolean
    boolean warningShowing = false;

    // Game Window Name
    JFrame gameWindow = new JFrame("Game");

    // Constructor
    public Game() {

        // Game Window Setup
        gameWindow.setSize(800, 600);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.add(this);
        gameWindow.setVisible(true);

        // Warning Window Setup
        warning.setSize(400, 500);

        // Warning Timer
        warningTimer = new Timer(1000, e -> {

            warning.setVisible(false);

            warningShowing = false;

            warningTimer.stop();
        });

        // Game Timer
        timer = new Timer(16, this);
        timer.start();

        // Keyboard
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
    }

    // Drawing
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.fillRect(playerX, playerY, playerL, playerW);
    }

    // Game Timer
    @Override
    public void actionPerformed(ActionEvent e) {

        // Movement

        if (wPressed && playerY > 0) {
            playerY -= 5;
        }

        if (aPressed && playerX > 0) {
            playerX -= 5;
        }

        if (sPressed && playerY + playerW < 600) {
            playerY += 5;
        }

        if (dPressed && playerX + playerL < 800) {
            playerX += 5;
        }

        // Boundary Check
        if (playerX + playerL >= 770 ||
            playerX <= 0 ||
            playerY + playerW >= 770 ||
            playerY <= 0) {

            // Warning Show
            if (!warningShowing) {

                warningShowing = true;

                warning.setVisible(true);

                warningTimer.restart();
            }
        }

        // Redraw
        repaint();
    }

    // Main
    public static void main(String[] args) {
        new Game();
    }
}
