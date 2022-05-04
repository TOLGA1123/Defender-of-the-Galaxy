package enemy;
import java.awt.Rectangle;

public class Enemy{

    private double x;
    private double y;
    private int hp;
    private int typeOfEnemy;
    private int enemyID;
    private Rectangle size;
    public Enemy(double x, double y, int typeOfEnemy, int enemyID){
        this.x = x;
        this.y = y;
        this.size = new Rectangle((int)x, (int)y, 32, 32);
        this.typeOfEnemy = typeOfEnemy;
        this.enemyID = enemyID;

    }
    public void changeLoc(double x, double y){
        this.x += x;
        this.y += y;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public int getEnemyID() {
        return enemyID;
    }
    public int getHp() {
        return hp;
    }
    public Rectangle getSize() {
        return size;
    }
    public int getTypeOfEnemy() {
        return typeOfEnemy;
    }
}
