
package headgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

class Blocks{
    private int blockX;
    private int blockY;
    
    public Blocks(int blockX, int blockY){
        this.blockX = blockX;
        this.blockY = blockY;
    }

    public int getBlockX() {
        return blockX;
    }

    public void setBlockX(int blockX) {
        this.blockX = blockX;
    }

    public int getBlockY() {
        return blockY;
    }

    public void setBlockY(int blockY) {
        this.blockY = blockY;
    }

}

public class HeadGame extends JPanel implements KeyListener, ActionListener{
    
    Timer timer1 = new Timer(1000/60, this);
    
    private int headX = 190;
    private int headY = 610;
    private int headWidth;
    private int headHeight;
    private int blockCreate = 0;
    private int downSpeed = 4;
    private BufferedImage headImg;
    Random rnd = new Random();
    
    private ArrayList<Blocks> blocks = new ArrayList<Blocks>();
    
    
    public boolean Check(){
        
        for(Blocks block: blocks){
            if(new Rectangle(headX, headY, headImg.getWidth()/2 - 3, headImg.getHeight()/2 - 15).intersects(block.getBlockX(), block.getBlockY(), 40, 70)){
                return true;
            }
        }
        return false;
    }
    
    public HeadGame(){
        
        try {
            headImg = ImageIO.read(new FileImageInputStream(new File("me.png")));
        } catch (IOException ex) {
            Logger.getLogger(HeadGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        setBackground(Color.pink);
        timer1.start();
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        
        for(Blocks block: blocks){
            if(block.getBlockY() >= 700){
                blocks.remove(block);
            }
        }
        g.setColor(Color.red);
        g.drawImage(headImg, headX, headY, headImg.getWidth()/2, headImg.getHeight()/2, this);
        g.setColor(Color.black);
        for(Blocks block: blocks){
            g.fillRect(block.getBlockX(), block.getBlockY(), 40, 70);
        }
        
        if(Check()){
           timer1.stop();
            String message ="Game Over! Your Score is: " + GameScreen.getScore();
            
            JOptionPane.showMessageDialog(this, message);
            System.exit(0);
        }
        
    }
    
    @Override
    public void repaint() {
        super.repaint(); 
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        
         if(k == KeyEvent.VK_LEFT){
             if(headX > 0){
                 while(true){
                     headX -= 3;
                  repaint();
                 }
                  
             }else if(headX <= 0 ){
                 headX = 0;
                 repaint();
             }
             
                     
        }else if(k == KeyEvent.VK_RIGHT){
            if(headX < 400){
                  headX += 12;
             repaint();
             }else if(headX >= 400){
                 headX = 380;
                 repaint();
             }
               
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        blockCreate++;
        GameScreen.setScore(blockCreate);
        if(blockCreate % 22 == 0){
            blocks.add(new Blocks(rnd.nextInt(350), 0));
            
        }
        if(blockCreate % 500 == 0){
            downSpeed++;
        }
        
        for(Blocks block: blocks){
            block.setBlockY(block.getBlockY() + downSpeed);
        }
       
        repaint();
    }

    
    
}
