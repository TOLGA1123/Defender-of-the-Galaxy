package scene;

import java.awt.Graphics;
import buttons.Button;
import main.MainGame;

public class Finish extends SceneParent implements SceneInterface{

    private Button menuButton = new Button(416, 288, 64, 128, "/menu.png", "/menu_over.png", "/menu_pressed.png");
    private Button quitButton = new Button(416, 416, 64, 128, "/quit_button.png", "/quit_over.png", "/quit_pressed.png");
    private Button replayButton = new Button(416, 544, 64, 128, "/play_button.png", "play_buton_over.png", "play_button_pressed.png");
    public Finish(MainGame mainGame) {
        super(mainGame);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(MainMenu.image, 0, 0, null);
        menuButton.paintButton(g, menuButton.getButtonImage());
        quitButton.paintButton(g, quitButton.getButtonImage());
        replayButton.paintButton(g, replayButton.getButtonImage());
    }

    @Override
    public void click(int mouseXLoc, int mouseYLoc) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void move(int mouseXLoc, int mouseYLoc) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void press(int mouseXLoc, int mouseYLoc) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void release(int mouseXLoc, int mouseYLoc) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void drag(int mouseXLoc, int mouseYLoc) {
        // TODO Auto-generated method stub
        
    }
    
}
