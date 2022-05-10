package scene;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;
import javax.imageio.ImageIO;
import buttons.Button;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import main.MainGame;
import static main.ConstantsForScenes.*;

public class Settings extends SceneParent implements SceneInterface{
    Random rand = new Random();
    BufferedImage image;
    Button menu;

    public Settings(MainGame mainGame) {
        super(mainGame);
        this.imageLoad();
        this.menu = new Button(20, 20, 64, 128, "/menu.png", "/menu_over.png", "/menu_pressed.png");
    }
    public void imageLoad(){
        InputStream inputStream = getClass().getResourceAsStream("/space_background.png");
        try {
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void click(int mouseXLoc, int mouseYLoc) {
        if(menu.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            set(MAIN_MENU);
        }
    }
    @Override
    public void move(int mouseXLoc, int mouseYLoc) {
        menu.setMouseOver(false);
        if(menu.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            menu.setMouseOver(true);
        }
    }
    @Override
    public void press(int mouseXLoc, int mouseYLoc) {
        if(menu.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            menu.setPressed(true);
        }
    }
    @Override
    public void release(int mouseXLoc, int mouseYLoc) {
        menu.setPressed(false);
        menu.setMouseOver(false);
    }
    @Override
    public void render(Graphics g) {
        g.drawImage(image, 0, 0, null);
        menu.paintButton(g, menu.getButtonImage());        
    }
    @Override
    public void drag(int mouseXLoc, int mouseYLoc) {
        // TODO Auto-generated method stub
        
    }
}
