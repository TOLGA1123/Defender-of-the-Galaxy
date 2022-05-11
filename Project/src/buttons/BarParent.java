package buttons;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import extras.SaveAndLoad;

public class BarParent {
    public Rectangle barSize;
    public int x;
    public int y;
    public int w;
    public int h;
    public BarParent(int x, int  y, int h, int w){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.barSize = new Rectangle(x, y ,w ,h);
        initBarDesign();
    }
    public static BufferedImage initBarDesign(){
        BufferedImage image = null;
        InputStream inputStream = SaveAndLoad.class.getClassLoader().getResourceAsStream("bar.png");
        try {
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    public static BufferedImage initBarPlayDesign(){
        BufferedImage image = null;
        InputStream inputStream = SaveAndLoad.class.getClassLoader().getResourceAsStream("barPlay.png");
        try {
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
