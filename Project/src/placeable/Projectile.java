package placeable;
import java.awt.geom.Point2D;
public class Projectile {
    private Point2D.Double projectilePosition;
    private int id;
    private int projectileType;
    private boolean projectileActive = true;
    private double xChange;
    private double yChange;
    private int projectileDamage;
    private double projectileRotation;
    public Projectile(double x, double y,double xSpeed, double ySpeed, int projectileDamage, double projectileRotation, int id, int projectileType){
        this.id = id;
        this.projectileType = projectileType;
        this.xChange = xSpeed;
        this.yChange = ySpeed;
        this.projectileRotation = projectileRotation;
        this.projectileDamage = projectileDamage;
        projectilePosition = new Point2D.Double(x,y);
    }
    public void move(){
        projectilePosition.x += xChange;
        projectilePosition.y += yChange;
    }
    public Point2D.Double getPosition() {
        return projectilePosition;
    }
    public void setPosition(Point2D.Double projectilePosition) {
        this.projectilePosition = projectilePosition;
    }
    public int getId() {
        return id;
    }
    public int getProjectileType(){
        return projectileType;
    }
    
    public boolean getActive() {
        return projectileActive;
    }
    public void setActive(boolean isActive) {
        this.projectileActive = isActive;
    }
    public int getProjectileDamage(){
        return projectileDamage;
    }
    public double getProjectileRotation(){
        return projectileRotation;
    }
}
