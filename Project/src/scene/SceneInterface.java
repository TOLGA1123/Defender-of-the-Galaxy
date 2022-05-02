package scene;
import java.awt.Graphics;
public interface SceneInterface {
    void render(Graphics g);
    void click(int mouseXLoc, int mouseYLoc);
    void move(int mouseXLoc, int mouseYLoc);
    void press(int mouseXLoc, int mouseYLoc);
    void release(int mouseXLoc, int mouseYLoc);
    void drag(int mouseXLoc, int mouseYLoc);
}
