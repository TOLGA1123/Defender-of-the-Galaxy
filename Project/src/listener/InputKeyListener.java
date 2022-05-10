package listener;
import java.awt.event.*;
import main.ConstantsForScenes;
import main.MainGame;

public class InputKeyListener implements KeyListener{

    private MainGame mainGame;
    public InputKeyListener(MainGame mainGame) {
        this.mainGame = mainGame;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_D){ //Trying changing scenes
            ConstantsForScenes.constantsForScenes = ConstantsForScenes.PLAY;
        }
        
    }
    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
}
