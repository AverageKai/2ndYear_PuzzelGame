/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mygameant;

import Entity.*;
import ObjectManager.*;

/**
 *
 * @author Miklos Bolarde
 */
public class PlaceObject {
    GamePanel gp;
    public PlaceObject(GamePanel gp) {
        this.gp = gp;
    }
    public void setObject(){
        switch(gp.randomWorld){
            case 1:
                gp.obj[0] = new ObjectCoin(gp);
                gp.obj[0].worldX = 25 * gp.tileSize;
                gp.obj[0].worldY = 8 * gp.tileSize;
                
                gp.obj[12] = new ObjectCoin(gp);
                gp.obj[12].name="Coin1";
                gp.obj[12].worldX = 39 * gp.tileSize;
                gp.obj[12].worldY = 14 * gp.tileSize;

                gp.obj[1] = new ObjectChest(gp);
                gp.obj[1].worldX = 42 * gp.tileSize;
                gp.obj[1].worldY = 23 * gp.tileSize;
                gp.obj[1].collision = true;

                gp.obj[3] = new ObjectMonolith_red(gp);
                gp.obj[3].worldX = 45 * gp.tileSize;
                gp.obj[3].worldY = 44 * gp.tileSize;

                gp.obj[4] = new ObjectCrystal_red(gp);
                gp.obj[4].worldX = 26 * gp.tileSize;
                gp.obj[4].worldY = 45 * gp.tileSize;

                gp.obj[5] = new ObjectMonolith_blue(gp);
                gp.obj[5].worldX = 7 * gp.tileSize;
                gp.obj[5].worldY = 40 * gp.tileSize;

                gp.obj[6] = new ObjectCrystal_blue(gp);
                gp.obj[6].worldX = 10 * gp.tileSize;
                gp.obj[6].worldY = 36 * gp.tileSize;

                gp.obj[7] = new ObjectMonolith_yellow(gp);
                gp.obj[7].worldX = 17 * gp.tileSize;
                gp.obj[7].worldY = 10 * gp.tileSize;

                gp.obj[8] = new ObjectCrystal_yellow(gp);
                gp.obj[8].worldX = 4 * gp.tileSize;
                gp.obj[8].worldY = 14 * gp.tileSize;

                gp.obj[9] = new ObjectMonolith_orange(gp);
                gp.obj[9].worldX = 44 * gp.tileSize;
                gp.obj[9].worldY = 5 * gp.tileSize;

                gp.obj[10] = new ObjectCrystal_orange(gp);
                gp.obj[10].worldX = 29 * gp.tileSize;
                gp.obj[10].worldY = 5 * gp.tileSize;

                gp.obj[11] = new ObjectDoor(gp);
                gp.obj[11].worldX = 21 * gp.tileSize;
                gp.obj[11].worldY = 4 * gp.tileSize;
                gp.obj[11].collision = true;
                break;
            case 2:
                gp.obj[0] = new ObjectCoin(gp);
                gp.obj[0].worldX = 5 * gp.tileSize;
                gp.obj[0].worldY = 25 * gp.tileSize;
                
                gp.obj[12] = new ObjectCoin(gp);
                gp.obj[12].name="Coin1";
                gp.obj[12].worldX = 41 * gp.tileSize;
                gp.obj[12].worldY = 26 * gp.tileSize;

                gp.obj[1] = new ObjectChest(gp);
                gp.obj[1].worldX = 29 * gp.tileSize;
                gp.obj[1].worldY = 27 * gp.tileSize;
                gp.obj[1].collision = true;

                gp.obj[3] = new ObjectMonolith_red(gp);
                gp.obj[3].worldX = 20 * gp.tileSize;
                gp.obj[3].worldY = 32 * gp.tileSize;

                gp.obj[4] = new ObjectCrystal_red(gp);
                gp.obj[4].worldX = 7 * gp.tileSize;
                gp.obj[4].worldY = 41 * gp.tileSize;

                gp.obj[5] = new ObjectMonolith_blue(gp);
                gp.obj[5].worldX = 42 * gp.tileSize;
                gp.obj[5].worldY = 4 * gp.tileSize;

                gp.obj[6] = new ObjectCrystal_blue(gp);
                gp.obj[6].worldX = 39 * gp.tileSize;
                gp.obj[6].worldY = 6 * gp.tileSize;

                gp.obj[7] = new ObjectMonolith_yellow(gp);
                gp.obj[7].worldX = 44 * gp.tileSize;
                gp.obj[7].worldY = 43 * gp.tileSize;

                gp.obj[8] = new ObjectCrystal_yellow(gp);
                gp.obj[8].worldX = 24 * gp.tileSize;
                gp.obj[8].worldY = 29 * gp.tileSize;

                gp.obj[9] = new ObjectMonolith_orange(gp);
                gp.obj[9].worldX = 21 * gp.tileSize;
                gp.obj[9].worldY = 4 * gp.tileSize;

                gp.obj[10] = new ObjectCrystal_orange(gp);
                gp.obj[10].worldX = 5 * gp.tileSize;
                gp.obj[10].worldY = 25 * gp.tileSize;

                gp.obj[11] = new ObjectDoor(gp);
                gp.obj[11].worldX = 28 * gp.tileSize;
                gp.obj[11].worldY = 20 * gp.tileSize;
                gp.obj[11].collision = true;
                break;
            case 3:
                gp.obj[0] = new ObjectCoin(gp);
                gp.obj[0].worldX = 44 * gp.tileSize;
                gp.obj[0].worldY = 17 * gp.tileSize;
                
                gp.obj[12] = new ObjectCoin(gp);
                gp.obj[12].name="Coin1";
                gp.obj[12].worldX = 28 * gp.tileSize;
                gp.obj[12].worldY = 29 * gp.tileSize;

                gp.obj[1] = new ObjectChest(gp);
                gp.obj[1].worldX = 17 * gp.tileSize;
                gp.obj[1].worldY = 18 * gp.tileSize;
                gp.obj[1].collision = true;

                gp.obj[3] = new ObjectMonolith_red(gp);
                gp.obj[3].worldX = 5 * gp.tileSize;
                gp.obj[3].worldY = 28 * gp.tileSize;

                gp.obj[4] = new ObjectCrystal_red(gp);
                gp.obj[4].worldX = 4 * gp.tileSize;
                gp.obj[4].worldY = 14 * gp.tileSize;

                gp.obj[5] = new ObjectMonolith_blue(gp);
                gp.obj[5].worldX = 33 * gp.tileSize;
                gp.obj[5].worldY = 9 * gp.tileSize;

                gp.obj[6] = new ObjectCrystal_blue(gp);
                gp.obj[6].worldX = 22 * gp.tileSize;
                gp.obj[6].worldY = 36 * gp.tileSize;

                gp.obj[7] = new ObjectMonolith_yellow(gp);
                gp.obj[7].worldX = 5 * gp.tileSize;
                gp.obj[7].worldY = 16 * gp.tileSize;

                gp.obj[8] = new ObjectCrystal_yellow(gp);
                gp.obj[8].worldX = 14 * gp.tileSize;
                gp.obj[8].worldY = 36 * gp.tileSize;

                gp.obj[9] = new ObjectMonolith_orange(gp);
                gp.obj[9].worldX = 45 * gp.tileSize;
                gp.obj[9].worldY = 34 * gp.tileSize;

                gp.obj[10] = new ObjectCrystal_orange(gp);
                gp.obj[10].worldX = 9 * gp.tileSize;
                gp.obj[10].worldY = 29 * gp.tileSize;

                gp.obj[11] = new ObjectDoor(gp);
                gp.obj[11].worldX = 8 * gp.tileSize;
                gp.obj[11].worldY = 14 * gp.tileSize;
                gp.obj[11].collision = true;
                break;
            case 4:
                gp.obj[0] = new ObjectCoin(gp);
                gp.obj[0].worldX = 36 * gp.tileSize;
                gp.obj[0].worldY = 21 * gp.tileSize;
                
                gp.obj[12] = new ObjectCoin(gp);
                gp.obj[12].name="Coin1";
                gp.obj[12].worldX = 10 * gp.tileSize;
                gp.obj[12].worldY = 28 * gp.tileSize;

                gp.obj[1] = new ObjectChest(gp);
                gp.obj[1].worldX = 28 * gp.tileSize;
                gp.obj[1].worldY = 33 * gp.tileSize;
                gp.obj[1].collision = true;

                gp.obj[3] = new ObjectMonolith_red(gp);
                gp.obj[3].worldX = 45 * gp.tileSize;
                gp.obj[3].worldY = 23 * gp.tileSize;

                gp.obj[4] = new ObjectCrystal_red(gp);
                gp.obj[4].worldX = 12 * gp.tileSize;
                gp.obj[4].worldY = 32 * gp.tileSize;

                gp.obj[5] = new ObjectMonolith_blue(gp);
                gp.obj[5].worldX = 27 * gp.tileSize;
                gp.obj[5].worldY = 4 * gp.tileSize;

                gp.obj[6] = new ObjectCrystal_blue(gp);
                gp.obj[6].worldX = 28 * gp.tileSize;
                gp.obj[6].worldY = 41 * gp.tileSize;

                gp.obj[7] = new ObjectMonolith_yellow(gp);
                gp.obj[7].worldX = 9 * gp.tileSize;
                gp.obj[7].worldY = 17 * gp.tileSize;

                gp.obj[8] = new ObjectCrystal_yellow(gp);
                gp.obj[8].worldX = 45 * gp.tileSize;
                gp.obj[8].worldY = 32 * gp.tileSize;

                gp.obj[9] = new ObjectMonolith_orange(gp);
                gp.obj[9].worldX = 34 * gp.tileSize;
                gp.obj[9].worldY = 34 * gp.tileSize;

                gp.obj[10] = new ObjectCrystal_orange(gp);
                gp.obj[10].worldX = 33 * gp.tileSize;
                gp.obj[10].worldY = 11 * gp.tileSize;

                gp.obj[11] = new ObjectDoor(gp);
                gp.obj[11].worldX = 33 * gp.tileSize;
                gp.obj[11].worldY = 21 * gp.tileSize;
                gp.obj[11].collision = true;
                break;
                case 5:
                gp.obj[0] = new ObjectCoin(gp);
                gp.obj[0].worldX = 44 * gp.tileSize;
                gp.obj[0].worldY = 1 * gp.tileSize;
                
                gp.obj[12] = new ObjectCoin(gp);
                gp.obj[12].name="Coin1";
                gp.obj[12].worldX = 11 * gp.tileSize;
                gp.obj[12].worldY = 33 * gp.tileSize;

                gp.obj[1] = new ObjectChest(gp);
                gp.obj[1].worldX = 36 * gp.tileSize;
                gp.obj[1].worldY = 13 * gp.tileSize;
                gp.obj[1].collision = true;

                gp.obj[3] = new ObjectMonolith_red(gp);
                gp.obj[3].worldX = 48 * gp.tileSize;
                gp.obj[3].worldY = 43 * gp.tileSize;

                gp.obj[4] = new ObjectCrystal_red(gp);
                gp.obj[4].worldX = 1 * gp.tileSize;
                gp.obj[4].worldY = 48 * gp.tileSize;

                gp.obj[5] = new ObjectMonolith_blue(gp);
                gp.obj[5].worldX = 1 * gp.tileSize;
                gp.obj[5].worldY = 1 * gp.tileSize;

                gp.obj[6] = new ObjectCrystal_blue(gp);
                gp.obj[6].worldX = 48 * gp.tileSize;
                gp.obj[6].worldY = 2 * gp.tileSize;

                gp.obj[7] = new ObjectMonolith_yellow(gp);
                gp.obj[7].worldX = 4 * gp.tileSize;
                gp.obj[7].worldY = 28 * gp.tileSize;

                gp.obj[8] = new ObjectCrystal_yellow(gp);
                gp.obj[8].worldX = 45 * gp.tileSize;
                gp.obj[8].worldY = 22 * gp.tileSize;

                gp.obj[9] = new ObjectMonolith_orange(gp);
                gp.obj[9].worldX = 25 * gp.tileSize;
                gp.obj[9].worldY = 33 * gp.tileSize;

                gp.obj[10] = new ObjectCrystal_orange(gp);
                gp.obj[10].worldX = 24 * gp.tileSize;
                gp.obj[10].worldY = 34 * gp.tileSize;

                gp.obj[11] = new ObjectDoor(gp);
                gp.obj[11].worldX = 25 * gp.tileSize;
                gp.obj[11].worldY = 0  * gp.tileSize;
                gp.obj[11].collision = true;
                break;
                    
        }
    }
    public void setNPC(){
        switch(gp.randomWorld){
            case 1:
                gp.npc[0] = new  NPC_Tao(gp);
                gp.npc[0].worldX = gp.player.defaultWorldX-96;
                gp.npc[0].worldY = gp.player.defaultWorldY-96;
                break;
            case 2:
                gp.npc[0] = new  NPC_Tao(gp);
                gp.npc[0].worldX = gp.player.defaultWorldX-96;
                gp.npc[0].worldY = gp.player.defaultWorldY-96;
                break;
            case 3:
                gp.npc[0] = new  NPC_Tao(gp);
                gp.npc[0].worldX = gp.player.defaultWorldX-96;
                gp.npc[0].worldY = gp.player.defaultWorldY-96;
                break;
            case 4:
                gp.npc[0] = new  NPC_Tao(gp);
                gp.npc[0].worldX = gp.player.defaultWorldX-96;
                gp.npc[0].worldY = gp.player.defaultWorldY-96;
                break;
            case 5:
                gp.npc[0] = new  NPC_Tao(gp);
                gp.npc[0].worldX = gp.player.defaultWorldX-96;
                gp.npc[0].worldY = gp.player.defaultWorldY-96;
                break;
        }
    }
}
