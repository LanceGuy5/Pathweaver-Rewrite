package src.Graphical;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.Arrays;

public class KeyInput extends KeyAdapter{
    
    //[0] == P (Point), [1] == S (Start), [2] == E (End), [3] == L (Midpoint)
    //[4] == \ (Terminal)
    private boolean[] keysDown = new boolean[5];

    public KeyInput(){
        Arrays.fill(keysDown, false);
    }

    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_P){
            keysDown[0] = true;    
        }else if(e.getKeyCode() == KeyEvent.VK_S){
            keysDown[1] = true;
        }else if(e.getKeyCode() == KeyEvent.VK_E){
            keysDown[2] = true;
        }else if(e.getKeyCode() == KeyEvent.VK_M){
            keysDown[3] = true;
        }else if(e.getKeyCode() == KeyEvent.VK_BACK_SLASH){
            keysDown[4] = true;
        }
    }

    public void keyReleased(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_P){
            keysDown[0] = false;    
        }else if(e.getKeyCode() == KeyEvent.VK_S){
            keysDown[1] = false;
        }else if(e.getKeyCode() == KeyEvent.VK_E){
            keysDown[2] = false;
        }else if(e.getKeyCode() == KeyEvent.VK_M){
            keysDown[3] = false;
        }else if(e.getKeyCode() == KeyEvent.VK_BACK_SLASH){
            keysDown[4] = false;
        }
    }

    public boolean[] getKeysDown(){
        return keysDown;
    }

}
