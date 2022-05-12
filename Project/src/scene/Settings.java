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
    MainGame mainGame;
    BufferedImage image;
    BufferedImage imageMuted;
    BufferedImage imagePlaying;
    BufferedImage muteOrPlayImage;
    static int count = 0;
    Button menu;
    Button mute;

    public Settings(MainGame mainGame) {
        super(mainGame);
        this.mainGame = mainGame;
        this.imageLoad();
        this.imagePlayLoad();
        this.imageMuteLoad();
        this.menu = new Button(20, 20, 64, 128, "/menu.png", "/menu_over.png", "/menu_pressed.png");
        this.mute = new Button(320, 416, 96, 160, "/mute.png", "/mute_over.png", "/mute_pressed.png");
    }
    public void imageLoad(){
        InputStream inputStream = getClass().getResourceAsStream("/space_background.png");
        try {
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void imagePlayLoad(){
        InputStream inputStream = getClass().getResourceAsStream("/playing.png");
        try {
            imagePlaying = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void imageMuteLoad(){
        InputStream inputStream = getClass().getResourceAsStream("/muted.png");
        try {
            imageMuted = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void click(int mouseXLoc, int mouseYLoc) {
        if(menu.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            set(MAIN_MENU);
        }
        else if(mute.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            count++;
            if(count%2 == 0){
                muteOrPlayImage = imagePlaying;
                mainGame.current.play();
            }
            else{
                muteOrPlayImage = imageMuted;
                mainGame.current.stop();
            } 
        }
    }
    @Override
    public void move(int mouseXLoc, int mouseYLoc) {
        menu.setMouseOver(false);
        mute.setMouseOver(false);
        if(menu.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            menu.setMouseOver(true);
        }
        else if(mute.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            mute.setMouseOver(true);
        }
    }
    @Override
    public void press(int mouseXLoc, int mouseYLoc) {
        if(menu.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            menu.setPressed(true);
        }
        else if(mute.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            mute.setPressed(true);
        }
    }
    @Override
    public void release(int mouseXLoc, int mouseYLoc) {
        menu.setPressed(false);
        menu.setMouseOver(false);
        mute.setPressed(false);
        mute.setMouseOver(false);
    }
    @Override
    public void render(Graphics g) {
        g.drawImage(image, 0, 0, null);
        menu.paintButton(g, menu.getButtonImage());   
        mute.paintButton(g, mute.getButtonImage());   
        g.drawImage(muteOrPlayImage, 480, 416, null);     
    }
    @Override
    public void drag(int mouseXLoc, int mouseYLoc) {
        // TODO Auto-generated method stub
        
    }
}
