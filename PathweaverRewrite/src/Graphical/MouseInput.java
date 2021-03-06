package Graphical;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Dimension;

import Graphical.BezierGraphical.*;

public class MouseInput extends MouseAdapter{
    
    private BezierGraphManager m_bezierGraphManager;
    private KeyInput m_keyInput;

    private Dimension m_boundaryMin;
    private Dimension m_boundaryMax;

    private BezierPoint selected = null;
    private int lockedX = 0;
    private int lockedY = 0;

    public MouseInput(BezierGraphManager m_bezierGraphManager, 
                      KeyInput m_keyInput,
                      Dimension m_boundaryMin,
                      Dimension m_boundaryMax){
        this.m_bezierGraphManager = m_bezierGraphManager;
        this.m_keyInput = m_keyInput;
        this.m_boundaryMin = m_boundaryMin;
        this.m_boundaryMax = m_boundaryMax;
    }

    public void tick(){
        if(selected != null){
            // temp.redrawPath();
            selected.setPosX(selected.getPosX()); //TODO MODIFY CALCULATIONS
            selected.setPosY(selected.getPosY()); //TODO MODIFY CALCULATIONS
            selected.setScreenX(lockedX);
            selected.setScreenY(lockedY);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e){
        int x = e.getX();
        int y = e.getY();

        try{
            selected.setColor(Color.MAGENTA);
            selected = null;
        }catch(Exception ignored){}

        //ESTABLISH MORE GUIDELINES IN FUTURE!
        //Determine x and y through MATH
        if(x > m_boundaryMin.getWidth() && x < m_boundaryMax.getWidth()){
            if(y > m_boundaryMin.getHeight() && y < m_boundaryMax.getHeight()){
                if(m_keyInput.getKeysDown()[0]){
                    //TODO Calculate the posX and posY (1, 1) using the final coordinate grid
                    if(m_bezierGraphManager.hasStart() && !m_bezierGraphManager.hasEnd()){
                        m_bezierGraphManager.addPoint(new BezierPoint(1, 1, 
                                                                      x, y, 
                                                                      BezierID.WAYPOINT));
                    }
                }else if(m_keyInput.getKeysDown()[1]){
                    //TODO Calculate the posX and posY (1, 1) using the final coordinate grid
                    if(m_bezierGraphManager.getMidpoints().size() == 0 && !m_bezierGraphManager.hasStart()){
                        m_bezierGraphManager.addPoint(new BezierPoint(1, 1, 
                                                                      x, y, 
                                                                      BezierID.START));
                    }
                }else if(m_keyInput.getKeysDown()[2]){
                    //TODO Calculate the posX and posY (1, 1) using the final coordinate grid
                    if(m_bezierGraphManager.hasStart() && !m_bezierGraphManager.hasEnd()){
                        m_bezierGraphManager.addPoint(new BezierPoint(1, 1, 
                                                                      x, y, 
                                                                      BezierID.END));
                    }
                }
                // else if(m_keyInput.getKeysDown()[3]){
                //     //This code will toggle on editing b-zeta point values
                // }
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e){
        if(m_keyInput.getMidpointsToggled()){
            if(selected != null){
                lockedX = e.getX();
                lockedY = e.getY();
                for(int i = 0; i < m_bezierGraphManager.getLines().size(); i++){
                    BezierLine temp = m_bezierGraphManager.getLines().get(i);
                    if(temp.getMidpoint().equals(selected)){
                        temp.redrawPath();
                        break;
                    }
                }
            }else{
                BezierPoint currClicked = isOnMidpoint(e.getX(), e.getY());
                if(currClicked != null){
                    selected = currClicked;
                    selected.setColor(Color.CYAN);
                }
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
        for(BezierPoint p : m_bezierGraphManager.getMidpoints()){
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
