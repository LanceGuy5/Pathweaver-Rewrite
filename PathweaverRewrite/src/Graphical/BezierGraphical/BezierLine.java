package Graphical.BezierGraphical;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.BasicStroke;
import java.awt.Color;

public class BezierLine {
    
    BezierPoint p1, p2;

    BezierPoint midpoint;

    BezierGraphManager m_bezierGraphManager;

    Path2D.Double p2d;

    public BezierLine(BezierPoint p1, BezierPoint p2, BezierGraphManager m_bezierGraphManager){
        this.p1 = p1;
        this.p2 = p2;
        this.m_bezierGraphManager = m_bezierGraphManager;
        midpoint = new BezierPoint((p1.getPosX() + p2.getPosX()) / 2, 
                                   (p1.getPosY() + p2.getPosY()) / 2, 
                                   (p1.getScreenX() + p2.getScreenX()) / 2, 
                                   (p1.getScreenY() + p2.getScreenY()) / 2, 
                                   BezierID.MIDPOINT);
        m_bezierGraphManager.addMidpoint(midpoint);
        //DEAL WITH THIS IN ANOTHER CLASS? - CREATE PATH RENDERING CLASS, ADD PATHS FROM EVERY LINE, RENDER AS ONE PATH
        p2d = new Path2D.Double();
        p2d.moveTo(p1.getScreenX(), p1.getPosY());
        p2d.curveTo(p1.getScreenX(), 
                    p1.getScreenY(), 
                    p2.getScreenX(), 
                    p2.getScreenY(), 
                    midpoint.getScreenX(), 
                    midpoint.getScreenY());
    }

    public void render(Graphics g){
        g.setColor(Color.BLACK);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setStroke(new BasicStroke(10));
        //TODO CHECK TO MAKE SURE POINTS ARE ORGANIZED CORRECTLY
        g2d.draw(p2d);
    }

    public void deconstruct(){
        m_bezierGraphManager.removePoint(midpoint);
    }

    //B(t) = (1-t)[(1-t)P0+tP1]+t[(1-t)P1+tP2]
    //B'(t) = 2(1-t)(P1-P0)+2t(P2-P1)

}
