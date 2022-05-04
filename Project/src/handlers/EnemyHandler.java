package handlers;
import scene.Play;
import java.awt.Graphics;
import java.util.ArrayList;
import enemy.Enemy;
import extras.SaveAndLoad;

import java.awt.image.BufferedImage;

public class EnemyHandler {

    private Play play;
    private ArrayList<BufferedImage> enemyImages = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    public EnemyHandler(Play play){
        this.play = play;
        this.insertNewEnemy(32 * 3, 32 * 13);
        loadEnemySprites();
    }
    public void updateGame(){
        for(int i = 0; i < enemies.size(); i++){
            this.enemies.get(i).changeLoc(0.5, 0);    
        } 
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
