package src.Graphical.BezierGraphical;

import java.util.LinkedList;
import java.awt.Graphics;

public class BezierGraphManager {

    private LinkedList<BezierPoint> points;
    private LinkedList<BezierLine> lines;

    private int prevSize = 0;

    //TODO ADD CODE THAT RETURNS THE STRUCTURE AS ONE LARGE SHAPE

    public BezierGraphManager(){
        points = new LinkedList<BezierPoint>();
        lines = new LinkedList<BezierLine>();
        prevSize = points.size();
    }

    public void tick(){
        // if(prevSize != points.size()){
        //     redrawPath();
        // }
        prevSize = points.size();
        System.out.println(prevSize);
    }

    public void render(Graphics g){
        for(BezierLine temp : lines){
            temp.render(g);
        }
        for(BezierPoint temp : points){
            temp.render(g);
        }
    }

    public void redrawPath(){
        if(points.size() > 1){
            for(int i = 0; i < points.size() - 1; i++){
                BezierLine temp = new BezierLine(points.get(i), points.get(i + 1), this);
                lines.add(temp);
            }
        }
    }

    //ADD MUCH MORE LOGIC
    public void addPoint(BezierPoint p){
        points.add(p);
    }

    public void addLine(BezierLine l){
        lines.add(l);
    }

    public void removePoint(BezierPoint p){
        points.remove(p);
    }

    public void removeLine(BezierLine l){
        l.deconstruct();
        lines.remove(l);
    }
    
}
