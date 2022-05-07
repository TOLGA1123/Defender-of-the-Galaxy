package buttons;
import java.awt.Rectangle;

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
       
    }
    
}
