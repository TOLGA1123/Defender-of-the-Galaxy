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
    }
    public static class EnemyConstants{
        public static final int ENEMY_1 = 0;
        public static final int ENEMY_2 = 1;
        public static final int ENEMY_3 = 2;
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
