/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mygameant;

import java.util.Random;
import javax.swing.JFrame;

/**
 *
 * @author Miklos Bolarde
 */
public class Main {
    public String playerT; 
    public static JFrame window;
     public void pleyGame(){
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle(playerT);
        
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setLocale(null);
        window.setVisible(true);
        
        gamePanel.setupObjects();
        gamePanel.startGameThread();
     }   
    public void setPlayerT(String playerT,int setRandomWorld) {
        GamePanel gamePanel = new GamePanel();
        gamePanel.setPlayerT(playerT);
        gamePanel.setRandomWorld(setRandomWorld);
    }
    public void closeGame(){
         UserUI userui = new UserUI();
         GamePanel gp = new GamePanel();
         userui.setVisible(true);
         userui.pack();
         userui.playerName.setText(gp.player.player);
         userui.setLocationRelativeTo(window);
         window.dispose();
    }
    
}
