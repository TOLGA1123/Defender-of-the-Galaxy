package placeable;
import java.awt.geom.Point2D;
public class Projectile {
    private Point2D.Double position;
    private int id;
    private int projectileType;
    private boolean active = true;
    private double xSpeed;
    private double ySpeed;
    private int damage;
    private double rotation;
    public Projectile(double x, double y,double xSpeed, double ySpeed, int damage, double rotation, int id, int projectileType){
        position = new Point2D.Double(x,y);
        this.id = id;
        this.projectileType = projectileType;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.damage = damage;
        this.rotation = rotation;
    }
    public void move(){
        position.x += xSpeed;
        position.y += ySpeed;
    }
    public Point2D.Double getPosition() {
        return position;
    }
    public void setPosition(Point2D.Double position) {
        this.position = position;
    }
    public int getId() {
        return id;
    }
    public int getProjectileType(){
        return projectileType;
    }
    
    public boolean getActive() {
        return active;
    }
    public void setActive(boolean isActive) {
        this.active = isActive;
    }
    public int getDamage(){
        return damage;
    }
    public double getRotation(){
        return rotation;
    }
}
