package handlers;
import scene.Play;
import java.awt.Graphics;
import java.util.ArrayList;
import enemy.Enemy;
import extras.SaveAndLoad;
import java.awt.image.BufferedImage;
import static extras.Constant.*;

public class EnemyHandler {

    private Play play;
    private double enemyChangeSpeed = 0.5;
    private ArrayList<BufferedImage> enemyImages = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    public EnemyHandler(Play play){
        this.play = play;
        this.insertNewEnemy(32 * 3, 32 * 13);
        loadEnemySprites();
    }
    public void updateGame(){
        for(int i = 0; i < enemies.size(); i++){
            //this.enemies.get(i).changeLoc(0.5, 0);
            if(isNextRoad(this.enemies.get(i))){
                
            }
        } 
    }
    private boolean isNextRoad(Enemy enemy){
        int checkX = (int)(enemy.getX() + getXChange(enemy.getLastDirection()));
        int checkY = (int)(enemy.getY() + getYChange(enemy.getLastDirection()));
        if(getNewPosTileType(checkX, checkY) == TileCheckConstants.PATH){
            enemy.changeLoc(enemyChangeSpeed, enemy.getLastDirection());
        }
        else if(isEnd(enemy)){

        }
        else{
            newDirection(enemy);
        }
        return false;
    }
    private int getNewPosTileType(int checkX, int checkY) {
        return play.getNewPosTileType(checkX, checkY);
    }
    private boolean isEnd(Enemy enemy) {
        return false;
    }
    private void newDirection(Enemy enemy){
        int direction = enemy.getLastDirection();
        int locOfEnemyX = (int)(enemy.getX() / 32);
        int locOfEnemyY = (int)(enemy.getY() / 32);
        if(direction == DirectionOfEnemy.RIGHT){
            if(locOfEnemyX < 29) {locOfEnemyX++;}
        }
        // else if(direction == DirectionOfEnemy.LEFT){
        //     if(locOfEnemyX > 0) {locOfEnemyX--;}
        // }
        // else if(direction == DirectionOfEnemy.UP){
        //     if(locOfEnemyY > 0) {locOfEnemyY--;}
        // }
        else if(direction == DirectionOfEnemy.DOWN){
            if(locOfEnemyY < 24) {locOfEnemyY++;}
        }
        enemy.initLoc(32 * locOfEnemyX, 32 * locOfEnemyY);
        if(direction == DirectionOfEnemy.RIGHT || direction == DirectionOfEnemy.LEFT){
            int checkY = (int)(enemy.getY() + getYChange(DirectionOfEnemy.UP));
            if(getNewPosTileType((int)enemy.getX(), checkY) == TileCheckConstants.PATH){
                enemy.changeLoc(enemyChangeSpeed, DirectionOfEnemy.UP);
            }
            else{
                enemy.changeLoc(enemyChangeSpeed, DirectionOfEnemy.DOWN);
            }
        }
        else{
            int checkX = (int)(enemy.getX() + getXChange(DirectionOfEnemy.RIGHT));
            if(getNewPosTileType(checkX, (int)enemy.getY()) == TileCheckConstants.PATH){
                enemy.changeLoc(enemyChangeSpeed, DirectionOfEnemy.RIGHT);
            }
            else{
                enemy.changeLoc(enemyChangeSpeed, DirectionOfEnemy.LEFT);
            }
        }
    }
    private double getYChange(int direction) {
        if(direction == DirectionOfEnemy.DOWN){
            return enemyChangeSpeed + 32;
        }
        else if(direction == DirectionOfEnemy.UP){
            return -enemyChangeSpeed;
        }
        return 0;
    }
    private double getXChange(int direction) {
        if(direction == DirectionOfEnemy.RIGHT){
            return enemyChangeSpeed + 32;
        }
        else if(direction == DirectionOfEnemy.LEFT){
            return -enemyChangeSpeed;
        }
        return 0;
    }
    private void loadEnemySprites(){
        enemyImages.add(0, SaveAndLoad.getAllSprites().getSubimage(6 * 32, 32, 32, 32));
        enemyImages.add(1, SaveAndLoad.getAllSprites().getSubimage(7 * 32, 32, 32, 32));
        enemyImages.add(2, SaveAndLoad.getAllSprites().getSubimage(8 * 32, 32, 32, 32));
    }
    public Play getPlay() {
        return play;
    }
    public void render(Graphics g){
        for(int i = 0; i < enemies.size(); i++){
            g.drawImage(enemyImages.get(enemies.get(i).getTypeOfEnemy()), (int)enemies.get(i).getX(), (int)enemies.get(i).getY(), null);     
        }
    }
    public void insertNewEnemy(int x, int y){
        this.enemies.add(new Enemy(x, y, 0, 0));
    }
}
