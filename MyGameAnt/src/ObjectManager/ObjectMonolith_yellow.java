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
public class ObjectMonolith_yellow extends Entity{
    public ObjectMonolith_yellow(GamePanel gp) {
        super(gp);
        name = "Monolith Yellow";
        down1 = setup("/Objects/Mono_yellow");
        collision = true;
    }  
}
