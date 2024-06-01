/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import mygameant.GamePanel;
import mygameant.Optimaization;

/**
 *
 * @author Miklos Bolarde
 */
public class NPC_Tao extends Entity {

    public NPC_Tao(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 1;
        getNPCImage();
        setText();
    }
    public void getNPCImage(){
        up1 = setup("oldman_up_1");
        up2 = setup("oldman_up_2");
        down1 = setup("oldman_down_1");
        down2 = setup("oldman_down_2");
        left1 = setup("oldman_left_1");
        left2 = setup("oldman_left_2");
        right1 = setup("oldman_right_1");
        right2 = setup("oldman_right_2");
    }
    public void setText(){
        text[0]="What are you doing?!/n Do not destroy this sacred Forest";
        text[1]="I am the frog that you pissed on/n You want to know how to get out?/n";
        text[2]="First find the Orbs and then put it in the right place/n then you will get out";
        text[3]="Well since im nice i give you this warning/n My Friends put traps be wary of them";
    }
    public BufferedImage setup(String imagePath){
        Optimaization op = new Optimaization();
        BufferedImage image = null;
        try{
            image = ImageIO.read(getClass().getResource("/Npc/"+imagePath+".png"));
            image = op.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            System.out.println("Error! : "+e);
        }
        return image;
    }
    public void setAction(){
        entityTime++;
        if(entityTime == 120){
            Random rand = new Random();
            int i = rand.nextInt(100)+1;
            if(i<=25){
                direction="up";
            }else if(i>25&&i<=50){
                direction="down";
            }else if(i>50&&i<=75){
                direction="left";
            }else if(i>75&&i<=100){
                direction="right";
            }
            entityTime = 0 ;
        }
    }
    public void speak(){
        super.speak();
    }
}
