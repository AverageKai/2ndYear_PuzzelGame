/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mygameant;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Miklos Bolarde
 */
public class Optimaization {
    public BufferedImage scaleImage(BufferedImage original,int width,int height){
        BufferedImage scaledImage = new BufferedImage(width,height,original.getType());
        Graphics2D graphics = scaledImage.createGraphics();
        graphics.drawImage(original,0,0,width,height,null);
        graphics.dispose();
        return scaledImage;
    }
}
