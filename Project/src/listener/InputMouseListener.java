package listener;
import java.awt.event.*;
import java.awt.event.MouseEvent;

import main.ConstantsForScenes;
import main.MainGame;

public class InputMouseListener implements MouseMotionListener, MouseListener{

    private MainGame mainGame;
    public InputMouseListener(MainGame mainGame) {
        this.mainGame = mainGame;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1){
            if(ConstantsForScenes.constantsForScenes == ConstantsForScenes.MAIN_MENU){
                mainGame.getMainMenu().click(e.getX(), e.getY());
            }
            else if (ConstantsForScenes.constantsForScenes == ConstantsForScenes.PLAY){
                mainGame.getPlay().click(e.getX(), e.getY());
            }
            else if (ConstantsForScenes.constantsForScenes == ConstantsForScenes.EDIT){
                mainGame.getEdit().click(e.getX(), e.getY());
            }
            else if (ConstantsForScenes.constantsForScenes == ConstantsForScenes.SETTINGS){
                mainGame.getSettings().click(e.getX(), e.getY());
            }
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {
        if(ConstantsForScenes.constantsForScenes == ConstantsForScenes.MAIN_MENU){
            mainGame.getMainMenu().press(e.getX(), e.getY());
        }
        else if (ConstantsForScenes.constantsForScenes == ConstantsForScenes.PLAY){
            mainGame.getPlay().press(e.getX(), e.getY());
        }
        else if (ConstantsForScenes.constantsForScenes == ConstantsForScenes.EDIT){
            mainGame.getEdit().press(e.getX(), e.getY());
        }
        else if (ConstantsForScenes.constantsForScenes == ConstantsForScenes.SETTINGS){
            mainGame.getSettings().press(e.getX(), e.getY());
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        if(ConstantsForScenes.constantsForScenes == ConstantsForScenes.MAIN_MENU){
            mainGame.getMainMenu().release(e.getX(), e.getY());
        }
        else if (ConstantsForScenes.constantsForScenes == ConstantsForScenes.PLAY){
            mainGame.getPlay().release(e.getX(), e.getY());
        }
        else if (ConstantsForScenes.constantsForScenes == ConstantsForScenes.EDIT){
            mainGame.getEdit().release(e.getX(), e.getY());
        }
        else if (ConstantsForScenes.constantsForScenes == ConstantsForScenes.SETTINGS){
            mainGame.getSettings().release(e.getX(), e.getY());
        }
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        if(ConstantsForScenes.constantsForScenes == ConstantsForScenes.MAIN_MENU){
            mainGame.getMainMenu().drag(e.getX(), e.getY());
        }
        else if (ConstantsForScenes.constantsForScenes == ConstantsForScenes.PLAY){
            mainGame.getPlay().drag(e.getX(), e.getY());
        }
        else if (ConstantsForScenes.constantsForScenes == ConstantsForScenes.EDIT){
            mainGame.getEdit().drag(e.getX(), e.getY());
        }
        else if (ConstantsForScenes.constantsForScenes == ConstantsForScenes.SETTINGS){
            mainGame.getSettings().drag(e.getX(), e.getY());
        }
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        if(ConstantsForScenes.constantsForScenes == ConstantsForScenes.MAIN_MENU){
            mainGame.getMainMenu().move(e.getX(), e.getY());
        }
        else if (ConstantsForScenes.constantsForScenes == ConstantsForScenes.PLAY){
            mainGame.getPlay().move(e.getX(), e.getY());
        }
        else if (ConstantsForScenes.constantsForScenes == ConstantsForScenes.EDIT){
            mainGame.getEdit().move(e.getX(), e.getY());
        }
        else if (ConstantsForScenes.constantsForScenes == ConstantsForScenes.SETTINGS){
            mainGame.getSettings().move(e.getX(), e.getY());
        }
    }
}
