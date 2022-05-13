package events;

import java.util.ArrayList;

public class Wave{
    
    private ArrayList<Integer> currentEnemies;
    public Wave(ArrayList<Integer> currentEnemies){
        this.currentEnemies = currentEnemies;
    }

    public ArrayList<Integer> getCurrentEnemies(){
        return currentEnemies;
    }
}
