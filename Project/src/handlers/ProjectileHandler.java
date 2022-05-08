package handlers;

import scene.Play;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Graphics2D;
import enemy.Enemy;
import extras.Constant;
import extras.SaveAndLoad;
import extras.Constant.Defenders.*;
import extras.Constant.ProjectileConstants.*;
import java.awt.image.BufferedImage;

import placeable.Defender;
import placeable.Projectile;
public class ProjectileHandler {
    private Play play;
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private ArrayList<BufferedImage> projectileImages = new ArrayList<>();
    private int projectileId = 0;
    public ProjectileHandler(Play play){
        this.play = play;
        loadProjectileImages();
    }
    private void loadProjectileImages(){
        BufferedImage atlas = SaveAndLoad.getAllSprites();
        projectileImages.add(atlas.getSubimage(9*32, 1*32, 32, 32));
        projectileImages.add(atlas.getSubimage(0*32, 2*32, 32, 32));
        projectileImages.add(atlas.getSubimage(1*32, 2*32, 32, 32));
        projectileImages.add(atlas.getSubimage(2*32, 2*32, 32, 32));
        projectileImages.add(atlas.getSubimage(3*32, 2*32, 32, 32));
    }
    public void updateGame(){
        for(Projectile p: projectiles){
            if(p.getActive()){
                p.move();
                if(isProjectileHittingEnemy(p)){
                    p.setActive(false);
                }
                else{
                    //nothing
                }
            }
        }
    }
    private boolean isProjectileHittingEnemy(Projectile p) {
        for(Enemy enemy: play.getEnemyHandler().getEnemies()){
            if(enemy.getSize().contains(p.getPosition())){
                enemy.hurt(p.getDamage());
                return true;
            }
        }
        return false;
    }
    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        for(Projectile p: projectiles){
            if(p.getActive()){
                g2d.translate(p.getPosition().x,p.getPosition().y);
                g2d.rotate(Math.toRadians(p.getRotation()+180));
                g2d.drawImage(projectileImages.get(p.getProjectileType()),-16, -16,null);
                g2d.rotate(-Math.toRadians(p.getRotation()+180));
                g2d.translate(-p.getPosition().x,-p.getPosition().y);
            }
        }
    }
    public void newProjectile(Defender def, Enemy e){
        int type = getProjectileType(def);

        int xDistance = (int) (def.getX() - e.getX());
        int yDistance = (int) (def.getY() - e.getY());
        int totalDistance = Math.abs(xDistance) + Math.abs(yDistance);

        double xPer = (double) Math.abs(xDistance)/totalDistance;

        double xSpeed = xPer * extras.Constant.ProjectileConstants.getSpeed(type) ;
        double ySpeed = extras.Constant.ProjectileConstants.getSpeed(type) - xSpeed;

        if(def.getX() > e.getX()){
            xSpeed = -1 * xSpeed;
        }
        if(def.getY() > e.getY()){
            ySpeed = -1 * ySpeed;
        }
        double arcValue =(double) Math.atan(yDistance/(double) xDistance);
        double rotate = (double) Math.toDegrees(arcValue);
        if(xDistance < 0){
            rotate += 180;
        }
        projectiles.add(new Projectile(def.getX()+16, def.getY()+16, xSpeed, ySpeed, def.getDamage(),rotate, projectileId++, type));
    }
    private int getProjectileType(Defender def) {
        if(def.getDefenderType() == Constant.Defenders.DEFENDER_1){
            return Constant.ProjectileConstants.PROJECTILE_1;
        }
        else if(def.getDefenderType() == Constant.Defenders.DEFENDER_2){
            return Constant.ProjectileConstants.PROJECTILE_2;
        }
        else if(def.getDefenderType() == Constant.Defenders.DEFENDER_3){
            return Constant.ProjectileConstants.PROJECTILE_3;
        }
        else if(def.getDefenderType() == Constant.Defenders.DEFENDER_4){
            return Constant.ProjectileConstants.PROJECTILE_4;
        }
        else{
            return -1;
        }
    }
}
