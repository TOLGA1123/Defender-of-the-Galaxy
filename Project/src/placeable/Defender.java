package placeable;

public class Defender {
    private int x , y;
    private int id;
    private int defenderType;
    private int damage;
    private double range;
    private double cooldown;
    private int cooldownTick;
    public Defender(int x, int y, int id, int defenderType){
        this.x = x;
        this.y = y;
        this.id = id;
        this.defenderType = defenderType;
        setDefaultDamage();
        setDefaultRange();
        setDefaultCooldown();
    }
    public void update(){
        cooldownTick++;
    }
    public boolean isCooldownOver() {
        return cooldownTick >= cooldown;
    }

    public void resetCooldown() {
        cooldownTick = 0;
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
    public int getDamage() {
        return damage;
    }
    public double getRange() {
        return range;
    }
    public double getCooldown() {
        return cooldown;
    }

    
}
