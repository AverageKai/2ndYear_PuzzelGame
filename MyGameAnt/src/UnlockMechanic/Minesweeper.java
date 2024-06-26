/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UnlockMechanic;

/**
 *
 * @author ethan
 */
import Entity.Player;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import mygameant.GamePanel;
public class Minesweeper {
    
    private class MineTile extends JButton{
        int r;
        int c;
        
        public MineTile(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    
    int tileSize = 70;
    int numRows = 9;
    int numCols = 9;
    int boardWidth = numCols * tileSize;
    int boardHeight = numRows *tileSize;
    GamePanel gp;
    
    JButton reset = new JButton("Give Up");
    
    JFrame frame = new JFrame("Minesweeper");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    
    int mineCtr = 5;
    MineTile[][] board = new MineTile[numRows][numCols];
    ArrayList<MineTile> mineList;
    
    Random random = new Random();
    int tilesClicked = 0;
    boolean gameOver = false;
    public void pleyMinesweeper( GamePanel gp){
        this.gp = gp;
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants. DO_NOTHING_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        textLabel.setFont(new Font("Arial", Font.BOLD, 25));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Minesweeper: " +Integer.toString(mineCtr));
        textLabel.setOpaque(true);
        
        reset.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                resetGame();
            }
        });
        
        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);
        textPanel.add(reset, BorderLayout.EAST);
        
        
        boardPanel.setLayout(new GridLayout(numRows, numCols));
       
        frame.add(boardPanel);
        
        for(int r = 0; r < numRows; r++){
            for(int c = 0; c < numCols; c++){
                MineTile tile = new MineTile(r, c);
                board[r][c] = tile;
                
                tile.setFocusable(false);
                tile.setMargin(new Insets (0,0,0,0));
                tile.setFont(new Font("Arial Unicode MS", Font.PLAIN, 45));
                
                
                tile.addMouseListener(new MouseAdapter(){
                @Override
                public void mousePressed(MouseEvent e){
                    if(gameOver){
                        return;
                    }
                    
       
                    MineTile tile = (MineTile) e.getSource();
                    
                    //left click
                    if(e.getButton() == MouseEvent.BUTTON1){
                        if(tile.getText() == ""){
                            if(mineList.contains(tile)){
                                revealMines();
                            }
                            else{
                                checkMine(tile.r, tile.c);
                            }
                        }
                    }
                    //right click
                    else if(e.getButton() == MouseEvent.BUTTON3){
                        if(tile.getText() == "" && tile.isEnabled()){
                            tile.setText("🚩");
                            updateMineCtr();
                        }
                        else if(tile.getText() == "🚩"){
                            tile.setText("");
                            updateMineCtr();
                        }
                    }
                    
                }
            });
                
                
                boardPanel.add(tile);
            }
        }
         frame.setVisible(true);
         
         setMines();
    }
    
    void setMines(){
        mineList = new ArrayList<MineTile>();

        int mineLeft = mineCtr;
        
        while(mineLeft > 0){
            int r = random.nextInt(numRows); //0-7 in array counting
            int c = random.nextInt(numCols);
            
            MineTile tile = board[r][c];
            
            if(!mineList.contains(tile)){
                mineList.add(tile);
                mineLeft -= 1;
            }
        }
 
    }
    
    void revealMines(){
        for(int i = 0; i < mineList.size();i++){
            MineTile tile = mineList.get(i);
            tile.setText("💣");
        }
        
        gameOver = true;
        frame.dispose();
        gp.gameState = gp.talkingState;
        gp.ui.text="You Failed to Unlock the Chest";
    }
    
    void checkMine(int r, int c){
        try{
            if(r < 0 || r > numRows || c < 0 || c >= numCols){
                return ;
            }

            MineTile tile = board[r][c];
            if(!tile.isEnabled()){
                return;
            }

            tile.setEnabled(false);
            tilesClicked += 1;
            int minesFound = 0;
            //top 3
            minesFound += countMine(r -1, c-1);//top left
            minesFound += countMine(r -1, c );//top
            minesFound += countMine(r- 1, c +1);//top right

            //left and right
            minesFound += countMine(r, c-1); //left
            minesFound += countMine(r, c+1);//right

            //bottom 3
            minesFound += countMine(r +1, c-1);//bottom left
            minesFound += countMine(r +1, c);//bottom
            minesFound += countMine(r +1, c+1);//bottom right

            if(minesFound > 0){
                tile.setText(Integer.toString(minesFound));
            }
            else{
                tile.setText("");

            //top 3
            checkMine(r -1, c-1);//top left
            checkMine(r -1, c );//top
            checkMine(r -1, c +1);//top right

            //left and right
            checkMine(r, c-1); //left
            checkMine(r, c+1);//right

                    //bottom 3
            checkMine(r +1, c-1);//bottom left
            checkMine(r +1, c);//bottom
            checkMine(r +1, c+1);//bottom right
            }

            if(tilesClicked == numRows * numCols - mineList.size()){
                gameOver = true;
                frame.dispose();
                gp.player.playOnce=true;
                gp.player.selectRandomReward();
            }
        }catch(Exception e){
            
        }
    }
    
    
    int countMine(int r, int c){
        if(r < 0 || r > numRows || c < 0 || c >= numCols){
            return 0;
        }
        if(mineList.contains(board[r][c])){
            return 1;
        }
        return 0;
    }
    
    void resetGame(){
        frame.dispose();
        gp.gameState = gp.talkingState;
        gp.ui.text="You Failed to Unlock the Chest";
    }
    
    void updateMineCtr(){
        int flagsPlaced = 0;    
        for(MineTile[] row : board){
            for(MineTile tile : row){
                if(tile.getText() == "🚩" ){
                    flagsPlaced++;
                }
            }
        }
        int flagsRemaining = mineCtr -flagsPlaced;
        textLabel.setText("Minesweeper: " +flagsRemaining);
    }
}
