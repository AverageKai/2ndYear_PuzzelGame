/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import ObjectManager.ObjectChest;
import UnlockMechanic.Minesweeper;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import mygameant.*;

/**
 *
 * @author Miklos Bolarde
 */
public class Player extends Entity{
    public KeyHandling control;
    public String player,playerType;
    public final int screenX,screenY;
    public int hasCoin = 0,monolith = 0;
    public boolean hasRed,hasBlue,hasYellow,hasOrange,playOnce=false,lightUpdated=false;
    public Player(GamePanel gp, KeyHandling control,String player){
        super(gp);
        this.control = control;
        this.player = player;
        screenX = (gp.screenWidth/2) - (gp.tileSize/2);
        screenY = (gp.screenHeight/2) - (gp.tileSize/2);
        hitBox = new Rectangle();
        hitBox.x = 16;
        hitBox.y = 16;
        defaultHitboxX = hitBox.x;
        defaultHitboxY = hitBox.y;
        hitBox.width = 16;
        hitBox.height = 16;
        setDefaulValues();
        getPlayerImage();
    }
    public void setDefaulValues(){
        switch(gp.randomWorld){
            case 1:
                worldX = 23*48;
                worldY = 24*48;
                defaultWorldX = worldX;
                defaultWorldY = worldY;
                break;
            case 2:
                worldX = 9*48;
                worldY = 6*48;
                defaultWorldX = worldX;
                defaultWorldY = worldY;
                break;
            case 3:
                worldX = 43*48;
                worldY = 22*48;
                defaultWorldX = worldX;
                defaultWorldY = worldY;
                break;
            case 4:
                worldX = 26*48;
                worldY = 25*48;
                defaultWorldX = worldX;
                defaultWorldY = worldY;
                break;
            case 5:
                worldX = 25*48;
                worldY = 5*48;
                defaultWorldX = worldX;
                defaultWorldY = worldY;
                break;
        }
        speed = 4;
        direction = "down";
        maxLife = 6;
        life = maxLife;
    }
    public void getPlayerImage(){
        DataBase connect = new DataBase();
        try{
            Connection con = connect.connection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `user` WHERE username='"+player+"'");
            while(rs.next()){
                    playerType = rs.getString("playerType");
                }
        }catch(Exception e){
            System.out.println("Error! "+e);
        }
        up1 = setup("cit_up1");
        up2 = setup("cit_up2");
        down1 = setup("cit_down1");
        down2 = setup("cit_down2");
        left1 = setup("cit_left1");
        left2 = setup("cit_left2");
        right1 = setup("cit_right1");
        right2 = setup("cit_right2");
    }
    public BufferedImage setup(String imagePath){
        Optimaization op = new Optimaization();
        BufferedImage image = null;
        try{
            image = ImageIO.read(getClass().getResource("/"+playerType+"/"+imagePath+".png"));
            image = op.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            System.out.println("Error! : "+e);
        }
        return image;
    }
    public void update(){
        if(control.pressedUp==true||control.pressedDown==true||control.pressedLeft==true||control.pressedRight==true)  {
            if(control.pressedUp==true){
            direction = "up";
        }else if(control.pressedDown==true){
            direction = "down";
        }else if(control.pressedLeft==true){
            direction = "left";
        }else if(control.pressedRight==true){
            direction = "right";
        }
            
        collisionOn = false;
        gp.cc.checkTile(this);
        
        int objIndex = gp.cc.checkObject(this, true);
        interactWithObj(objIndex);
        
        int npcIndex =  gp.cc.checkEntity(this, gp.npc);
        interactNPC(npcIndex);
        
        gp.event.checkEvent();
        
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
            if(life<=0){
                gp.gameState = gp.gameOverState;
            }
        }  
    }
    
