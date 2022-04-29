package src.Frog;

import javax.swing.*;
import java.util.Random;

public class Enemy extends JPanel {

    private int x;
    private int y;
    private Random random;
    private boolean isGameOver = false;
    private JLabel lizard;
    private ImageIcon lizard_front;
    private ImageIcon lizard_right;
    private ImageIcon lizard_down;
    private ImageIcon lizard_left;

    public Enemy() {
        random = new Random();

        // the enemy sprite
        lizard_front = new ImageIcon("images/lizard_front.png");
        lizard_right = new ImageIcon("images/lizard_right.png");
        lizard_down = new ImageIcon("images/lizard_down.png");
        lizard_left = new ImageIcon("images/lizard_left.png");

        lizard = new JLabel();
        lizard.setIcon(lizard_front);
        this.add(lizard);
        this.setOpaque(false);

        startingPosition();

        // thread to make the enemy move around the screen
        Thread enemyThread1 = new Thread(){
            @Override
            public void run(){
                while (true) {
                    attack();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    if (isGameOver){
                        break;
                    }
                }
            }
        };
        enemyThread1.start();

    }

    public int get_x(){
        return x;
    }

    public int get_y(){
        return y;
    }

    // moves the enemy around the screen while making sure the enemy doesn't go outside the frame
    private void attack(){
        int rng = random.nextInt(4) + 1; // gives a random number between 1 and 4
        if (rng == 1) {
            if (y <= 0) {
                return;
            }
            lizard.setIcon(lizard_front);
            y -= 50;
        } else if (rng == 2) {
            if (x >= 450) {
                return;
            }
            lizard.setIcon(lizard_right);
            x += 50;
        } else if (rng == 3) {
            if (y >= 450) {
                return;
            }
            lizard.setIcon(lizard_down);
            y += 50;
        } else if (rng == 4) {
            if (x <= 0) {
                return;
            }
            lizard.setIcon(lizard_left);
            x -= 50;
        }
        this.setLocation(x,y);
    }

    // method which randomises the starting position of the enemy
    public void startingPosition(){
        int position = random.nextInt(10) * 50;
        int startingSide = random.nextInt(4) + 1;

        // North side
        if (startingSide == 1) {
            this.setBounds(position, 0, 50, 50);
            x = position;
            y = 0;
            // East side
        } else if (startingSide == 2) {
            this.setBounds(450, position, 50, 50);
            x = 450;
            y = position;
            // South side
        } else if (startingSide == 3) {
            this.setBounds(position, 450, 50, 50);
            x = position;
            y = 450;
            // West side
        } else if (startingSide == 4) {
            this.setBounds(0, position, 50, 50);
            x = 0;
            y = position;
        } else {
            System.out.println("error");
        }
    }

    public void setGameOver(){
        isGameOver = true;
    }
}
