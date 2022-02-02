package Graphical.BezierGraphical;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

/**
 * Class representative of a singular Bezier point (for graphing/rendering purposes)
 * DATE: 1/31/2022
 * @author Lance Hartman
 */
public class BezierPoint {
    
    protected static final int RADIUS = 5; //5px radius

    /**
     * Values for the x and y positions relative to the coordinate graph in meters
     */
    private int posX, posY;

    /**
     * Values for the x and y positions relative to the screen origin
     */
    private int screenX, screenY;

    /**
     * ID of the specific BezierPoint
     * @see BezierID
     */
    BezierID id;

    /**
     * @param posX X position (relative to coordinate grid) on field
     * @param posY Y position (relative to coordinate grid) on field
     * @param screenX X position (relative to screen origin) on screen
     * @param screenY Y position (relative to screen origin) on screen
     * @param id ID of the BezierPoint
     */
    public BezierPoint(int posX, int posY, int screenX, int screenY, BezierID id){
        this.posX = posX;
        this.posY = posY;
        this.screenX = screenX;
        this.screenY = screenY;
        this.id = id;
    }

    public void tick(){

    }

    public void render(Graphics g){
        if(id.equals(BezierID.START)){
            g.setColor(Color.RED);
        }else if(id.equals(BezierID.WAYPOINT)){
            g.setColor(Color.BLUE);
        }else if(id.equals(BezierID.END)){
            g.setColor(Color.GREEN);
        }else{
            g.setColor(Color.MAGENTA);
        }
        // Graphics2D g2d = (Graphics2D)g;
        // g2d.setStroke(new BasicStroke(10));
        g.fillOval(screenX - RADIUS, screenY - RADIUS, RADIUS * 2, RADIUS * 2);
    }

    public int getPosX(){
        return posX;
    }

    public int getPosY(){
        return posY;
    }

    public int getScreenX(){
        return screenX;
    }

    public int getScreenY(){
        return screenY;
    }

    public BezierID getID(){
        return id;
    }

}
