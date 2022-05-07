package placeable;
import java.awt.geom.Point2D;
public class Projectile {
    private Point2D.Double position;
    private int id;
    private int projectileType;
    private boolean active = true;

    public Projectile(double x, double y, int id, int projectileType){
        position = new Point2D.Double(x,y);
        this.id = id;
        this.projectileType = projectileType;
    }
    public void move(double x, double y){
        position.x += x;
        position.y += y;
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
}
