/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mygameant;

import java.io.File;
import java.util.Random;

/**
 *
 * @author Miklos Bolarde
 */
public class test {
    public static void main(String[] args) {
        File folder = new File("C:/Users/bolar/OneDrive/Documents/NetBeansProjects/MyGameAnt/src/Tiles");
        File[] listOfTiles = folder.listFiles();

        for (int i = 0; i < listOfTiles.length; i++) {
          if (listOfTiles[i].isFile()) {
            System.out.println("File " + listOfTiles[i].getName()+"         Index:"+i);
            }
        }
        File folder1 = new File("C:/Users/bolar/OneDrive/Documents/NetBeansProjects/MyGameAnt/src/Sounds");
        File[] listOfSounds = folder1.listFiles();
        for (int i = 0; i < listOfSounds.length; i++) {
                 System.out.println("SoundFile " + listOfSounds[i].getName()+"         Index:"+i);
        }
         Random ran = new Random();
         int random;
          for(int j = 1 ; j<=48 ; j++){
              random = ran.nextInt(2);
              if(random==0){
                  System.out.print("10 ");
              }else{
                  System.out.print("11 ");
              }
          }
    }
}
