package Graphical.BezierGraphical;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.lang.ClassCastException;

import Graphical.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.AffineTransform;

public class BezierGraphManager {

    private LinkedList<BezierPoint> points;
    private LinkedList<BezierLine> lines;
    private LinkedList<BezierPoint> midpoints;

    private boolean hasFinalPath = false;

    private int prevSize = 0;

    Path2D.Double m_totalPath;
    PathIterator pi;

    private KeyInput m_keyInput;

    //TODO ADD CODE THAT RETURNS THE STRUCTURE AS ONE LARGE SHAPE

    public BezierGraphManager(KeyInput m_keyInput){
        this.m_keyInput = m_keyInput;
        points = new LinkedList<BezierPoint>();
        lines = new LinkedList<BezierLine>();
        midpoints = new LinkedList<BezierPoint>();
        prevSize = points.size();
        m_totalPath = new Path2D.Double();
    }

    public void tick(){
        if(prevSize != points.size()){
            redrawPath();
        }
        prevSize = points.size();
        if(midpoints.size() != lines.size()){
            System.out.println("ERROR WITH COUNT");
        }
    }

    public void render(Graphics g){
        for(BezierLine temp : lines){
            temp.render(g);
        }
        for(BezierPoint temp : points){
            temp.render(g);
        }
        if(m_keyInput.getMidpointsToggled()){
            for(BezierPoint temp : midpoints){
                temp.render(g);
            }
        }
    }

    public void redrawPath(){
        LinkedList<BezierPoint> tempMidpoints = new LinkedList<BezierPoint>();
        for(int i = 0; i < lines.size(); i++){
            tempMidpoints.add(lines.get(i).getMidpoint());
            lines.get(i).deconstruct();
        }
        lines.clear();
        if(points.size() > 1){
            for(int i = 0; i < points.size() - 1; i++){
                try{
                    lines.add(new BezierLine(points.get(i), points.get(i + 1), tempMidpoints.get(i), this));
                }catch(IndexOutOfBoundsException ignored){
                    lines.add(new BezierLine(points.get(i), points.get(i + 1), this));
                }
            }
        }
    }

    public boolean hasStart(){
        try{
            return points.getFirst().getID().equals(BezierID.START);
        }catch(NoSuchElementException ignored){
            return false;
        }
    }

    public boolean hasEnd(){
        try{
            return points.getLast().getID().equals(BezierID.END);
        }catch(NoSuchElementException ignored){
            return false;
        }
    }

    public Path2D.Double generateFullScreenPath(){
        if(!(hasStart() && hasEnd())){
            return null;
        }else{
            Path2D.Double ret = new Path2D.Double();
            ret.moveTo(points.getFirst().getScreenX(), points.getFirst().getScreenY());
            for(BezierLine line : lines){
                ret.append(line.getPath(), true);
            }
            return ret;
        }
    }

    public Path2D.Double generateFullMathematicalPath(){
        return null;
    }

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

    public LinkedList<BezierPoint> getMidpoints(){
        return midpoints;
    }

    public LinkedList<BezierLine> getLines(){
        return lines;
    }

    public boolean hasFinalPath(){
        return hasFinalPath;
    }
    
}
