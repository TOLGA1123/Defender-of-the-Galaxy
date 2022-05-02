package buttons;
import java.awt.Rectangle;
import scene.Play;
import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;
import static main.ConstantsForScenes.*;

public class Bar extends BarParent{
    
    private Button menu;
    private Play playGetHandler;
    private Rectangle barSize;
    public Bar(int x, int  y, int h, int w, Play play){
        super(x, y, h, w);
        this.barSize = super.barSize;
        this.menu = new Button(10, 810, 64, 128, "/menu.png", "/menu_over.png", "/menu_pressed.png");
        this.playGetHandler = play;
    }
    public void click(int mouseXLoc, int mouseYLoc) {
        if(menu.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            set(MAIN_MENU);
        }   
    }
    public void move(int mouseXLoc, int mouseYLoc) {
        menu.setMouseOver(false);
        if(menu.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            menu.setMouseOver(true);
        }
    }
    public void press(int mouseXLoc, int mouseYLoc) {
        if(menu.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            menu.setPressed(true);
        }
    }
    public void release(int mouseXLoc, int mouseYLoc) {
        menu.setPressed(false);
        menu.setMouseOver(false);
    }
    public void paintBar(Graphics g){
        g.setColor(Color.ORANGE);
        g.fillRect(x, y, w, h);

        menu.paintButton(g, menu.getButtonImage());
    }
}
