/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mygameant;

import Entity.Entity;
import ObjectManager.ObjectClock;
import ObjectManager.ObjectCoin;
import ObjectManager.ObjectHeart;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import javax.swing.JButton;

/**
 *
 * @author Miklos Bolarde
 */
public class UI {
    GamePanel gp;
    Graphics2D graphic;
    Font arial_40;
    BufferedImage coinImage,fullHeart,halfHeart,noHeart,clockImage;  
    public boolean gameFinished= false,messageOn=false,fullScreenOn=false,insertDataBaseOnce=false;
    double playTime;
    String msg;
    public int msgCount,options = 0,pickOption = 0;
    public String text = "";
    String lastPoint="";
    double lastTime=0;
    public Entity npc;
    private static final DecimalFormat mi = new DecimalFormat("#.##");
    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial",Font.PLAIN,30);
        ObjectCoin coin = new ObjectCoin(gp);
        coinImage = coin.image;
        ObjectHeart heart = new ObjectHeart(gp);
        fullHeart = heart.image;
        ObjectClock clock = new ObjectClock(gp);
        clockImage = clock.image;
        halfHeart = heart.image2;
        noHeart = heart.image3;
    }
    public void showMessage(String message){
        msg = message;
        messageOn=true;
    }
    public void draw(Graphics2D graphic){
        this.graphic = graphic;
        if(gp.gameState==gp.playState){
            if(messageOn==true){
                graphic.setColor(Color.white);
                graphic.setFont(graphic.getFont().deriveFont(30F));
                graphic.drawString(msg,gp.tileSize/2,gp.tileSize*5);   
                msgCount++;
                if(msgCount>120){
                    msgCount=0;
                    messageOn=false;
                }
            }
                graphic.setFont(arial_40);
                graphic.setColor(Color.white);
                graphic.drawImage(coinImage,gp.tileSize*6,gp.tileSize/2,gp.tileSize,gp.tileSize,null);
                graphic.drawString("x "+gp.player.hasCoin,gp.tileSize*7,60);
                playTime+=(double)1/60;
                graphic.drawImage(clockImage,gp.tileSize*10,gp.tileSize/2,gp.tileSize,gp.tileSize,null);
                graphic.drawString(""+mi.format(playTime), gp.tileSize*11, 60);
                
                int x = gp.tileSize/2 , y = gp.tileSize/2 , i =0;
                while(i<gp.player.maxLife/2){
                    graphic.drawImage(noHeart,x,y,null);
                    i++;
                    x+=gp.tileSize;
                }
                x = gp.tileSize/2;
                y = gp.tileSize/2;        
                i = 0;
                
                while(i<gp.player.life){
                    graphic.drawImage(halfHeart,x,y,null);
                    i++;
                    if(i<gp.player.life){
                        graphic.drawImage(fullHeart,x,y,null);
                    }
                    i++;
                    x += gp.tileSize;
                }
            
        }
        if(gp.gameState==gp.finishedState){
                graphic.setColor(new Color(0,0,0,150));
                graphic.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
                int x,y;
                String text;
                graphic.setFont(graphic.getFont().deriveFont(Font.BOLD,110f));
                text = "You Escaped!";
                graphic.setColor(Color.black);
                x = getXforCenteredText(text);
                y = gp.tileSize*4;
                graphic.drawString(text, x, y);
                graphic.setColor(Color.white);
                graphic.drawString(text, x-4, y-4);
                graphic.setFont(graphic.getFont().deriveFont(40f));
                graphic.setFont(arial_40);
                graphic.setColor(Color.white);
                
                text = "Your Time is : "+mi.format(playTime)+"!";
                x = getXforCenteredText(text);
                y += gp.tileSize+10;
                graphic.drawString(text, x, y);
                
                
                text = "Play Again";
                x = getXforCenteredText(text);
                y += gp.tileSize*4;
                graphic.drawString(text, x, y);
                if(pickOption==0){
                 graphic.drawString(">",x-40,y);
                 if(gp.control.pressedEnter==true){
                     options = 0;
                     pickOption = 4;
                   }
                }  

                text = "Quit";
                x = getXforCenteredText(text);
                y += 55;
                graphic.drawString(text, x, y);
                if(pickOption==1){
                 graphic.drawString(">",x-40,y);
                 if(gp.control.pressedEnter==true){
                     options = 0;
                     pickOption = 4;
                   }
                }  


                if(insertDataBaseOnce==false){
                    DataBase connect = new DataBase();
                     try {
                        Connection con = connect.connection();
                        Statement st_user = con.createStatement();
                        ResultSet rs = st_user.executeQuery("SELECT * FROM `user` WHERE username='"+gp.player.player+"'");
                        while(rs.next()){
                            lastPoint = rs.getString("points");
                            lastTime = rs.getDouble("FastestTime");
                        }
                     }catch(Exception e){
                        System.out.println("Error! "+e);
                    }
                     if(playTime<lastTime||lastTime==0){
                         double newTime = Double.parseDouble(mi.format(playTime));
                          try{
                            Connection con = connect.connection();
                            Statement st = con.createStatement();
                            st.executeUpdate("UPDATE user SET FastestTime ='"+newTime+"' WHERE username='"+gp.player.player+"'");
                        }catch(Exception e){
                            System.out.println("Error! "+e);
                        }
                     }
                    try{
                        Connection con = connect.connection();
                        Statement st = con.createStatement();
                        st.executeUpdate("UPDATE user SET points='"+(Integer.parseInt(lastPoint)+((gp.player.hasCoin)*2))+"' WHERE username='"+gp.player.player+"'");
                    }catch(Exception e){
                        System.out.println("Error! "+e);
                    }
                    insertDataBaseOnce=true;
                }
                
            }   
        if(gp.gameState==gp.pauseState){
            String text = "PAUSED";
            int textLen = (int)graphic.getFontMetrics().getStringBounds(text, graphic).getWidth();
            int x= (gp.screenWidth/2)- (textLen/2),y = gp.screenHeight/2;
            graphic.setColor(Color.white);
            graphic.setFont(graphic.getFont().deriveFont(30F));
            graphic.drawString(text,x,y);
        }
        if(gp.gameState==gp.talkingState){
            int x = gp.tileSize*2,
            y = gp.tileSize/2,
            height = gp.tileSize*4,
            width = gp.screenWidth - (gp.tileSize*4);
            drawWindow(x,y,width,height);
            
            x+=gp.tileSize;
            y+=gp.tileSize;
            for(String line : text.split("/n")){
                graphic.drawString(line,x,y);
                y+=40;
            }
        }
        if(gp.gameState==gp.optionState) {
            drawOptionScreen();
        }if(gp.gameState==gp.gameOverState){
            graphic.setColor(new Color(0,0,0,150));
            graphic.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            int x,y;
            String text;
            graphic.setFont(graphic.getFont().deriveFont(Font.BOLD,110f));
            text = "GAME OVER!";
            graphic.setColor(Color.black);
            x = getXforCenteredText(text);
            y = gp.tileSize*4;
            graphic.drawString(text, x, y);
            graphic.setColor(Color.white);
            graphic.drawString(text, x-4, y-4);
            graphic.setFont(graphic.getFont().deriveFont(40f));
            
            text = "Retry";
            x = getXforCenteredText(text);
            y += gp.tileSize*4;
            graphic.drawString(text, x, y);
            if(pickOption==0){
             graphic.drawString(">",x-40,y);
             if(gp.control.pressedEnter==true){
                 options = 0;
                 pickOption = 4;
               }
            }  
            
            text = "Quit";
            x = getXforCenteredText(text);
            y += 55;
            graphic.drawString(text, x, y);
            if(pickOption==1){
             graphic.drawString(">",x-40,y);
             if(gp.control.pressedEnter==true){
                 options = 0;
                 pickOption = 4;
               }
            }  
        }      
    }
    public void drawOptionScreen(){
        graphic.setColor(Color.white);
        graphic.setFont(gp.graphics2.getFont().deriveFont(24F));
        
        int frameX = gp.tileSize*6;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize*8;
        int frameHeight = gp.tileSize*10;
        drawWindow(frameX,frameY,frameWidth,frameHeight);
        switch(options){
            case 0: 
                   options(frameX,frameY);
                break;
            case 1: 
                    notifFullScreen(frameX,frameY);
                break;
            case 2: 
                    controls(frameX,frameY);
                break;
            case 3: 
                    confirmExit(frameX,frameY);
                break;
            case 4: 
                break;
        }
        gp.control.pressedEnter=false;
    }
    public void notifFullScreen(int frameX,int frameY){
         int textY = frameY+gp.tileSize*3,textX = frameX+gp.tileSize;
         text = "The change will take affect \nafter restarting the game";
         for(String line : text.split("\n")){
             graphic.drawString(line, textX, textY);
             textY+=40;
         }
         textY = frameY + gp.tileSize*9;
         graphic.drawString("Back", textX, textY);
         if(pickOption == 0){
             graphic.drawString(">",textX-25,textY);
             if(gp.control.pressedEnter==true){
                 options = 0;
             }
         }
    }
    public void confirmExit(int frameX,int frameY){
        int textX = frameX + gp.tileSize-10;
        int textY = frameY + gp.tileSize*3;
        String option = "Do you want to quit the game?";
        for(String line : option.split("/n")){
             graphic.drawString(line, textX, textY);
             textY+=40;
        }
        
        option = "Yes";
        textX = getXforCenteredText(option);
        textY += gp.tileSize*3;
        graphic.drawString(option, textX, textY);
        if(pickOption==0){
             graphic.drawString(">",textX-25,textY);
             if(gp.control.pressedEnter==true){
                 options = 0;
                 Main play = new Main();
                 play.closeGame();
                 gp.bm.stop();
             }
         }
        
        option = "No";
        textX = getXforCenteredText(option);
        textY += gp.tileSize;
        graphic.drawString(option, textX, textY);
        if(pickOption==1){
             graphic.drawString(">",textX-25,textY);
             if(gp.control.pressedEnter==true){
                 options = 0;
                 pickOption = 4;
             }
         }
    }
    public void controls(int frameX,int frameY){
         int textY,textX;
         String text = "Controls";
         textX = getXforCenteredText(text);
         textY = frameY +gp.tileSize;
         graphic.drawString(text, textX, textY);
         
         textX = frameX+gp.tileSize;
         textY += gp.tileSize*2;
         graphic.drawString("Move", textX, textY);
         
         textY += gp.tileSize;
         graphic.drawString("Interact", textX, textY);
         
         textY += gp.tileSize*2;
         graphic.drawString("Back", textX, textY);
         if(pickOption==0){
             graphic.drawString(">",textX-25,textY);
             if(gp.control.pressedEnter==true){
                 options = 0;
                 pickOption = 3;
             }
         }
         
         textX = frameX + (int)(gp.tileSize*4.5);
         textY = frameY + gp.tileSize*3;
         graphic.drawString("WASD", textX, textY);
         
         textY += gp.tileSize;
         graphic.drawString("Space", textX, textY);
         
    }
    public void options(int frameX,int frameY){
         int textY,textX;
         String text = "Options";
         textX = getXforCenteredText(text);
         textY = frameY +gp.tileSize;
         graphic.drawString(text, textX, textY);
         
         textX = frameX+gp.tileSize;
         textY += gp.tileSize*2;
         graphic.drawString("Full Screen", textX, textY);
         if(pickOption==0){
             graphic.drawString(">",textX-25,textY);
             if(gp.control.pressedEnter==true){
                 if(gp.fullScreenOn==false){
                     gp.fullScreenOn=true;
                 }else if(gp.fullScreenOn==true){
                     gp.fullScreenOn=false;
                 }
                  options = 1;
             }
         }
         
         textY += gp.tileSize;
         graphic.drawString("Music", textX, textY);
         if(pickOption==1){
             graphic.drawString(">",textX-25,textY);
         }
         
          textY += gp.tileSize;
         graphic.drawString("SoundEffect", textX, textY);
         if(pickOption==2){
             graphic.drawString(">",textX-25,textY);
         }
         
          textY += gp.tileSize;
         graphic.drawString("Controls", textX, textY);
         if(pickOption==3){
             graphic.drawString(">",textX-25,textY);
             if(gp.control.pressedEnter==true){
                 options = 2;
                 pickOption = 0;
             }
         }
         
          textY += gp.tileSize;
         graphic.drawString("End Game", textX, textY);
         if(pickOption==4){
             graphic.drawString(">",textX-25,textY);
             if(gp.control.pressedEnter==true){
                 options = 3;
                 pickOption = 0;
             }
         }
         
          textY += gp.tileSize*2;
         graphic.drawString("Back", textX, textY);
         if(pickOption==5){
             graphic.drawString(">",textX-25,textY);
             if(gp.control.pressedEnter==true){
                 gp.gameState = gp.playState;
                 options = 0;
                 pickOption = 0;
             }
         }
         
         textX = frameX + (int)(gp.tileSize*4.5);
         textY = frameY + gp.tileSize*2 + 24;
         graphic.drawRect(textX, textY, 24, 24);
         if (gp.fullScreenOn==true) {
            graphic.fillRect(textX, textY, 24, 24);
        }
         
         textY += gp.tileSize;
         graphic.drawRect(textX, textY, 120, 24);
         int volumeWidth = 24 * gp.bm.volumeScale;
         graphic.fillRect(textX, textY, volumeWidth, 24);
         
         textY += gp.tileSize;
         graphic.drawRect(textX, textY, 120, 24);
         volumeWidth = 24 * gp.se.volumeScale;
         graphic.fillRect(textX, textY, volumeWidth, 24);
         
         
    }
    public void drawWindow(int x, int y, int width, int height){
        Color c = new Color(0,0,0,200);
        graphic.setColor(c);
        graphic.fillRoundRect(x,y,width,height,35,35);
        c = new Color(255,255,255,200);
        graphic.setColor(c);
        graphic.setStroke(new BasicStroke(5));
        graphic.drawRoundRect(x+5,y+5,width-10,height-10,25,25);
    }

    private int getXforCenteredText(String text) {
        int textLen = (int)graphic.getFontMetrics().getStringBounds(text, graphic).getWidth();
        int x= (gp.screenWidth/2)- (textLen/2);
        return x;
    }
}
