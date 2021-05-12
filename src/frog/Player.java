package Frog;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends JPanel implements KeyListener {

    private int x = 200;
    private int y = 200;
    private ImageIcon frog_front;
    private ImageIcon frog_right;
    private ImageIcon frog_down;
    private ImageIcon frog_left;
    private JLabel frog;

    public Player(){
        // player sprite
        frog_front = new ImageIcon("frog_front.png");
        frog_right = new ImageIcon("frog_right.png");
        frog_down = new ImageIcon("frog_down.png");
        frog_left = new ImageIcon("frog_left.png");

        frog = new JLabel(frog_front);
        this.add(frog);
        this.setOpaque(false);
        this.setBounds(200,200,50,50);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // do nothing
    }

    // action listener which allows the user to move the player with the W,A,S,D keys
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W){
            if (y <= 0){
                return;
            }
            frog.setIcon(frog_front);
            y -= 50;
            this.setLocation(x,y);
        } else if (e.getKeyCode() == KeyEvent.VK_A){
            if (x <= 0){
                return;
            }
            frog.setIcon(frog_left);
            x -= 50;
            this.setLocation(x,y);
        } else if (e.getKeyCode() == KeyEvent.VK_S){
            if (y >= 450){
                return;
            }
            frog.setIcon(frog_down);
            y += 50;
            this.setLocation(x,y);
        } else if (e.getKeyCode() == KeyEvent.VK_D){
            if (x >= 450){
                return;
            }
            frog.setIcon(frog_right);
            x += 50;
            this.setLocation(x,y);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // do nothing
    }

    public int get_x(){
        return x;
    }

    public int get_y(){
        return y;
    }

}
