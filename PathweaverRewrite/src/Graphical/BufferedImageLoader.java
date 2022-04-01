package Graphical;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BufferedImageLoader {

    private BufferedImage img;

    public BufferedImage loadImage(String path){
        try {
            // System.out.println(Image);
            img = ImageIO.read(getClass().getResource(path));
            // img = ImageIO.read(new URL(path));
        }catch(IOException e){
            return null;
        }
        return img;
    }
}