package scene;
import java.awt.Graphics;
import java.util.ArrayList;
import buttons.BarPlay;
import extras.Data;
import extras.SaveAndLoad;
import extras.Constant.TileCheckConstants;
import handlers.TileHandler;
import main.MainGame;
import placeable.EnterExitLoc;
import placeable.Tile;

public class Edit extends SceneParent implements SceneInterface{
    private MainGame mainGame;
    private BarPlay controlBar = new BarPlay(0, 800, 160, 960, this);
    private EnterExitLoc enter;
    private EnterExitLoc exit;
    private Tile selected;
    private TileHandler handler = new TileHandler();
    private boolean drawTileChecker = false;
    private int mouseLocX;
    private int mouseLocY; 
    private int[][] levelData = Data.data();
    
    public Edit(MainGame mainGame) {
        super(mainGame);
        this.mainGame = mainGame;
        defaultLevel();
        //levelData = SaveAndLoad.levelData("idle");
    }
    public void defaultLevel(){
        levelData = SaveAndLoad.levelData("idle");
        ArrayList<EnterExitLoc> enterAndExit = SaveAndLoad.levelEnterExitLoc("idle");
        enter = enterAndExit.get(0);
        exit = enterAndExit.get(1);
    }
    public void save(){
        SaveAndLoad.save(levelData, "idle", enter, exit);
        mainGame.getPlay().currentLevel(levelData);
    }
    private void replace(int mouseLocX2, int mouseLocY2){
        int xTile = mouseLocX2 / 32;
        int yTile = mouseLocY2 / 32;
        if(selected.getId() >= 0){
            if(selected != null){
                levelData[yTile][xTile] = selected.getId();
            }    
        }
        else{
            if(MainGame.handler.getTileWithId(levelData[yTile][xTile]).getTypeOfTile() == TileCheckConstants.PATH){
                if(selected.getId() == -2){
                    exit = new EnterExitLoc(xTile, yTile);
                }
                else if(selected.getId() == -1){
                    enter = new EnterExitLoc(xTile, yTile);
                }
            }
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
        if(enter != null){g.drawImage(handler.getAllSprites().getSubimage(32, 96, 32, 32), 32 * enter.getLocX(), 32 * enter.getLocY(), 32, 32, null);}
        if(exit != null){g.drawImage(handler.getAllSprites().getSubimage(64, 96, 32, 32), 32 * exit.getLocX(), 32 * exit.getLocY(), 32, 32, null);}    
    }

    @Override
    public void click(int mouseXLoc, int mouseYLoc) {
        if (mouseYLoc >= 800){
            controlBar.click(mouseXLoc, mouseYLoc);
        }
        else{
            replace(mouseXLoc, mouseYLoc);
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