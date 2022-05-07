package placeable;

public class Defender {
    private int x , y;
    private int id;
    private int defenderType;

    public Defender(int x, int y, int id, int defenderType){
        this.x = x;
        this.y = y;
        this.id = id;
        this.defenderType = defenderType;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDefenderType() {
        return defenderType;
    }

    public void setDefenderType(int defenderType) {
        this.defenderType = defenderType;
    }
}
