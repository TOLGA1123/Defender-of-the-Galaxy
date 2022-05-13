package placeable;

import buttons.Bar;
import extras.Constant;

public class Defender {
    private int x , y;
    private int id;
    private int defenderType;
    private int damage = extras.Constant.Defenders.getStartDamage(defenderType);
    private double range = extras.Constant.Defenders.getStartRange(defenderType);
    private double cooldown = extras.Constant.Defenders.getStartCooldown(defenderType);;
    private int cooldownTick;
    private int level = 1;
    public Defender(int x, int y, int id, int defenderType){
        this.x = x;
        this.y = y;
        this.id = id;
        this.level = 1;
        this.defenderType = defenderType;
    }
    public void update(){
        cooldownTick++;
    }
    public void upgradeDefender(){
        level++;
        if (this.defenderType == Constant.Defenders.DEFENDER_1) //cok dmg 1 canno 2 sniper 3 normal 4 noob
        {
            damage+= 3;
            range+= 5;
            cooldown-= 2; //bunu azaltınca güçleniyo daha hızxlı  tarıyo çünkü
        }
        else if (this.defenderType == Constant.Defenders.DEFENDER_2)
        {
            damage+= 5;
            range+= 10;
            cooldown-= 5;  
        }
        else if (this.defenderType == Constant.Defenders.DEFENDER_3)
        {
            damage+= 5;
            range+= 5;
            cooldown-= 0;  
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
    public boolean cooldownFinished() {
        return cooldownTick >= cooldown;
    }

    public void initCooldown() {
        cooldownTick = 0;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getId() {
        return id;
    }
    public int getDefenderType() {
        return defenderType;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setId(int id) {
            this.id = id;
        }
    public void setDefenderType(int defenderType) {
        this.defenderType = defenderType;
    }
    public int defenderDamage() {
        return damage;
    }
    public double defenderRange() {
        return range;
    }
    public double defenderCooldown() {
        return cooldown;
    }

    
}
