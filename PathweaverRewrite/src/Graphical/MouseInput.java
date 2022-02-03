package Graphical;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Dimension;

import Graphical.BezierGraphical.*;

public class MouseInput extends MouseAdapter{
    
    private BezierGraphManager m_BezierGraphManager;
    private KeyInput m_keyInput;

    private Dimension m_boundaryMin;
    private Dimension m_boundaryMax;

    private BezierPoint selected = null;

    public MouseInput(BezierGraphManager m_BezierGraphManager, 
                      KeyInput m_keyInput,
                      Dimension m_boundaryMin,
                      Dimension m_boundaryMax){
        this.m_BezierGraphManager = m_BezierGraphManager;
        this.m_keyInput = m_keyInput;
        this.m_boundaryMin = m_boundaryMin;
        this.m_boundaryMax = m_boundaryMax;
    }

    @Override
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

    //BUGGY
    @Override
    public void mouseClicked(MouseEvent e){
        int x = e.getX(), y = e.getY();
        if(m_keyInput.getMidpointsToggled()){
            if(selected == null){
                BezierPoint currClicked = isOnMidpoint(x, y);
                if(currClicked != null){
                    System.out.println("Clicked");
                    selected = currClicked;
                    selected.setColor(Color.CYAN);
                }else{
                    System.out.println("Null clicked");
                }
            }else{
                selected.setPosX(selected.getPosX()); //TODO MODIFY CALCULATIONS
                selected.setPosY(selected.getPosY()); //TODO MODIFY CALCULATIONS
                selected.setScreenX(x);
                selected.setScreenY(y);
                // m_BezierGraphManager.redrawPath();
                selected.setColor(Color.MAGENTA);
                selected = null;
            }
        }
    }

    /**
     * Determines if the click is on any midpoint
     * @param x X point of click
     * @param y Y point of click
     * @return The midpoint it is selecting
     */
    public BezierPoint isOnMidpoint(int x, int y){
        for(BezierPoint p : m_BezierGraphManager.getMidpoints()){
            if(x < p.getScreenX() + BezierPoint.RADIUS && x > p.getScreenX() - BezierPoint.RADIUS){
                if(y < p.getScreenY() + BezierPoint.RADIUS && y > p.getScreenY() - BezierPoint.RADIUS){
                    assert p.getID().equals(BezierID.MIDPOINT);
                    return p;
                }
            }
        }
        return null;
    }

    public void deselectMidpoint(){

    }

}
