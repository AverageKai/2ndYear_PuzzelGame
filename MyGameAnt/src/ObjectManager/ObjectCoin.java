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
public class ObjectCoin extends Entity{
    public ObjectCoin(GamePanel gp) {
        super(gp);
        name = "Coin";
        down1 = setup("/Objects/coin");
        image = setup("/Objects/coin");
    }
    
}
