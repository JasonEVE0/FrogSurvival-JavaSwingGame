package src.Frog;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Window extends JFrame {

    private Player player;
    private List<Enemy> enemies = new ArrayList<Enemy>();
    private int numberOfEnemies = 0;
    private JLabel background;
    private boolean isGameOver = false;

    public Window(){

        // player
        player = new Player();

        // scoreboard
        ScoreBoard score = new ScoreBoard();

        // frame
        this.setSize(517,537);
        this.setLayout(null);
        this.setTitle("Frog Survival");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        // game over screen
        GameOver gameOver = new GameOver();

        // background
        background = new JLabel();
        background.setBounds(0,0,517,537);
        background.setIcon(new ImageIcon("images/background.png"));

        // adding to frame
        this.addKeyListener(player);
        this.add(gameOver);
        this.add(player);
        this.add(score);
        this.add(background);

        // setting visibility
        this.setVisible(true);

        // enemy collision thread
        Thread enemyCollision = new Thread(){
            public void run(){
                while(true) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < enemies.size(); i++) {
                        if (player.get_x() == enemies.get(i).get_x() && player.get_y() == enemies.get(i).get_y()) {
                            gameOver.endGame(score.getScore());
                            score.setGameOver();
                            for (int j = 0; j < enemies.size(); j++){
                                enemies.get(i).setGameOver();
                            }
                            isGameOver = true;
                        }
                    }
                    if (isGameOver){
                        break;
                    }
                }
            }
        };
        enemyCollision.start();

        // creating new enemies thread
        Thread newEnemy = new Thread(){
            @Override
            public void run(){
                while (true) {
                    createEnemy();
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (isGameOver){
                        break;
                    }
                }
            }
        };
        newEnemy.start();
    }

    // adding a new enemy object to the enemies arraylist and then adding that enemy to the frame
    public void createEnemy(){
        enemies.add(new Enemy());
        this.add(enemies.get(numberOfEnemies));
        this.add(background);
        numberOfEnemies++;
    }

}
