/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Environment;

import java.awt.Graphics2D;
import mygameant.GamePanel;

/**
 *
 * @author Miklos Bolarde
 */
public class EnvironmentManager {
    GamePanel gp;
    Lighting lighting;

    public EnvironmentManager(GamePanel gp) {
        this.gp = gp;
    }
    public void setUp(){
        lighting = new Lighting(gp);
    }
    public void update(){
        lighting.update();
    }
    public void draw(Graphics2D graphic){
        lighting.draw(graphic);
    }
}
