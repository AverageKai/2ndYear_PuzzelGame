/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TileManager;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import mygameant.*;


/**
 *
 * @author Miklos Bolarde
 */
public class TileManager {
    GamePanel gp;
    public Tiles[] tile;  
    public int mapTiles[][];
    
    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tiles[100];
        mapTiles = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/Maps/Map"+gp.randomWorld);
    }
    public void getTileImage(){
        File folder = new File("C:/Users/bolar/OneDrive/Documents/NetBeansProjects/MyGameAnt/src/Tiles");
        File[] listOfTiles = folder.listFiles();
        Optimaization op = new Optimaization();
        try{
            for (int i = 0; i < listOfTiles.length; i++) {
                tile[i] = new Tiles();
                tile[i].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/"+listOfTiles[i].getName()));
                tile[i].image = op.scaleImage(tile[i].image, gp.tileSize, gp.tileSize);
            }
            tile[12].collision=true;
            tile[13].collision=true;
            tile[14].collision=true;
            tile[15].collision=true;
            tile[16].collision=true;
            tile[17].collision=true;
            tile[18].collision=true;
            tile[19].collision=true;
            tile[25].collision=true;
            tile[26].collision=true;
            tile[27].collision=true;
            tile[21].collision=true;
            tile[22].collision=true;
            tile[23].collision=true;
            tile[24].collision=true;
            
        }catch(IOException e){
            System.out.println("Error! "+e);
        }
    }
    public void loadMap(String filePath){
        try{
            InputStream is =  getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is ));
            
            int col=0,row=0;
            
            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();
                while(col < gp.maxWorldCol){
                    String numbers[]=line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTiles[col][row] = num;
                    col++;
                }
                if(col==gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch(Exception e){
            
        }
    }
    public void draw(Graphics2D tileGraphics){
        int worldRow=0,worldCol=0;
        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
            int tileNum = mapTiles[worldCol][worldRow];
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            if(worldX+gp.tileSize>gp.player.worldX-gp.player.screenX && worldX-gp.tileSize<gp.player.worldX+gp.player.screenX && worldY+gp.tileSize>gp.player.worldY-gp.player.screenY && worldY-gp.tileSize<gp.player.worldY+gp.player.screenY){
               tileGraphics.drawImage(tile[tileNum].image, screenX, screenY,null); 
            }       
            worldCol++;
            if(worldCol==gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
    }   
}
