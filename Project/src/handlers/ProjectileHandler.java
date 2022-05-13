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
import extras.Constant.ProjectileConstants;
import placeable.Defender;
import placeable.Projectile;
import java.awt.geom.Point2D;
public class ProjectileHandler {
    private Play play;
    public ArrayList<Projectile> projectiles = new ArrayList<>();
    private ArrayList<BufferedImage> projectileImages = new ArrayList<>();
    private ArrayList<BufferedImage> explosionImages = new ArrayList<>();
    public int projectileId = 0;
    public ArrayList<Explosion> explosions = new ArrayList<>();
    public class Explosion{
        private Point2D.Double position;
        private int explosionCountTick = 0;
        private int explosionIndexCount = 0;
        public Explosion(Point2D.Double position){
            this.position = position;
        }
        public void update(){
                explosionCountTick++;
                if(explosionCountTick >= 12){
                    explosionCountTick = 0;
                    explosionIndexCount++;
                }
        }
        public int getIndexOfExplosion(){
            return explosionIndexCount;
        }
        public Point2D.Double getPosition(){
            return position;
        }
    }
    public ProjectileHandler(Play play){
        this.play = play;
        loadProjectileImages();
    }
    private void loadProjectileImages(){
        BufferedImage spriteSheet = SaveAndLoad.getAllSprites();
        projectileImages.add(spriteSheet.getSubimage(9*32, 1*32, 32, 32));
        projectileImages.add(spriteSheet.getSubimage(0*32, 2*32, 32, 32));
        projectileImages.add(spriteSheet.getSubimage(1*32, 2*32, 32, 32));
        projectileImages.add(spriteSheet.getSubimage(2*32, 2*32, 32, 32));
        projectileImages.add(spriteSheet.getSubimage(3*32, 2*32, 32, 32));
        loadExplosionImages(spriteSheet);
    }
    private void loadExplosionImages(BufferedImage spriteSheet) {
        explosionImages.add(spriteSheet.getSubimage(4*32,2*32,32,32));
        explosionImages.add(spriteSheet.getSubimage(5*32,2*32,32,32));
        explosionImages.add(spriteSheet.getSubimage(6*32,2*32,32,32));
        explosionImages.add(spriteSheet.getSubimage(7*32,2*32,32,32));
        explosionImages.add(spriteSheet.getSubimage(8*32,2*32,32,32));
        explosionImages.add(spriteSheet.getSubimage(9*32,2*32,32,32));
        explosionImages.add(spriteSheet.getSubimage(0*32,3*32,32,32));
    }
    public void updateGame(){
        for(int i = 0; i < projectiles.size(); i++){
            if(projectiles.get(i).getActive()){
                projectiles.get(i).move();
                if(hittingEnemy(projectiles.get(i))){
                    projectiles.get(i).setActive(false);
                    if(projectiles.get(i).getProjectileType() == Constant.ProjectileConstants.PROJECTILE_1){
                        explosions.add(new Explosion(projectiles.get(i).getPosition()));
                        explode(projectiles.get(i));
                    }
                }
                else if(outOfGameField(projectiles.get(i))){
                    projectiles.get(i).setActive(false);
                }
            }
        }
        for(int i = 0; i < explosions.size(); i++){
            if(explosions.get(i).getIndexOfExplosion()<7){
                explosions.get(i).update();
            }
        }
    }
    private boolean outOfGameField(Projectile projectile) {
        if(projectile.getPosition().x >= 0){
            if(projectile.getPosition().x <= 960){
                if(projectile.getPosition().y >= 0){
                    if(projectile.getPosition().y <= 800){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    private void explode(Projectile projectile) {
        for(int i = 0; i < play.getEnemyHandler().getEnemies().size(); i++){
            if(play.getEnemyHandler().getEnemies().get(i).isEnemyAlive()){
                double radius = 40;
                double xDistance = Math.abs(projectile.getPosition().x - play.getEnemyHandler().getEnemies().get(i).getX());
                double yDistance = Math.abs(projectile.getPosition().y - play.getEnemyHandler().getEnemies().get(i).getY());
                double realDistance = (double) Math.hypot(xDistance,yDistance);
                if(realDistance <= radius){
                    play.getEnemyHandler().getEnemies().get(i).hurtEnemy(projectile.getProjectileDamage());
                }
            }
           
        }
    }
    private boolean hittingEnemy(Projectile projectile) {
        for(int i = 0; i < play.getEnemyHandler().getEnemies().size(); i++){
            if(play.getEnemyHandler().getEnemies().get(i).isEnemyAlive()){
                if(play.getEnemyHandler().getEnemies().get(i).getSize().contains(projectile.getPosition())){
                    play.getEnemyHandler().getEnemies().get(i).hurtEnemy(projectile.getProjectileDamage());

                    if(projectile.getProjectileType() == Constant.ProjectileConstants.PROJECTILE_4){
                        play.getEnemyHandler().getEnemies().get(i).slow();
                    }
                    return true;
                }
            }
            }
        return false;
    }
    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        for(int i = 0; i < projectiles.size(); i++){
            if(projectiles.get(i).getActive()){
                g2d.translate(projectiles.get(i).getPosition().x,projectiles.get(i).getPosition().y);
                g2d.rotate(Math.toRadians(projectiles.get(i).getProjectileRotation()+180));
                g2d.drawImage(projectileImages.get(projectiles.get(i).getProjectileType()),-16, -16,null);
                g2d.rotate(-Math.toRadians(projectiles.get(i).getProjectileRotation()+180));
                g2d.translate(-projectiles.get(i).getPosition().x,-projectiles.get(i).getPosition().y);
            }
        }
        drawExplosions(g2d);
    }
    private void drawExplosions(Graphics2D g2d) {
        for(Explosion e: explosions){
            if(e.getIndexOfExplosion() <7){
                g2d.drawImage(explosionImages.get(e.getIndexOfExplosion()), (int) e.getPosition().x-16,(int) e.getPosition().y-16, null);
            }
        }
    }
    public void createProjectile(Defender def, Enemy e){
        int typeOfProjectile = getProjectileType(def);
        int xDistance = (int) (def.getX() - e.getX());
        int yDistance = (int) (def.getY() - e.getY());
        int totalDistance = Math.abs(xDistance) + Math.abs(yDistance);
        double xPer = (double) Math.abs(xDistance)/totalDistance;
        double xSpeed = xPer * extras.Constant.ProjectileConstants.getSpeed(typeOfProjectile) ;
        double ySpeed = extras.Constant.ProjectileConstants.getSpeed(typeOfProjectile) - xSpeed;
        if(def.getY() > e.getY()){
            ySpeed = -1 * ySpeed;
        }
        if(def.getX() > e.getX()){
            xSpeed = -1 * xSpeed;
        }
        double temp =(double) Math.atan(yDistance/(double) xDistance);
        double change = (double) Math.toDegrees(temp);
        if(xDistance < 0){
            change += 180;
        }
        projectiles.add(new Projectile(def.getX()+16, def.getY()+16, xSpeed, ySpeed, def.defenderDamage(),change, projectileId++, typeOfProjectile));
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