    public void interactNPC(int i ){
        if(i != 999){
            if(gp.control.pressedSpace==true){
                gp.gameState = gp.talkingState;
                gp.npc[i].speak();
            }
        }
        gp.control.pressedSpace=false;
    }
    public void interactWithObj(int i){
        Minesweeper ms = new Minesweeper();
        if(i != 999){
            String objName = gp.obj[i].name;
            switch(objName){
                case "Coin":
                    gp.obj[0].down1=null;
                    gp.obj[0] = null;
                    hasCoin++;
                    gp.playSoundEffect(7);
                    break;
                case "Coin1":
                    gp.obj[12].down1=null;
                    gp.obj[0] = null;
                    hasCoin++;
                    gp.playSoundEffect(7);
                    break;
                case "Chest":
                    if(playOnce==false){
                        if(gp.control.pressedSpace==true){
                            ms.pleyMinesweeper(gp);
                        }
                    }
                        //gp.ui.showMessage("You got a chest!");
                    break;
                case "Red Crystal":
                    if(hasRed==false&&hasBlue==false&&hasYellow==false&&hasOrange==false){
                        gp.obj[4].down1=null;
                        gp.playSoundEffect(4);
                        hasRed=true;
                    }
                    break;
                case "Monolith Red":
                    if(hasRed==true){
                        gp.playSoundEffect(6);
                        monolith++;
                        System.out.println("monolith: "+monolith);
                        changeDoor(monolith,3,"Mono_red_on");
                        hasRed=false;
                    }
                    break;
                case "Blue Crystal":
                    if(hasRed==false&&hasBlue==false&&hasYellow==false&&hasOrange==false){
                        gp.obj[6].down1=null;
                        gp.playSoundEffect(4);
                        hasBlue=true;
                    }
                    break;
                case "Monolith Blue":
                    if(hasBlue==true){
                        gp.playSoundEffect(6);
                        monolith++;
                        System.out.println("monolith: "+monolith);
                        changeDoor(monolith,5,"Mono_blue_on");
                        hasBlue=false;
                    }
                    break;
                case "Yellow Crystal":
                    if(hasRed==false&&hasBlue==false&&hasYellow==false&&hasOrange==false){
                        gp.obj[8].down1=null;
                        gp.playSoundEffect(4);
                        hasYellow=true;
                    }
                    break;
                case "Monolith Yellow":
                    if(hasYellow==true){
                        gp.playSoundEffect(6);
                        monolith++;
                        System.out.println("monolith: "+monolith);
                        changeDoor(monolith,7,"Mono_yellow_on");
                        hasYellow=false;
                    }
                    break;
                case "Orange Crystal":
                    if(hasRed==false&&hasBlue==false&&hasYellow==false&&hasOrange==false){
                        gp.obj[10].down1=null;
                        gp.playSoundEffect(4);
                        hasOrange=true;
                    }
                    break;
                case "Monolith Orange":
                    if(hasOrange==true){
                        gp.playSoundEffect(6);
                        monolith++;
                        System.out.println("monolith: "+monolith);
                        changeDoor(monolith,9,"Mono_orange_on");
                        hasOrange=false;
                    }
                    break;
                case "Door":
                    if(monolith==4){
                        gp.gameState = gp.finishedState;
                        gp.stopBackgroundMusic();
                        gp.playSoundEffect(2);
                    }
            }
        }
    }
    public void draw(Graphics2D graphic){
        //graphic.setColor(Color.white);
        //graphic.fillRect(x,y,gp.tileSize,gp.tileSize);
        BufferedImage image = null;
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
            graphic.drawImage(image, screenX, screenY, null);
        }
    public void changeDoor(int monolith,int i,String image){
        try {
                gp.obj[i].down1 = ImageIO.read(getClass().getResourceAsStream("/Objects/"+image+".png"));
                switch(monolith){
                    case 1:
                        gp.obj[11].down1 = ImageIO.read(getClass().getResourceAsStream("/Objects/door1.png"));
                        break;
                    case 2:
                        gp.obj[11].down1 = ImageIO.read(getClass().getResourceAsStream("/Objects/door2.png"));
                        break;
                    case 3:
                        gp.obj[11].down1 = ImageIO.read(getClass().getResourceAsStream("/Objects/door3.png"));
                        break;
                    case 4:
                        gp.obj[11].down1 = ImageIO.read(getClass().getResourceAsStream("/Objects/door4.png"));
                        break;
            }
        } catch (IOException ex) {
            System.out.println("Error: "+ex);
        }
    }
    public void selectRandomReward(){
        Random rand = new Random();
        int random = rand.nextInt(2)+1;
        switch(random){
            case 1:
                gp.gameState = gp.talkingState;
                gp.ui.text="Unlocked chest, You found some Boots";
                gp.playSoundEffect(7);
                speed+=4;
                break;
            case 2:
                gp.gameState = gp.talkingState;
                gp.ui.text="Unlocked chest, You found a Medkit";
                life+=2;
                gp.playSoundEffect(7);
                break;
            case 3:
                gp.gameState = gp.talkingState;
                gp.ui.text="Unlocked chest, You found a FlashLight";
                gp.playSoundEffect(7);
                lightUpdated=true;
                changeLight=true;
                break;
        }
    }
}
