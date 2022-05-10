package scene;
import java.awt.Point;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import buttons.Button;
import handlers.TileHandler;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import static main.ConstantsForScenes.*;
import main.MainGame;

public class MainMenu extends SceneParent implements SceneInterface{
    Random rand = new Random();
    public static BufferedImage image;
    private TileHandler handler;
    private Button play = new Button(416, 288, 64, 128, "/play_button.png", "/play_button_over.png", "/play_button_pressed.png");
    private Button edit = new Button(416, 416, 64, 128, "/edit.png", "/edit_over.png", "/edit_pressed.png");
    private Button quit = new Button(416, 544, 64, 128, "/quit_button.png", "/quit_over.png", "/quit_pressed.png");
    private Button settings = new Button(20, 876, 64, 128, "/settings_button.png", "/settings_over.png", "/settings_pressed.png");
    private ArrayList<BufferedImage> sprites = new ArrayList<>();

    public MainMenu(MainGame mainGame){
        super(mainGame);
        this.imageLoad();
        this.addSpritestoArray();
    }
    private void addSpritestoArray(){
        for (int i = 0; i < 30; i++){
            for (int q = 0; q < 30; q++){
                sprites.add(image.getSubimage(32 * q, 32 * i, 32, 32)); //adds sprites to the array with measurements: 32 pixels heigth, 32 pixels width
            }
        }
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
    public void render(Graphics g) {
        g.drawImage(image, 0, 0, null);
        play.paintButton(g, play.getButtonImage());
        edit.paintButton(g, edit.getButtonImage());
        quit.paintButton(g, quit.getButtonImage());
        settings.paintButton(g, settings.getButtonImage());
    }
    @Override
    public void click(int mouseXLoc, int mouseYLoc) {
        if(play.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            set(PLAY);
        }
        else if (settings.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            set(SETTINGS);
        }
        else if (edit.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            set(EDIT);
        }  
        else if (quit.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            System.exit(0);
        }        
    }
    @Override
    public void move(int mouseXLoc, int mouseYLoc) {
        play.setMouseOver(false);
        edit.setMouseOver(false);
        settings.setMouseOver(false);
        quit.setMouseOver(false);
        if(play.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            play.setMouseOver(true);
        }
        else if(edit.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            edit.setMouseOver(true);
        }
        else if(settings.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            settings.setMouseOver(true);
        }  
        else if(quit.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            quit.setMouseOver(true);
        }    
    }
    @Override
    public void press(int mouseXLoc, int mouseYLoc) {
        play.setPressed(false);
        edit.setPressed(false);
        settings.setPressed(false);
        quit.setPressed(false);
        if(play.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            play.setPressed(true);
        }
        else if(edit.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            edit.setPressed(true);
        }
        else if(settings.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            settings.setPressed(true);
        } 
        else if(quit.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            quit.setPressed(true);
        }   
    }
    @Override
    public void release(int mouseXLoc, int mouseYLoc) {
        play.setPressed(false);
        play.setMouseOver(false);
        edit.setPressed(false);
        edit.setMouseOver(false);
        settings.setPressed(false);
        settings.setMouseOver(false);
        quit.setPressed(false);
        quit.setMouseOver(false);
    }
    @Override
    public void drag(int mouseXLoc, int mouseYLoc) {
        // TODO Auto-generated method stub
        
    }
    public TileHandler getTileHandler(){
        return this.handler;
    }
}
