/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ObjectManager;

import Entity.Entity;
import java.io.IOException;
import javax.imageio.ImageIO;
import mygameant.GamePanel;

/**
 *
 * @author Miklos Bolarde
 */
public class ObjectDoor extends Entity{
    public ObjectDoor(GamePanel gp) {
        super(gp);
        name = "Door";
        down1 = setup("/Objects/Door");
        collision = true;
        hitBox.x=0;
        hitBox.y=16;
        hitBox.width=48;
        hitBox.height=32;
        defaultHitboxX=hitBox.x;
        defaultHitboxY=hitBox.y;
    }
    
}
