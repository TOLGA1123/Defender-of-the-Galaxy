package handlers;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import enemy.Enemy;

import java.awt.Graphics;
import extras.SaveAndLoad;
import placeable.Defender;
import scene.Play;
import static extras.Constant.Defenders.*;
public class DefenderHandler {
    private Play play;
    private ArrayList<BufferedImage> defenderImages = new ArrayList<>();
    public ArrayList<Defender> defenders = new ArrayList<>();
    public int defenderCount = 0;
    public DefenderHandler(Play play){
        this.play = play;
        loadDefenderSprites();
    }
   
    private void loadDefenderSprites() {
        defenderImages.add(0,SaveAndLoad.getAllSprites().getSubimage(2*32, 32, 32, 32));
        defenderImages.add(0,SaveAndLoad.getAllSprites().getSubimage(3*32, 32, 32, 32));
        defenderImages.add(0,SaveAndLoad.getAllSprites().getSubimage(4*32, 32, 32, 32));
        defenderImages.add(0,SaveAndLoad.getAllSprites().getSubimage(5*32, 32, 32, 32));
    }
    public void render(Graphics g){
        for(Defender defender: defenders){
            g.drawImage(defenderImages.get(defender.getDefenderType()),defender.getX(),defender.getY(),null);
        }
    }
    public void updateGame(){
        for(int i = 0; i < defenders.size(); i++){
            defenders.get(i).update();
            attackEnemyInRange(defenders.get(i));
        }
    }
    private void attackEnemyInRange(Defender def) {
        
            for(int i = 0; i < play.getEnemyHandler().getEnemies().size(); i++){
                if(play.getEnemyHandler().getEnemies().get(i).isEnemyAlive()){
                    if(isEnemyInRangeOfDefender(def,play.getEnemyHandler().getEnemies().get(i))){ //defender shoots enemy
                        if(def.cooldownFinished()){
                            play.shootEnemy(def,play.getEnemyHandler().getEnemies().get(i));
                            def.initCooldown();
                        }
                    }
                    else{}
                }
            }
    }

    private boolean isEnemyInRangeOfDefender(Defender def, Enemy enemy) {
        double xDiff = Math.abs(def.getX() - enemy.getX());
        double yDiff = Math.abs(def.getY() - enemy.getY());
        int range = (int) Math.hypot(xDiff,yDiff);

        return range < def.defenderRange();
    }

    public ArrayList<BufferedImage> getDefenderImages(){
        return defenderImages;
    }
    public void addDefender(Defender selectedDefender, int xPos, int yPos) {
        defenders.add(new Defender(xPos,yPos,defenderCount++,selectedDefender.getDefenderType()));
    }
    public Defender getDefenderAtLocation(int x, int y) {
        for(Defender def: defenders){
            if(def.getX() == x){
                if(def.getY() == y){
                    return def;
                }
            }
        }
        return null;
    }

    public void removeDefender(Defender displayedDefender) {
        for (int i = 0; i < defenders.size(); i++)
        {
            if (defenders.get(i).getId() == displayedDefender.getId())
            {
                defenders.remove(i);
            }
        }
    }
    public void upgradeDefender(Defender displayedDefender) {
        for (int i = 0; i < defenders.size(); i++)
        {
            if (defenders.get(i).getId() == displayedDefender.getId())
            {
                defenders.get(i).upgradeDefender();
            }
        }
	}


}

