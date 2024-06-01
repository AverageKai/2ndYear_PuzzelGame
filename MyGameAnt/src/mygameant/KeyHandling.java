/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mygameant;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Miklos Bolarde
 */
public class KeyHandling implements KeyListener{
    GamePanel gp;
    public boolean pressedUp,pressedDown,pressedLeft,pressedRight,pressedSpace,pressedEnter;

    public KeyHandling(GamePanel gp) {
        this.gp = gp;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int code = e.getKeyCode();
        if(gp.gameState == gp.playState){
            if(code == KeyEvent.VK_W){
                pressedUp=true;
            }
            if(code == KeyEvent.VK_S){
                pressedDown=true;
            }
            if(code == KeyEvent.VK_A){
                pressedLeft=true;
            }
            if(code == KeyEvent.VK_D){
                pressedRight=true;
            }
            if(code == KeyEvent.VK_ESCAPE){
                gp.gameState = gp.optionState;
            }
            if(code == KeyEvent.VK_P){
                gp.gameState = gp.pauseState;
            }
            if(code == KeyEvent.VK_SPACE){
                pressedSpace = true;
            }
        }else if(gp.gameState==gp.pauseState){
            if(code == KeyEvent.VK_P){
                gp.gameState=gp.playState;
            }
        }else if(gp.gameState==gp.talkingState){
            if(code == KeyEvent.VK_SPACE||code == KeyEvent.VK_ESCAPE){
                gp.gameState=gp.playState;
            }
        }else if(gp.gameState==gp.optionState){
            if(code == KeyEvent.VK_ESCAPE){
                gp.gameState=gp.playState;
            }
            if(code == KeyEvent.VK_ENTER||code == KeyEvent.VK_SPACE){
                pressedEnter = true;
            }
            int pickOptionMax = 0;
            switch(gp.ui.options){
                case 0: pickOptionMax = 5; break;
                case 3: pickOptionMax = 1; break;
            }
            if(code == KeyEvent.VK_W){
                gp.ui.pickOption--;
                gp.playSoundEffect(5);
                if(gp.ui.pickOption<0){
                    gp.ui.pickOption = pickOptionMax;
                }
            }
            if(code == KeyEvent.VK_S){
                 gp.ui.pickOption++;
                gp.playSoundEffect(5);
                if(gp.ui.pickOption>pickOptionMax){
                    gp.ui.pickOption = 0;
                }
            }
            if(code == KeyEvent.VK_A){
                if(gp.ui.options==0){
                    if(gp.ui.pickOption==1&&gp.bm.volumeScale>0){
                        gp.bm.volumeScale--;
                        gp.bm.checkVolume();
                        gp.playSoundEffect(5);
                    }
                     if(gp.ui.pickOption==2&&gp.se.volumeScale>0){
                        gp.se.volumeScale--;
                        gp.playSoundEffect(5);
                    }
                }
            }
            if(code == KeyEvent.VK_D){
                if(gp.ui.options==0){
                    if(gp.ui.pickOption==1&&gp.bm.volumeScale<5){
                        gp.bm.volumeScale++;
                        gp.bm.checkVolume();
                        gp.playSoundEffect(5);
                    }
                    if(gp.ui.pickOption==2&&gp.se.volumeScale<5){
                        gp.se.volumeScale++;
                        gp.playSoundEffect(5);
                    }
                }
            }
        }else if(gp.gameOverState==gp.gameOverState){
            if(code == KeyEvent.VK_W){
                gp.ui.pickOption--;
                if(gp.ui.pickOption<0){
                    gp.ui.pickOption = 1;
                }
                gp.playSoundEffect(5);
            }
            if(code == KeyEvent.VK_S){
                gp.ui.pickOption++;
                if(gp.ui.pickOption>1){
                    gp.ui.pickOption = 0;
                }
                gp.playSoundEffect(5);
            }
            if(code == KeyEvent.VK_SPACE||code == KeyEvent.VK_ENTER){
                if (gp.ui.pickOption == 0) {
                    gp.bm.stop();
                    gp.gameState=gp.playState;
                    gp.reset();
                }
                if(gp.ui.pickOption == 1){
                    Main play = new Main();
                    play.closeGame();
                    gp.bm.stop();
                }
            }
        }else if(gp.gameOverState==gp.finishedState){
            if(code == KeyEvent.VK_W){
                gp.ui.pickOption--;
                if(gp.ui.pickOption<0){
                    gp.ui.pickOption = 1;
                }
                gp.playSoundEffect(5);
            }
            if(code == KeyEvent.VK_S){
                gp.ui.pickOption++;
                if(gp.ui.pickOption>1){
                    gp.ui.pickOption = 0;
                }
                gp.playSoundEffect(5);
            }
            if(code == KeyEvent.VK_SPACE||code == KeyEvent.VK_ENTER){
                if (gp.ui.pickOption == 0) {
                    gp.bm.stop();
                    gp.gameState=gp.playState;
                    gp.reset();
                }
                if(gp.ui.pickOption == 1){
                    Main play = new Main();
                    play.closeGame();
                    gp.bm.stop();
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            pressedUp=false;
        }
        if(code == KeyEvent.VK_S){
            pressedDown=false;
        }
        if(code == KeyEvent.VK_A){
            pressedLeft=false;
        }
        if(code == KeyEvent.VK_D){
            pressedRight=false;
        }
        
    }
}
