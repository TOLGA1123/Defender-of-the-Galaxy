package extras;

public class Constant {

    public static class Defenders{
        public static final int DEFENDER_1 = 0;
        public static final int DEFENDER_2 = 1;
        public static final int DEFENDER_3 = 2;
        public static final int DEFENDER_4 = 3;

        public static String GetName(int defenderType){
            if(defenderType == DEFENDER_1){
                return "Defender_1";
            }
            else if(defenderType == DEFENDER_2){
                return "Defender_2";
            }
            else if(defenderType == DEFENDER_3){
                return "Defender_3";
            }
            else if(defenderType == DEFENDER_4){
                return "Defender_4";
            }
            else{
                return "";
            }
        }
        public static int getStartDamage(int defenderType){
            if(defenderType == DEFENDER_1){
                return 5;
            }
            else if(defenderType == DEFENDER_2){
                return 10;
            }
            else if(defenderType == DEFENDER_3){
                return 1;
            }
            else if(defenderType == DEFENDER_4){
                return 8;
            }
            else{
                return 0;
            }
        }
        public static double getStartRange(int defenderType){
            if(defenderType == DEFENDER_1){
                return 100;
            }
            else if(defenderType == DEFENDER_2){
                return 150;
            }
            else if(defenderType == DEFENDER_3){
                return 50;
            }
            else if(defenderType == DEFENDER_4){
                return 180;
            }
            else{
                return 0;
            }
        }
        public static double getStartCooldown(int defenderType){
            if(defenderType == DEFENDER_1){
                return 50;
            }
            else if(defenderType == DEFENDER_2){
                return 40;
            }
            else if(defenderType == DEFENDER_3){
                return 35;
            }
            else if(defenderType == DEFENDER_4){
                return 30;
            }
            else{
                return 0;
            }
        }
    }
    public static class EnemyConstants{
        public static final int ENEMY_1 = 0;
        public static final int ENEMY_2 = 1;
        public static final int ENEMY_3 = 2;
        public static int getStartHp(int typeOfEnemy) {
            if(typeOfEnemy == ENEMY_1){
                return 100;
            }
            else if(typeOfEnemy == ENEMY_2){
                return 80;
            }
            else if(typeOfEnemy == ENEMY_3){
                return 300; 
            }
            else{
                return 0;
            }
        }
    }
    public static class ProjectileConstants{
        public static final int PROJECTILE_1 = 0;
        public static final int PROJECTILE_2 = 1;
        public static final int PROJECTILE_3 = 2;
        public static final int PROJECTILE_4 = 3;

        public static double getSpeed(int type){
            if(type==PROJECTILE_1){
                return 1.0;
            }
            else if(type==PROJECTILE_2){
                return 2.0;
            }
            else if(type==PROJECTILE_3){
                return 3.0;
            }
            else if(type==PROJECTILE_4){
                return 4.0;
            }
            else{
                return 0;
            }
        }
    }

    public static class DirectionOfEnemy{
        public static final int UP = 0;
        public static final int DOWN = 2;
        public static final int RIGHT = 1;
        public static final int LEFT = 3;
    }
    public static class TileCheckConstants{
        public static final int STAR = 0;
        public static final int EMPTY_SPACE = 1;
        public static final int PATH = 2;
    }
}
