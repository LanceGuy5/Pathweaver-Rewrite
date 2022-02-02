package Graphical.BezierGraphical;

import java.util.LinkedList;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

public class BezierGraphManager {

    private LinkedList<BezierPoint> points;
    private LinkedList<BezierLine> lines;
    private LinkedList<BezierPoint> midpoints;

    private int prevSize = 0;

    private boolean renderMidpoints = true;

    Path2D.Double m_totalPath;

    //TODO ADD CODE THAT RETURNS THE STRUCTURE AS ONE LARGE SHAPE

    public BezierGraphManager(){
        points = new LinkedList<BezierPoint>();
        lines = new LinkedList<BezierLine>();
        midpoints = new LinkedList<BezierPoint>();
        prevSize = points.size();
    }

    public void tick(){
        if(prevSize != points.size()){
            System.out.println("Invoked");
            redrawPath();
        }
        prevSize = points.size();
    }

    public void render(Graphics g){
        for(BezierLine temp : lines){
            temp.render(g);
        }
        for(BezierPoint temp : points){
            temp.render(g);
        }
        if(renderMidpoints){
            for(BezierPoint temp : midpoints){
                temp.render(g);
            }
        }
    }

    public void redrawPath(){
        if(points.size() > 1){
            for(int i = 0; i < points.size() - 1; i++){
                // BezierLine temp = new BezierLine(points.get(i), points.get(i + 1), this);
                lines.add(new BezierLine(points.get(i), points.get(i + 1), this));
            }
        }
    }

    //ADD MUCH MORE LOGIC
    public void addPoint(BezierPoint p){
        assert !p.getID().equals(BezierID.MIDPOINT);
        points.add(p);
    }

    public void addLine(BezierLine l){
        lines.add(l);
    }

    public void addMidpoint(BezierPoint p){
        assert p.getID().equals(BezierID.MIDPOINT);
        midpoints.add(p);
    }

    public void removePoint(BezierPoint p){
        assert !p.getID().equals(BezierID.MIDPOINT);
        points.remove(p);
    }

    public void removeLine(BezierLine l){
        l.deconstruct();
        lines.remove(l);
    }

    public void removeMidpoint(BezierPoint p){
        assert p.getID().equals(BezierID.MIDPOINT);
        midpoints.remove(p);
    }

    public boolean getMidpointRender(){
        return renderMidpoints;
    }

    public void setMidpointRender(boolean set){
        renderMidpoints = set;
    }
    
}
