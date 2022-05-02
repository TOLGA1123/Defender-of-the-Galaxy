package scene;
import java.awt.Graphics;
import buttons.Bar;
import extras.Data;
import extras.SaveAndLoad;
import main.MainGame;

public class Play extends SceneParent implements SceneInterface{
    
    private Bar controlBar = new Bar(0, 800, 160, 960, this);
    private int mouseLocX;
    private int mouseLocY; 
    private int[][] levelData = Data.data();

    public Play(MainGame mainGame) {
        super(mainGame);
        levelData = SaveAndLoad.levelData("idle");
    }
    public void currentLevel(int[][] levelData){
        this.levelData = levelData;
    }
    public void moveMouse(int mouseXLoc, int mouseYLoc) {
        if (mouseYLoc >= 800){
            controlBar.move(mouseXLoc, mouseYLoc);
        }
        else{
            mouseLocX = mouseXLoc / 32;
            mouseLocY = mouseYLoc / 32;
            mouseLocX = mouseLocX * 32;
            mouseLocY = mouseLocY * 32;
        }        
    }
    @Override
    public void render(Graphics g) {
        for (int i = 0; i < levelData.length; i++){
            for (int q = 0; q < levelData[i].length; q++){
                g.drawImage(MainGame.handler.getTile(levelData[i][q]), q * 32, i * 32, null);
            }
        }
        controlBar.paintBar(g);
    }
    @Override
    public void click(int mouseXLoc, int mouseYLoc) {
        if (mouseYLoc >= 800){
            controlBar.click(mouseXLoc, mouseYLoc);
        }
    }
    @Override
    public void move(int mouseXLoc, int mouseYLoc) {
        if (mouseYLoc >= 800){
            controlBar.move(mouseXLoc, mouseYLoc);
        }
        else{
            mouseLocX = mouseXLoc / 32;
            mouseLocY = mouseYLoc / 32;
            mouseLocX = mouseLocX * 32;
            mouseLocY = mouseLocY * 32;
        }        
    }
    @Override
    public void press(int mouseXLoc, int mouseYLoc) {
        if (mouseYLoc >= 800){
            controlBar.press(mouseXLoc, mouseYLoc);
        }
    }
    @Override
    public void release(int mouseXLoc, int mouseYLoc) {
        controlBar.release(mouseXLoc, mouseYLoc);
    }
    @Override
    public void drag(int mouseXLoc, int mouseYLoc){
        
    }
}
