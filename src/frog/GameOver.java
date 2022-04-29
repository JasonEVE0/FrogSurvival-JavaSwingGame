package src.Frog;

import javax.swing.*;
import java.awt.*;

public class GameOver extends JPanel {

    public GameOver(){
        this.setBackground(Color.BLACK);
        this.setBounds(0,0,517,537);
        this.setVisible(false);
    }

    public void endGame(int score){
        JLabel scoreLabel = new JLabel();
        this.setLayout(null);
        scoreLabel.setBounds(175,50,170,100);
        scoreLabel.setFont(new Font("MV Boli",Font.PLAIN,26));
        scoreLabel.setForeground(Color.RED);
        scoreLabel.setText("<html>Game Over <br/> Score: " + Integer.toString(score));
        this.add(scoreLabel);
        this.setVisible(true);
    }

}
