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
public class ObjectMonolith_red extends Entity{
    public ObjectMonolith_red(GamePanel gp) {
        super(gp);
        name = "Monolith Red";
        down1 = setup("/Objects/Mono_red");
        collision = true;
    }  
}
