/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package headgame;

import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class GameScreen extends JFrame {
    
    public static int score = 0;
    
    public GameScreen(String title) throws HeadlessException{
    super(title);
}
    
    public static void main(String[] args) {
        GameScreen gamesc = new GameScreen("Head Game");
        
        gamesc.setSize(400, 700);
        gamesc.setResizable(false);
        gamesc.setFocusable(false);
        
        gamesc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        HeadGame headgame = new HeadGame();
        
        JLabel scoreBoard = new JLabel();
        headgame.add(scoreBoard);
        scoreBoard.setVisible(true);
        scoreBoard.setSize(200, 300);
        
        headgame.requestFocus();
        headgame.addKeyListener(headgame);
        
        headgame.setFocusable(true);
        headgame.setFocusTraversalKeysEnabled(false);
        
        gamesc.add(headgame);
        gamesc.setVisible(true);
        
        while(true){
            
            scoreBoard.setText("Score: " + getScore());
        }
        
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        GameScreen.score = score;
    }
    
}
