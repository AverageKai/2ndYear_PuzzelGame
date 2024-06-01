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
public class ObjectMonolith_orange extends Entity{
    public ObjectMonolith_orange(GamePanel gp) {
        super(gp);
        name = "Monolith Orange";
        down1 = setup("/Objects/Mono_orange");
        collision = true;
    } 
}
