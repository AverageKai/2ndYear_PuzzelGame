/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mygameant;

import Entity.Entity;

/**
 *
 * @author Miklos Bolarde
 */
public class CheckCollision {
    GamePanel gp;
   public CheckCollision(GamePanel gp){
       this.gp=gp;  
   }
   public void checkTile(Entity entity){
       int entityCheckLeftX = entity.worldX+entity.hitBox.x;
       int entityCheckRightX = entity.worldX+entity.hitBox.x+entity.hitBox.width;
       int entityCheckTopY = entity.worldY+entity.hitBox.y;
       int entityCheckBottomY = entity.worldY+entity.hitBox.y+entity.hitBox.height;
       
       int entityLeftCol = entityCheckLeftX/gp.tileSize;
       int entityRightcol = entityCheckRightX/gp.tileSize;
       int entityTopRow = entityCheckTopY/gp.tileSize;
       int entityBottomRow = entityCheckBottomY/gp.tileSize;
       int tileNum1,tileNum2;
       
       switch(entity.direction){
           case "up":
               entityTopRow = (entityCheckTopY - entity.speed)/gp.tileSize;
               tileNum1 = gp.tileM.mapTiles[entityLeftCol][entityTopRow];
               tileNum2 = gp.tileM.mapTiles[entityRightcol][entityTopRow];
               if(gp.tileM.tile[tileNum1].collision == true||gp.tileM.tile[tileNum2].collision == true){
                   entity.collisionOn = true;
               }
               break;
           case "down":
               entityBottomRow = (entityCheckBottomY + entity.speed)/gp.tileSize;
               tileNum1 = gp.tileM.mapTiles[entityLeftCol][entityBottomRow];
               tileNum2 = gp.tileM.mapTiles[entityRightcol][entityBottomRow];
               if(gp.tileM.tile[tileNum1].collision == true||gp.tileM.tile[tileNum2].collision == true){
                   entity.collisionOn = true;
               }
               break;
           case "left":
               entityLeftCol = (entityCheckLeftX - entity.speed)/gp.tileSize;
               tileNum1 = gp.tileM.mapTiles[entityLeftCol][entityTopRow];
               tileNum2 = gp.tileM.mapTiles[entityLeftCol][entityBottomRow];
               if(gp.tileM.tile[tileNum1].collision == true||gp.tileM.tile[tileNum2].collision == true){
                   entity.collisionOn = true;
               }
               break;
           case "right":
               entityRightcol = (entityCheckRightX + entity.speed)/gp.tileSize;
               tileNum1 = gp.tileM.mapTiles[entityRightcol][entityTopRow];
               tileNum2 = gp.tileM.mapTiles[entityRightcol][entityBottomRow];
               if(gp.tileM.tile[tileNum1].collision == true||gp.tileM.tile[tileNum2].collision == true){
                   entity.collisionOn = true;
               }
               break;
       }
   }
    public int checkObject(Entity entity, boolean player){
        int index = 999;
        for(int i = 0 ; i<gp.obj.length ; i++){
            if(gp.obj[i]!=null){
                entity.hitBox.x = entity.worldX + entity.hitBox.x;
                entity.hitBox.y = entity.worldY + entity.hitBox.y;
                gp.obj[i].hitBox.x = gp.obj[i].worldX + gp.obj[i].hitBox.x;
                gp.obj[i].hitBox.y = gp.obj[i].worldY + gp.obj[i].hitBox.y;
                switch(entity.direction){
                    case"up":
                        entity.hitBox.y -= entity.speed;
                        if(entity.hitBox.intersects(gp.obj[i].hitBox)){
                            if(gp.obj[i].collision==true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case"down":
                        entity.hitBox.y += entity.speed;
                        if(entity.hitBox.intersects(gp.obj[i].hitBox)){
                            if(gp.obj[i].collision==true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case"left":
                        entity.hitBox.x -= entity.speed;
                        if(entity.hitBox.intersects(gp.obj[i].hitBox)){
                            if(gp.obj[i].collision==true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case"right":
                        entity.hitBox.x += entity.speed;
                        if(entity.hitBox.intersects(gp.obj[i].hitBox)){
                            if(gp.obj[i].collision==true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                            
                }
                entity.hitBox.x = entity.defaultHitboxX;
                entity.hitBox.y = entity.defaultHitboxY;
                gp.obj[i].hitBox.x = gp.obj[i].defaultHitboxX;
                gp.obj[i].hitBox.y = gp.obj[i].defaultHitboxY;
            }
        }
        return index;
    }
    public int checkEntity(Entity entity, Entity[] target){
                int index = 999;
        for(int i = 0 ; i<target.length ; i++){
            if(target[i]!=null){
                entity.hitBox.x = entity.worldX + entity.hitBox.x;
                entity.hitBox.y = entity.worldY + entity.hitBox.y;
                target[i].hitBox.x = target[i].worldX + target[i].hitBox.x;
                target[i].hitBox.y = target[i].worldY + target[i].hitBox.y;
                switch(entity.direction){
                    case"up":
                        entity.hitBox.y -= entity.speed;
                        if(entity.hitBox.intersects(target[i].hitBox)){
                            entity.collisionOn = true;
                                index = i;
                        }
                        break;
                    case"down":
                        entity.hitBox.y += entity.speed;
                        if(entity.hitBox.intersects(target[i].hitBox)){
                                entity.collisionOn = true;
                                index = i;
                        }
                        break;
                    case"left":
                        entity.hitBox.x -= entity.speed;
                        if(entity.hitBox.intersects(target[i].hitBox)){
                                entity.collisionOn = true;
                                index = i;
                        }
                        break;
                    case"right":
                        entity.hitBox.x += entity.speed;
                        if(entity.hitBox.intersects(target[i].hitBox)){
                                entity.collisionOn = true;
                                index = i;
                        }
                        break;
                            
                }
                entity.hitBox.x = entity.defaultHitboxX;
                entity.hitBox.y = entity.defaultHitboxY;
                target[i].hitBox.x = target[i].defaultHitboxX;
                target[i].hitBox.y = target[i].defaultHitboxY;
            }
        }
        return index;
    }
    public void checkPlayer(Entity entity){
                entity.hitBox.x = entity.worldX + entity.hitBox.x;
                entity.hitBox.y = entity.worldY + entity.hitBox.y;
                gp.player.hitBox.x = gp.player.worldX + gp.player.hitBox.x;
                gp.player.hitBox.y = gp.player.worldY + gp.player.hitBox.y;
                switch(entity.direction){
                    case"up":
                        entity.hitBox.y -= entity.speed;
                        if(entity.hitBox.intersects(gp.player.hitBox)){
                            entity.collisionOn = true;
                        }
                        break;
                    case"down":
                        entity.hitBox.y += entity.speed;
                        if(entity.hitBox.intersects(gp.player.hitBox)){
                                entity.collisionOn = true;
                        }
                        break;
                    case"left":
                        entity.hitBox.x -= entity.speed;
                        if(entity.hitBox.intersects(gp.player.hitBox)){
                                entity.collisionOn = true;
                        }
                        break;
                    case"right":
                        entity.hitBox.x += entity.speed;
                        if(entity.hitBox.intersects(gp.player.hitBox)){
                                entity.collisionOn = true;
                        }
                        break;
                            
                }
                entity.hitBox.x = entity.defaultHitboxX;
                entity.hitBox.y = entity.defaultHitboxY;
                gp.player.hitBox.x = gp.player.defaultHitboxX;
                gp.player.hitBox.y = gp.player.defaultHitboxY;
    }
}
