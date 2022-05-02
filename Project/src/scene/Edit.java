package scene;
import java.awt.Graphics;
import buttons.BarPlay;
import extras.Data;
import extras.SaveAndLoad;
import main.MainGame;
import placeable.Tile;

public class Edit extends SceneParent implements SceneInterface{
    private MainGame mainGame;
    private BarPlay controlBar = new BarPlay(0, 800, 160, 960, this);
    private Tile selected;
    private boolean drawTileChecker = false;
    private int mouseLocX;
    private int mouseLocY; 
    private int[][] levelData = Data.data();
    
    public Edit(MainGame mainGame) {
        super(mainGame);
        this.mainGame = mainGame;
        defaultLevel();
        levelData = SaveAndLoad.levelData("idle");
    }
    public void defaultLevel(){
        levelData = SaveAndLoad.levelData("idle");
    }
    public void save(){
        SaveAndLoad.save(levelData, "idle");
        mainGame.getPlay().currentLevel(levelData);
    }
    private void replace(int mouseLocX2, int mouseLocY2){
        if(selected != null){
            int xTile = mouseLocX2 / 32;
            int yTile = mouseLocY2 / 32;

            levelData[yTile][xTile] = selected.getId();
        }
    }
    public void moveMouse(int mouseXLoc, int mouseYLoc) {
        if (mouseYLoc >= 800){
            controlBar.move(mouseXLoc, mouseYLoc);
            drawTileChecker = false;
        }
        else{
            drawTileChecker = true;
            mouseLocX = mouseXLoc / 32;
            mouseLocY = mouseYLoc / 32;
            mouseLocX = mouseLocX * 32;
            mouseLocY = mouseLocY * 32;
        }        
    }
    private void drawSelectedTile(Graphics g) {
        if (selected != null && drawTileChecker){
            g.drawImage(selected.getImage(), mouseLocX, mouseLocY, 32, 32,null);
        }
    }
    public void setSelected(Tile selected) {
        this.selected = selected;
        drawTileChecker = true;
    }
    @Override
    public void render(Graphics g) {
        for (int i = 0; i < levelData.length; i++){
            for (int q = 0; q < levelData[i].length; q++){
                g.drawImage(MainGame.handler.getTile(levelData[i][q]), q * 32, i * 32, null);
            }
        }
        controlBar.paintBar(g);
        drawSelectedTile(g);        
    }

    @Override
    public void click(int mouseXLoc, int mouseYLoc) {
        if (mouseYLoc >= 800){
            controlBar.click(mouseXLoc, mouseYLoc);
        }
        else{
            replace(mouseLocX, mouseLocY);
        }
    }

    @Override
    public void move(int mouseXLoc, int mouseYLoc) {
        if (mouseYLoc >= 800){
            controlBar.move(mouseXLoc, mouseYLoc);
            drawTileChecker = false;
        }
        else{
            drawTileChecker = true;
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
    public void drag(int mouseXLoc, int mouseYLoc) {
        if (mouseYLoc >= 800){
        }
        else{
            replace(mouseXLoc, mouseYLoc);
        }
    }

}