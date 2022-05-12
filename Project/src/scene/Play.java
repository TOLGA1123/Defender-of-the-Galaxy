package scene;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.nio.channels.AcceptPendingException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Color;
import buttons.Bar;
import enemy.Enemy;
import extras.Data;
import extras.SaveAndLoad;
import handlers.DefenderHandler;
import handlers.EnemyHandler;
import handlers.ProjectileHandler;
import handlers.WaveHandler;
import main.MainGame;
import placeable.Defender;
import placeable.EnterExitLoc;
import extras.Constant.TileCheckConstants;
import extras.Constant.TileCheckConstants.*;

public class Play extends SceneParent implements SceneInterface{
    
    private Bar controlBar = new Bar(0, 800, 160, 960, this);
    private EnemyHandler enemyHandler;
    private DefenderHandler defenderHandler;
    private ProjectileHandler projectileHandler;
    private EnterExitLoc enter;
    private EnterExitLoc exit;
    private int mouseLocX;
    private int mouseLocY; 
    private int tick;
    private int[][] levelData = Data.data();

    private WaveHandler waveHandler;
    private boolean pause = false;
    private Defender selectedDefender;

    public Play(MainGame mainGame) {
        super(mainGame);
        levelData = SaveAndLoad.levelData("idle");
        ArrayList<EnterExitLoc> enterAndExit = SaveAndLoad.levelEnterExitLoc("idle");
        this.enter = enterAndExit.get(0);
        this.exit = enterAndExit.get(1);
        this.enemyHandler = new EnemyHandler(this, enter, exit);
        this.defenderHandler = new DefenderHandler(this);
        this.projectileHandler = new ProjectileHandler(this);

        this.waveHandler = new WaveHandler(this);
    }
    public void updateGame(){
        if (!pause)
        {
            tick++;
            if (tick % (60*3) == 0) // 3 mean every 3 second in 60*3 60 çarpı üçteki üç 3 sn demek
            {
                controlBar.addMoney(1); // 1 mean add 1 money
            }

            waveHandler.updateGame();

            if(allEnemiesDead()){
                if(isThereMoreWaves()){
                    waveHandler.initWaveTimer();
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
            defenderHandler.updateGame();
            projectileHandler.updateGame();
        }
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
    public void enemyPassedDefenders(){
        controlBar.enemyPassedDefenders();
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
        defenderHandler.render(g);
        drawSelectedDefender(g);
        drawWaveInfo(g);
        drawHighlight(g);
        projectileHandler.render(g);
    }
    private void drawHighlight(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawRect(mouseLocX,mouseLocY,32,32);
    }
    private void drawSelectedDefender(Graphics g) {
        if(selectedDefender != null){
            g.drawImage(defenderHandler.getDefenderImages().get(selectedDefender.getDefenderType()), mouseLocX, mouseLocY, null);
        }
    }
    private void drawWaveInfo(Graphics g) {
        
    }
    @Override
    public void click(int mouseXLoc, int mouseYLoc) {
        if (mouseYLoc >= 800){
            controlBar.click(mouseXLoc, mouseYLoc);
        }
        else{
            if(selectedDefender!=null){
                if(isTileNotPath(mouseLocX,mouseLocY)){
                    if(getDefenderAt(mouseLocX, mouseLocY)==null){
                        defenderHandler.addDefender(selectedDefender, mouseLocX,mouseLocY); // global variables mouseLocX mouseLocY!!!!! not parameter
                        removeGold(selectedDefender.getDefenderType());
                        selectedDefender = null;
                    }
                }
            }
            else{
                Defender def = getDefenderAt(mouseLocX, mouseLocY);
                controlBar.displayDefender(def);
            }
        }
        
    }
    private Defender getDefenderAt(int x, int y) {
        return defenderHandler.getDefenderAt(x,y);
    }
    private boolean isTileNotPath(int x, int y) {
        int id = levelData[y/32][x/32];
        int tileType = mainGame.getTileHandler().getTileWithId(id).getTypeOfTile();
        if(tileType != TileCheckConstants.PATH ){
            return true;
        }
        return false;
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
    public EnemyHandler getEnemyHandler(){
        return enemyHandler;
    }
    public void setSelectedDefender(Defender selectedDefender) {
        this.selectedDefender = selectedDefender;
    }
    public DefenderHandler getDefenderHandler() {
        return defenderHandler;
    }
    public void shootEnemy(Defender def, Enemy enemy) {
        projectileHandler.newProjectile(def,enemy);
    }
    public void pause(boolean pause)
    {
        this.pause = pause;
    }       
    public boolean getPause()
    {
        return pause;
    }   
    private void removeGold(int DefenderType) 
    {
        controlBar.payForDefender(DefenderType);
    }
	public void rewardPlayer(int enemyType) 
    {
        controlBar.addMoney(extras.Constant.EnemyConstants.getEnemyLoot(enemyType));
	}
    class GoldTicker extends TimerTask {
        public void run() {
           controlBar.addMoney(1);; 
        }
    }
    public void removeDefender(Defender displayedDefender)
    {
        defenderHandler.removeDefender(displayedDefender);
    }
	public void upgradeDefender(Defender displayedDefender) {
        if(controlBar.money > 0){
            defenderHandler.upgradeDefender(displayedDefender);    
        }
	}
    public void resetGameState() {
        controlBar.resetGameState();
        mouseLocX = 0; mouseLocY = 0; tick = 0;
        pause = false;
        selectedDefender = null;
        enemyHandler.enemies.clear();
        defenderHandler.defenders.clear();
        defenderHandler.defenderCount = 0;
        projectileHandler.projectiles.clear();
        projectileHandler.explosions.clear();
        projectileHandler.projectileId = 0;
        waveHandler.waves.clear();
        waveHandler.createWaves();
        waveHandler.waveIndex = 0;
        waveHandler.enemyIndex = 0;
        waveHandler.initWaveTimer = false;
        waveHandler.isTimerOverWave = false;
        waveHandler.waveInit = 0;
        waveHandler.enemyInit = waveHandler.enemyInitLimit;
    }

}
