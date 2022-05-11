package scene;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Point;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import buttons.Button;
import main.ConstantsForScenes;
import main.MainGame;

public class Finish extends SceneParent implements SceneInterface{

    private BufferedImage image;
    private Button menuButton = new Button(416, 308, 64, 128, "/menu.png", "/menu_over.png", "/menu_pressed.png");
    private Button quitButton = new Button(416, 436, 64, 128, "/quit_button.png", "/quit_over.png", "/quit_pressed.png");
    private Button replayButton = new Button(416, 564, 64, 128, "/play_button.png", "/play_button_over.png", "/play_button_pressed.png");
    public Finish(MainGame mainGame) {
        super(mainGame);
        imageLoad();
    }
    public void imageLoad(){
        InputStream inputStream = getClass().getResourceAsStream("/space_backgroundOver.png");
        try {
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void render(Graphics g) {
        g.drawImage(image, 0, 0, null);
        menuButton.paintButton(g, menuButton.getButtonImage());
        quitButton.paintButton(g, quitButton.getButtonImage());
        replayButton.paintButton(g, replayButton.getButtonImage());
    }

    @Override
    public void click(int mouseXLoc, int mouseYLoc) {
        if(menuButton.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            ConstantsForScenes.constantsForScenes = ConstantsForScenes.MAIN_MENU;
            mainGame.getPlay().resetGameState();
        }
        else if(quitButton.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            System.exit(0);
        }
        else if(replayButton.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            resetGameState();
        }
    }
    private void resetGameState() {
        mainGame.getPlay().resetGameState();
        ConstantsForScenes.constantsForScenes = ConstantsForScenes.PLAY;
    }
    @Override
    public void move(int mouseXLoc, int mouseYLoc) {
        menuButton.setMouseOver(false);
        quitButton.setMouseOver(false);
        replayButton.setMouseOver(false);
        if(menuButton.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            menuButton.setMouseOver(true);
        }
        else if(quitButton.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            quitButton.setMouseOver(true);
        }
        else if(replayButton.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            replayButton.setMouseOver(true);
        }
    }

    @Override
    public void press(int mouseXLoc, int mouseYLoc) {
        menuButton.setPressed(false);
        quitButton.setPressed(false);
        replayButton.setPressed(false);
        if(menuButton.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            menuButton.setPressed(true);
        }
        else if(quitButton.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            quitButton.setPressed(true);
        }
        else if(replayButton.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            replayButton.setPressed(true);
        }
    }

    @Override
    public void release(int mouseXLoc, int mouseYLoc) {
        menuButton.setMouseOver(false);
        menuButton.setPressed(false);
        quitButton.setMouseOver(false);
        quitButton.setPressed(false);
        replayButton.setMouseOver(false);
        replayButton.setPressed(false);
    }

    @Override
    public void drag(int mouseXLoc, int mouseYLoc) {
        // TODO Auto-generated method stub
        
    }
    
}
