
import Graphical.BufferedImageLoader;
import Graphical.GraphicalInterface;
import Graphical.MouseInput;
import Graphical.BezierGraphical.BezierGraphManager;
import Graphical.KeyInput;

//GUI STUFF
import java.awt.Canvas;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.Dimension;

//IO STUFF
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

/**
 * DESCRIPTION: This class is simply used to combine all other components
 * of the Pathweaver Rewrite.
 * Starts its own Thread and runs at the same time as the robot process.
 * Graphical interface runs with a JFrame and a MouseListener.
 * Graphical interface is updated with information provided by the gyro.
 * 
 * DATE: 1/31/2022
 * @author Lance Hartman
 */
public class Pathweaver extends Canvas implements Runnable{

    /**
     * List of general variables
     */
    // public static final int REAL_WIDTH = 816, REAL_HEIGHT = 839;
    // public static final int WIDTH = 800, HEIGHT = 800;
    public String name = "Pathweaver";

    /**
     * List of pathweaver objects
     */
    private MouseInput m_mouseInput;
    private KeyInput m_keyInput;
    private GraphicalInterface m_graphicalInterface;
    private BezierGraphManager m_bezierGraphManager;
    private BufferedImageLoader m_bufferedImageLoader = new BufferedImageLoader();

    /**
     * Images
     */
    private BufferedImage m_fieldImage;
    Thread loadFlag = new Thread(() -> m_fieldImage = m_bufferedImageLoader.loadImage("/flag.png"));
    Dimension fieldMin = new Dimension(0, 0);
    Dimension fieldMax = new Dimension(1, 1);


    /**
     * Other info
     */
    private int frames = -1;

    /**
     * Thread information
     */
    boolean isRunning = false;
    Thread running;


    /**
     * Class to construct a new Pathweaver window with the following robot components:
     */
    public Pathweaver(){
        m_graphicalInterface = new GraphicalInterface(name, this);
        running = new Thread(this);
        init();
        start();
    }

    public synchronized void start(){
        if(isRunning) return;
        running.start();
        isRunning = true;
    }

    public synchronized void stop(){
        if(!isRunning) return;
        try{
            running.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        isRunning = false;
    }

    public void init(){
        m_bezierGraphManager = new BezierGraphManager();
        m_keyInput = new KeyInput();

         //TODO ESTABLISH X AND Y USING IMAGE.X, IMAGE.y
        m_mouseInput = new MouseInput(m_bezierGraphManager, m_keyInput, 
        new Dimension(0, 0), new Dimension(m_graphicalInterface.getFrameWidth(), 
        m_graphicalInterface.getFrameHeight()));

        this.addKeyListener(m_keyInput);
        this.addMouseListener(m_mouseInput);
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        frames = 0;
        while(isRunning){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                frames = 0;
                updates = 0;
            }
        }
    }

    public void tick(){
        m_bezierGraphManager.tick();
    }

    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            bs = this.getBufferStrategy();
        }
        Graphics g = bs.getDrawGraphics();

        /*RENDER BETWEEN HERE*/

        //background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, m_graphicalInterface.getFrameWidth(), m_graphicalInterface.getFrameHeight());

        m_bezierGraphManager.render(g);
        // g.drawImage(m_fieldImage,
        //             0, 
        //             0, 
        //             m_fieldImage.getWidth(), 
        //             m_fieldImage.getHeight(), 
        //             0, 
        //             0, 
        //             m_fieldImage.getWidth(), 
        //             m_fieldImage.getHeight(), 
        //             Color.WHITE, 
        //             this);

        /*AND HERE*/

        bs.show();
        g.dispose();
    }

    public int getFrames(){
        return frames;
    }

}
