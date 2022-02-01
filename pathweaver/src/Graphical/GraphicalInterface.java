package src.Graphical;

//JavaAWT Imports
import java.awt.Component;
import javax.swing.*;

/**
 * DESCRIPTION: This class is used to draw a frame on the screen that can be used to
 * plot points in the field.
 * DATE: 1/31/2022
 * @author Lance Hartman
 */
public class GraphicalInterface {
    
    JFrame frame;

    public GraphicalInterface(String title, Component c){
        frame = new JFrame(title);
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(c);
        frame.pack();
        frame.setVisible(true);
    }

    public int getFrameWidth(){
        assert frame != null;
        return frame.getWidth();
    }

    public int getFrameHeight(){
        assert frame != null;
        return frame.getHeight();
    }

}
