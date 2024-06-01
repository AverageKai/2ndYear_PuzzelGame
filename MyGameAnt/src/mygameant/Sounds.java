/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mygameant;

import java.io.File;
import java.net.URL;
import javax.sound.sampled.*;

/**
 *
 * @author Miklos Bolarde
 */
public class Sounds {
    Clip clip;
    URL soundURL[] = new URL[30];
    FloatControl fc;
    int volumeScale = 3;
    float volume;
    public Sounds() {
        File folder = new File("C:/Users/bolar/OneDrive/Documents/NetBeansProjects/MyGameAnt/src/Sounds");
        File[] listOfSounds = folder.listFiles();
        for (int i = 0; i < listOfSounds.length; i++) {
                 soundURL[i] = getClass().getResource("/Sounds/"+listOfSounds[i].getName());
        }
    }
    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
        }catch(Exception e){
            System.out.println("Error! "+e);
        }
        
    }
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
    public void checkVolume(){
        switch(volumeScale){
            case 0: volume = -80f; break; 
            case 1: volume = -20f; break; 
            case 2: volume = -12f; break; 
            case 3: volume = -5f; break; 
            case 4: volume = 1f; break; 
            case 5: volume = 6f; break; 
        }
        fc.setValue(volume);
    }
}
