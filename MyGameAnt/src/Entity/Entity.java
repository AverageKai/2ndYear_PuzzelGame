/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import mygameant.GamePanel;
import mygameant.Optimaization;

/**
 *
 * @author Miklos Bolarde
 */
public class Entity {
    GamePanel gp;
    public int worldX,worldY,speed,defaultWorldX,defaultWorldY;
    public BufferedImage up1, up2, left1, left2, right1, right2, down1, down2;
    public String direction = "down";
    public int spriteCounter = 0, spriteNum = 1;
    public Rectangle hitBox = new Rectangle(0,0,48,48);
    public int defaultHitboxX,defaultHitboxY;
    public boolean collisionOn=false;
    public int entityTime = 0;
    public String text[] = new String[20];
    public int textIndex = 0;
    public BufferedImage image,image2,image3;
    public String name;
    public boolean collision = false;
    public boolean changeLight = false;
     public boolean lightUpdated = false;
    
    public int maxLife;
    public int life;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }
    public void setAction(){
    }
    public void speak(){
        if(text[textIndex]==null){
            textIndex = 0;
        }
            gp.ui.text =  text[textIndex];
            textIndex++;
        switch(gp.player.direction){
            case"up":
                direction="down";
                break;
            case"down":
                direction="up";
                break;
            case"left":
                direction="right";
                break;
            case"right":
                direction="left";
                break;
        }
    }
    public void update(){
        setAction();
        collisionOn = false;
        gp.cc.checkTile(this);
        gp.cc.checkObject(this, false);
        gp.cc.checkPlayer(this);
        
        if(collisionOn==false){
            switch(direction){
                case "up":
                    worldY-=speed;
                    break;
                case "down":
                    worldY+=speed;
                    break;
                case "left":
                    worldX-=speed;
                    break;
                case "right":
                    worldX+=speed;
                    break;
                    
            }
        }
        
        spriteCounter++;
            if(spriteCounter>12){
                if(spriteNum==1){
                    spriteNum=2;
                }else if(spriteNum==2){
                    spriteNum=1;
                }
                spriteCounter=0;
            }
    }
    public void draw(Graphics2D graphic){
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
            if(worldX+gp.tileSize>gp.player.worldX-gp.player.screenX && worldX-gp.tileSize<gp.player.worldX+gp.player.screenX && worldY+gp.tileSize>gp.player.worldY-gp.player.screenY && worldY-gp.tileSize<gp.player.worldY+gp.player.screenY){
                
                switch(direction){
                case "up":
                    if(spriteNum==1){
                        image = up1;
                    }
                    if(spriteNum==2){
                        image = up2;
                    }
                    break;
                case "down":
                    if(spriteNum==1){
                        image = down1;
                    }
                    if(spriteNum==2){
                        image = down2;
                    }
                    break;
                case "left":
                    if(spriteNum==1){
                        image = left1;
                    }
                    if(spriteNum==2){
                        image = left2;
                    }
                    break;
                case "right":
                    if(spriteNum==1){
                        image = right1;
                    }
                    if(spriteNum==2){
                        image = right2;
                    }
                    break;
              }
                graphic.drawImage(image, screenX, screenY, gp.tileSize,gp.tileSize,null); 
            }       
    }
    public BufferedImage setup(String imagePath){
        Optimaization op = new Optimaization();
        BufferedImage image = null;
         try{
             image = ImageIO.read(getClass().getResourceAsStream(imagePath+".png"));
             image = op.scaleImage(image, gp.tileSize, gp.tileSize);
         }catch(IOException e){
             System.out.println("Error! "+e);
         }
         return image;
    }
}
