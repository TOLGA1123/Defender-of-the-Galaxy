package main;
import java.awt.Graphics;

public class Render {

    private MainGame mainGame;
    public Render(MainGame mainGame){
        this.mainGame = mainGame;
    }
    public void render(Graphics g){
        if(ConstantsForScenes.constantsForScenes == ConstantsForScenes.PLAY){
            mainGame.play.render(g);
        }
        else if(ConstantsForScenes.constantsForScenes == ConstantsForScenes.EDIT){
            mainGame.edit.render(g);
        }
        else if(ConstantsForScenes.constantsForScenes == ConstantsForScenes.SETTINGS){
            mainGame.settings.render(g);
        }
        else if(ConstantsForScenes.constantsForScenes == ConstantsForScenes.MAIN_MENU){
            mainGame.mainMenu.render(g);
        }
        else if(ConstantsForScenes.constantsForScenes == ConstantsForScenes.FINISH){
            mainGame.finish.render(g);
        }
    }
}
