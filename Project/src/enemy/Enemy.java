package enemy;
import java.awt.Rectangle;
import static extras.Constant.*;

public class Enemy{

    private double x;
    private double y;
    private int hp;
    private int typeOfEnemy;
    private int enemyID;
    private int lastDirection;
    private Rectangle size;
    public Enemy(double x, double y, int typeOfEnemy, int enemyID){
        this.x = x;
        this.y = y;
        this.size = new Rectangle((int)x, (int)y, 32, 32);
        this.typeOfEnemy = typeOfEnemy;
        this.enemyID = enemyID;
        this.lastDirection = DirectionOfEnemy.RIGHT;
    }
    public void initLoc(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void changeLoc(double changeSpeed, int direction){
        this.lastDirection = direction;
        if (direction == DirectionOfEnemy.UP){
            this.y -= changeSpeed;
        }
        else if (direction == DirectionOfEnemy.DOWN){
            this.y += changeSpeed;
        }
        else if (direction == DirectionOfEnemy.RIGHT){
            this.x += changeSpeed;
        }
        else if (direction == DirectionOfEnemy.LEFT){
            this.x -= changeSpeed;
        }
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public int getLastDirection() {
        return lastDirection;
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
