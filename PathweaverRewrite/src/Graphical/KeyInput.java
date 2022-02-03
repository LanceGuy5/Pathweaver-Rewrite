package Graphical;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.Arrays;

public class KeyInput extends KeyAdapter{
    
    //[0] == P (Point), [1] == S (Start), [2] == E (End), [3] == L (Midpoint)
    //[4] == \ (Terminal) [5] == ESC (deselect)
    private boolean[] keysDown = new boolean[6];

    private boolean toggleMidpoints = true;

    public KeyInput(){
        Arrays.fill(keysDown, false);
    }

    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_P){
            keysDown[0] = true;    
        }
        if(e.getKeyCode() == KeyEvent.VK_S){
            keysDown[1] = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_E){
            keysDown[2] = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_B){
            keysDown[3] = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_BACK_SLASH){
            keysDown[4] = true;
        }
        // if(e.getKeyCode() == KeyEvent.VK_ESCAPE){

        // }
        if(e.getKeyCode() == KeyEvent.VK_M){
            toggleMidpoints = !toggleMidpoints;
        }
    }

    public void keyReleased(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_P){
            keysDown[0] = false;    
        }
        if(e.getKeyCode() == KeyEvent.VK_S){
            keysDown[1] = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_E){
            keysDown[2] = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_B){
            keysDown[3] = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_BACK_SLASH){
            keysDown[4] = false;
        }
    }

    public boolean[] getKeysDown(){
        return keysDown;
    }

    public boolean getMidpointsToggled(){
        return toggleMidpoints;
    }

}
