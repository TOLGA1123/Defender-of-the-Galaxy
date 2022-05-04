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
        else{

        }
        return false;
    }
    private int getNewPosTileType(int checkX, int checkY) {
        return play.getNewPosTileType(checkX, checkY);
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
