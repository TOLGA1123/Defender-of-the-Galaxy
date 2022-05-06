package scene;
import java.awt.Graphics;
import java.util.ArrayList;

import buttons.Bar;
import extras.Data;
import extras.SaveAndLoad;
import handlers.EnemyHandler;
import handlers.WaveHandler;
import main.MainGame;
import placeable.EnterExitLoc;

public class Play extends SceneParent implements SceneInterface{
    
    private Bar controlBar = new Bar(0, 800, 160, 960, this);
    private EnemyHandler enemyHandler;
    private EnterExitLoc enter;
    private EnterExitLoc exit;
    private int mouseLocX;
    private int mouseLocY; 
    private int[][] levelData = Data.data();

    private WaveHandler waveHandler;

    public Play(MainGame mainGame) {
        super(mainGame);
        levelData = SaveAndLoad.levelData("idle");
        ArrayList<EnterExitLoc> enterAndExit = SaveAndLoad.levelEnterExitLoc("idle");
        this.enter = enterAndExit.get(0);
        this.exit = enterAndExit.get(1);
        this.enemyHandler = new EnemyHandler(this, enter, exit);


        this.waveHandler = new WaveHandler(this);
    }
    public void updateGame(){
        waveHandler.updateGame();

        if(allEnemiesDead()){
            if(isThereMoreWaves()){
                waveHandler.startWaveTimer();
                if(isWaveTimerOver()){
                    waveHandler.increaseWaveIndex();
                    enemyHandler.getEnemies().clear();
                    waveHandler.resetEnemyIndex();
                }
            }
        }
        if(isTimeForNewEnemy()){
            spawnEnemy();
        }

        enemyHandler.updateGame();
    }
    private boolean isWaveTimerOver() {
        return waveHandler.isWaveTimerOver();
    }
    private boolean isThereMoreWaves() {
        return waveHandler.isThereMoreWaves();
    }
    private boolean allEnemiesDead() {
        if(waveHandler.isThereMoreEnemiesInWave()){ return false; }
        for(int i = 0; i< enemyHandler.getEnemies().size(); i++ ){
            if(enemyHandler.getEnemies().get(i).isAlive()){
                return false;
            }
        }
        return true;
    }
    private void spawnEnemy() {
        enemyHandler.spawnEnemy(this.waveHandler.getNextEnemy());
    }
    private boolean isTimeForNewEnemy() {
        if(this.waveHandler.isTimeForNewEnemy()){
            if(this.waveHandler.isThereMoreEnemiesInWave()){
                return true;
            }
        }
        return false;
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
        enemyHandler.render(g);

        drawWaveInfo(g);
    }
    private void drawWaveInfo(Graphics g) {
        if(waveHandler.isWaveTimerStarted()){
            double timeRemaining = waveHandler.getRemainingTime();
            g.drawString("Time remaining: " + timeRemaining, 300, 800);
        }
    }
    @Override
    public void click(int mouseXLoc, int mouseYLoc) {
        if (mouseYLoc >= 800){
            controlBar.click(mouseXLoc, mouseYLoc);
        }
        // else{
        //     enemyHandler.insertNewEnemy(mouseXLoc, mouseYLoc);
        // }
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
    public int getNewPosTileType(int checkX, int checkY){
        int correctX = checkX / 32;
        int correctY = checkY / 32;
        if(correctX > 29 || correctX < 0){
            return 0;
        }
        if(correctY > 24 || correctY < 0){
            return 0;
        }
        return MainGame.handler.getTileWithId(levelData[checkY / 32][checkX / 32]).getTypeOfTile();
    }
    public WaveHandler getWaveHandler(){
        return waveHandler;
    }
}
