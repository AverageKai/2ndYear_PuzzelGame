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
public class ObjectHeart extends Entity{
    public ObjectHeart(GamePanel gp) {
        super(gp);
        name = "Heart";
        image = setup("/Objects/fullheart");
        image2 = setup("/Objects/halfheart");
        image3 = setup("/Objects/noheart");
    }
}
