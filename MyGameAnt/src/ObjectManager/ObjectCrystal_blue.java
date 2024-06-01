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
public class ObjectCrystal_blue extends Entity{
    public ObjectCrystal_blue(GamePanel gp) {
        super(gp);
        name = "Blue Crystal";
        down1 = setup("/Objects/Crystal_blue");
    }
    
}
