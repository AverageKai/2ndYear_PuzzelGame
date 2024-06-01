/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mygameant;

import Entity.*;
import Environment.EnvironmentManager;
import TileManager.TileManager;
import java.awt.Color;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author Miklos Bolarde
 */
public class GamePanel extends JPanel implements Runnable{
    //SCREEN
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize*scale;
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    public final int screenWidth = maxScreenCol*tileSize;//768
    public final int screenHeight = maxScreenRow*tileSize;//57
    public static String playerT="mik";
    public boolean fullScreenOn = false;
    
    //WORLD
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D graphics2;
    
    int FPS=60;
    public static int randomWorld;
    
    TileManager tileM = new TileManager(this);
    public KeyHandling control = new KeyHandling(this);
    Sounds bm = new Sounds();
    Sounds se = new Sounds();
    public CheckCollision cc = new CheckCollision(this);
    public PlaceObject po = new PlaceObject(this);
    public UI ui = new UI(this);
    public EventHandling event = new EventHandling(this);
    public EnvironmentManager em = new EnvironmentManager(this);
    
    public Entity npc[] = new Entity[10];
    public Player player = new Player(this,control,playerT); 
    public Entity obj[]= new Entity[15];
    ArrayList<Entity> entityList = new ArrayList<>();
    Thread gameThread;
    
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int talkingState = 3;
    public final int optionState = 4;
    public final int gameOverState = 5;
    public final int finishedState = 6;
    
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(control);
        this.setFocusable(true);  
    }
    
    public void setupObjects(){
        po.setObject();
        po.setNPC();
        playBackgroundMusic(1);
        em.setUp();
        gameState = playState;
        tempScreen = new BufferedImage(screenWidth,screenHeight,BufferedImage.TYPE_INT_ARGB);
        graphics2 = (Graphics2D)tempScreen.getGraphics();
        if(fullScreenOn==true){
            fullScreen();
        }
    }
    
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    @Override
    public void run() {
        double interval = 1000000000/FPS;
        double nextUpdate = System.nanoTime() + interval;
        while(gameThread!=null){
            update();
            drawTempScreen();
            drawScreen();
            try{
                double time = nextUpdate - System.nanoTime();
                time/=1000000;
                if(time<0){
                    time=0;
                }
                Thread.sleep((long)time);
                nextUpdate+=interval;
            }catch(Exception e){
                System.out.println("Error! "+e.getMessage());
            }
        }
    }
    
    public void update(){
        if(gameState==playState){
            player.update();
            for(int i = 0; i<npc.length; i++){
            if(npc[i] != null){
                npc[i].update();
            }
            em.update();
        }
        }else if(gameState == pauseState){
            
        }
    }
    public void drawTempScreen(){
        tileM.draw(graphics2);
        entityList.add(player);
        for (int i = 0; i < npc.length; i++) {
            if(npc[i]!=null){
                entityList.add(npc[i]);
            }
        }
        for (int i = 0; i < obj.length; i++) {
            if(obj[i]!=null){
                entityList.add(obj[i]);
            }
        }
        Collections.sort(entityList, new Comparator<Entity>() {
            @Override
            public int compare(Entity e1, Entity e2) {
                int result = Integer.compare(e1.worldY, e2.worldY);
                return result;
            }
        });
        for (int i = 0; i < entityList.size(); i++) {
            entityList.get(i).draw(graphics2);
        }
        for (int i = 0; i < entityList.size(); i++) {
            entityList.remove(i);
        }
        em.draw(graphics2);
        ui.draw(graphics2);
    }
    public void reset(){
        po.setObject();
        po.setNPC();
        player.setDefaulValues();
        playBackgroundMusic(1);
        player.monolith = 0;
        player.hasCoin = 0;
        ui.playTime=0;
        ui.insertDataBaseOnce = false;
        for (int i = 0; i < event.eventHitBox.length; i++) {
            for (int j = 0; j < event.eventHitBox[i].length; j++) {
                event.eventHitBox[i][j].eventDone=false;
            }
        }
    }
    public void fullScreen(){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Main.window);
        screenWidth2 = Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();
    }
    public void drawScreen(){
        try{
            Graphics g = getGraphics();
            g.drawImage(tempScreen, 0, 0, screenWidth2,screenHeight2,null);
            g.dispose();
        }catch(Exception e){
            
        }
    }
    public void setPlayerT(String playerType) {
        this.playerT = playerType;
    }

    public void setRandomWorld(int randomWorld) {
        this.randomWorld = randomWorld;
    }
    public void playBackgroundMusic(int i){
        bm.setFile(i);
        bm.play();
        bm.loop();
    }
    public void stopBackgroundMusic(){
        bm.stop();
    }
    public void playSoundEffect(int i){
        se.setFile(i);
        se.play();
    }
}
