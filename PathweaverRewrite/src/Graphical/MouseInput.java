package Graphical;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;

import Graphical.BezierGraphical.*;

public class MouseInput extends MouseAdapter{
    
    private BezierGraphManager m_BezierGraphManager;
    private KeyInput m_keyInput;

    private Dimension m_boundaryMin;
    private Dimension m_boundaryMax;

    public MouseInput(BezierGraphManager m_BezierGraphManager, 
                      KeyInput m_keyInput,
                      Dimension m_boundaryMin,
                      Dimension m_boundaryMax){
        this.m_BezierGraphManager = m_BezierGraphManager;
        this.m_keyInput = m_keyInput;
        this.m_boundaryMin = m_boundaryMin;
        this.m_boundaryMax = m_boundaryMax;
    }

    public void mouseReleased(MouseEvent e){
        int x = e.getX();
        int y = e.getY();
        //ESTABLISH MORE GUIDELINES IN FUTURE!!

        //Determine x and y through MATH
        if(x > m_boundaryMin.getWidth() && x < m_boundaryMax.getWidth()){
            if(y > m_boundaryMin.getHeight() && y < m_boundaryMax.getHeight()){
                if(m_keyInput.getKeysDown()[0]){
                    //This code will create a point - waypoint in between start and end
                    //TODO Calculate the posX and posY (1, 1) using the final coordinate grid
                    m_BezierGraphManager.addPoint(new BezierPoint(1, 1, 
                                                                  x, y, 
                                                                  BezierID.WAYPOINT));
                }else if(m_keyInput.getKeysDown()[1]){
                    //This code will create a point - start point of path
                    //TODO Calculate the posX and posY (1, 1) using the final coordinate grid
                    m_BezierGraphManager.addPoint(new BezierPoint(1, 1, 
                                                                  x, y, 
                                                                  BezierID.START));
                }else if(m_keyInput.getKeysDown()[2]){
                    //This code will create a point - end point of path
                    //TODO Calculate the posX and posY (1, 1) using the final coordinate grid
                    m_BezierGraphManager.addPoint(new BezierPoint(1, 1, 
                                                                  x, y, 
                                                                  BezierID.END));
                }else if(m_keyInput.getKeysDown()[3]){
                    //This code will toggle on editing b-zeta point values
                }
                // m_BezierGraphManager.addPoint(new BezierPoint(1, 1, x, y, BezierID.MIDPOINT));
            }
        }
    }

}
