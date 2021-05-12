package Frog;

import javax.swing.*;
import java.awt.*;

public class ScoreBoard extends JLabel {

    private int score;
    private boolean isGameOver = false;

    public ScoreBoard(){
        score = 0;

        // game over screen text
        this.setText("Score: 0");
        this.setBounds(175, 25, 300, 50);
        this.setFont(new Font("MV Boli", Font.PLAIN, 26));

        // thread to increment score by 25, every second
        Thread newScore = new Thread(){
            @Override
            public void run(){
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    score += 25;
                    set_text();
                    if (isGameOver){
                        break;
                    }
                }
            }
        };
        newScore.start();
    }

    public void set_text(){
        this.setText("Score: " + score);
    }

    public void setGameOver(){
        isGameOver = true;
    }

    public int getScore(){
        return score;
    }

}
