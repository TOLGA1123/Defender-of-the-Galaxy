package placeable;

public class Defender {
    private int x , y;
    private int id;
    private int defenderType;
    private double damage;
    private double range;
    private double cooldown;
    public Defender(int x, int y, int id, int defenderType){
        this.x = x;
        this.y = y;
        this.id = id;
        this.defenderType = defenderType;
        setDefaultDamage();
        setDefaultRange();
        setDefaultCooldown();
    }

    private void setDefaultCooldown() {
        cooldown = extras.Constant.Defenders.getStartCooldown(defenderType);
    }

    private void setDefaultRange() {
        range = extras.Constant.Defenders.getStartRange(defenderType);
    }

    private void setDefaultDamage() {
        damage = extras.Constant.Defenders.getStartDamage(defenderType);
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
    public double getDamage() {
        return damage;
    }
    public double getRange() {
        return range;
    }
    public double getCooldown() {
        return cooldown;
    }
}
