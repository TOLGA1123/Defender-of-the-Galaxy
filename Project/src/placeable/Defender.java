package placeable;

import buttons.Bar;
import extras.Constant;

public class Defender {
    private int x , y;
    private int id;
    private int defenderType;
    private int damage;
    private double range;
    private double cooldown;
    private int cooldownTick;
    private int level = 1;
    public Defender(int x, int y, int id, int defenderType){
        this.x = x;
        this.y = y;
        this.id = id;
        this.level = 1;
        this.defenderType = defenderType;
        setDefaultDamage();
        setDefaultRange();
        setDefaultCooldown();
    }
    public void update(){
        cooldownTick++;
    }
    public void upgrade(){
        level++;
        if (this.defenderType == Constant.Defenders.DEFENDER_1) //cok dmg 1 canno 2 sniper 3 normal 4 noob
        {
            damage+= 20;
            range+= 10;
            cooldown-= 0; //bunu azaltınca güçleniyo daha hızxlı  tarıyo çünkü
        }
        else if (this.defenderType == Constant.Defenders.DEFENDER_2)
        {
            damage+= 10;
            range+= 30;
            cooldown-= 5;  
        }
        else if (this.defenderType == Constant.Defenders.DEFENDER_3)
        {
            damage+= 5;
            range+= 5;
            cooldown-= 15;  
        }
        else if (this.defenderType == Constant.Defenders.DEFENDER_4)
        {
            damage+= 2;
            range+= 5;
            cooldown-= 5;  
        }
    }
    public int getLevel()
    {
        return level;
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
