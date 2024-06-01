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
public class ObjectClock extends Entity{
     GamePanel gp;
    public ObjectClock(GamePanel gp) {
        super(gp);
        name = "Clock";
        down1 = setup("/Objects/clock");
        image = setup("/Objects/clock");
    }
}
