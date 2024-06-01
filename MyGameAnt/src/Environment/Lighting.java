/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Environment;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import mygameant.GamePanel;

/**
 *
 * @author Miklos Bolarde
 */
public class Lighting {
    GamePanel gp;
    BufferedImage darkfilter;

    public Lighting(GamePanel gp) {
         this.gp = gp;
         setLight();
    }
    public void setLight(){
        darkfilter = new BufferedImage(gp.screenWidth,gp.screenHeight,BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphic = (Graphics2D)darkfilter.getGraphics();
        int centerX = gp.player.screenX + (gp.tileSize)/2;
        int centerY = gp.player.screenY + (gp.tileSize)/2;
        Color color[] = new Color[12];
        float fraction[] = new float[12];
        color[0] = new Color(0,0,0,0.1f);
        color[1] = new Color(0,0,0,0.25f);
        color[2] = new Color(0,0,0,0.5f);
        color[3] = new Color(0,0,0,0.61f);
        color[4] = new Color(0,0,0,0.69f);
        color[5] = new Color(0,0,0,0.76f);
        color[6] = new Color(0,0,0,0.82f);
        color[7] = new Color(0,0,0,0.87f);
        color[8] = new Color(0,0,0,0.91f);
        color[9] = new Color(0,0,0,0.94f);
        color[10] = new Color(0,0,0,0.96f);
        color[11] = new Color(0,0,0,0.98f);
        fraction[0] = 0f;
        fraction[1] = 0.4f;
        fraction[2] = 0.5f;
        fraction[3] = 0.6f;
        fraction[4] = 0.65f;
        fraction[5] = 0.7f;
        fraction[6] = 0.75f;
        fraction[7] = 0.8f;
        fraction[8] = 0.85f;
        fraction[9] = 0.90f;
        fraction[10] = 0.95f;
        fraction[11] = 1f;
        if(gp.player.changeLight==false){
            RadialGradientPaint paint = new RadialGradientPaint(centerX,centerY,(400/2),fraction,color);
            graphic.setPaint(paint);
        }else{
            RadialGradientPaint paint = new RadialGradientPaint(centerX,centerY,(600/2),fraction,color);
            graphic.setPaint(paint);
        }
        graphic.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        graphic.dispose();; 
    }
    public void update(){
         if(gp.player.lightUpdated==true){
             setLight();
             gp.player.lightUpdated=false;
         }
    }
    public void draw(Graphics2D graphic){
        graphic.drawImage(darkfilter, 0, 0,null);
    }
    
}
