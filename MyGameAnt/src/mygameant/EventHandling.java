/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mygameant;

import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author Miklos Bolarde
 */
public class EventHandling {
    GamePanel gp;
    EventHitBox eventHitBox[][];
    public EventHandling(GamePanel gp) {
        this.gp = gp;
        eventHitBox = new EventHitBox[gp.maxWorldCol][gp.maxWorldRow];
        int col = 0,row = 0;
        while(col<gp.maxWorldCol && row<gp.maxWorldRow){
            eventHitBox[col][row] = new EventHitBox();
            eventHitBox[col][row].x = 23;
            eventHitBox[col][row].y = 23;
            eventHitBox[col][row].width = 2;
            eventHitBox[col][row].height = 2;
            eventHitBox[col][row].eventHitBoxX = eventHitBox[col][row].x;
            eventHitBox[col][row].eventHitBoxY = eventHitBox[col][row].y;
            col++;
            if(col==gp.maxWorldCol){
                col=0;
                row++;
            }
        }
    }
    public void checkEvent(){
        switch(gp.randomWorld){
            case 1:
                fallHole();
                teleport(29,44);
                teleport(23,28);
                break;
            case 2:
                fallHole();
                teleport(42,35);
                teleport(39,20);
                break;
            case 3:
                fallHole();
                teleport(45,16);
                teleport(26,4);
                break;
            case 4:
                fallHole();
                teleport(31,19);
                teleport(30,33);
                break;
            case 5:
                fallHole();
                teleport(34,13);
                teleport(47,15);
                break;
                
        }
    }
    public void teleport(int col,int row){
        if(hit(col,row,"any")==true){
                gp.playSoundEffect(4);
                gp.gameState = gp.talkingState;
                gp.ui.text = "Woah! Teleportataion";
                gp.player.worldX = gp.player.defaultWorldX;
                gp.player.worldY = gp.player.defaultWorldY;
                eventHitBox[col][row].eventDone=true;
            }
    }
    public void fallHole(){
        Random rand = new Random();
        int quantity= rand.nextInt(5)+1;
        for(int i = 0 ; i<quantity  ; i++){
            int row = rand.nextInt(50);
            int col = rand.nextInt(50);
            if(hit(col,row,"any")==true){
                    gp.playSoundEffect(9);
                    gp.gameState = gp.talkingState;
                    gp.ui.text = "You fell into a Hole";
                    gp.player.life-=1;
                    eventHitBox[col][row].eventDone=true;
                }
     }
    }
    public boolean hit(int col,int row ,String reqDirection){
        boolean hit = false;
        gp.player.hitBox.x = gp.player.worldX + gp.player.hitBox.x;
        gp.player.hitBox.y = gp.player.worldY + gp.player.hitBox.y;
        eventHitBox[col][row].x = col*gp.tileSize + eventHitBox[col][row].x;
        eventHitBox[col][row].y = row*gp.tileSize + eventHitBox[col][row].y;
        if(gp.player.hitBox.intersects(eventHitBox[col][row]) && eventHitBox[col][row].eventDone==false){
            if(gp.player.direction.contentEquals(reqDirection)|| reqDirection.contentEquals("any")){
                hit=true;
            }
        }
        gp.player.hitBox.x = gp.player.defaultHitboxX;
        gp.player.hitBox.y = gp.player.defaultHitboxY;
        eventHitBox[col][row].x = eventHitBox[col][row].eventHitBoxX;
        eventHitBox[col][row].y = eventHitBox[col][row].eventHitBoxY;
        return hit;
    }
    
}
