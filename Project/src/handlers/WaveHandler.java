package handlers;

import java.util.ArrayList;
import java.util.Arrays;

import events.Wave;
import scene.Play;

public class WaveHandler {
    
    private Play play;
    private ArrayList<Wave> waves = new ArrayList<Wave>();
    private int enemySpawnTickLimit = 60;
    private int enemySpawnTick = enemySpawnTickLimit;
    private int enemyIndex;
    private int waveIndex;
    public WaveHandler(Play play){
        this.play = play;
        createWaves();
    }
    public void update(){
        if(enemySpawnTick<enemySpawnTickLimit){
            enemySpawnTick++;
        }
    }
    public int getNextEnemy(){
        enemySpawnTick = 0;
        return waves.get(waveIndex).getEnemyList().get(enemyIndex++);
    }
    private void createWaves() {
        waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0,0,0,0,1,1,1,1))));  //these should be enemy types
    }

    public ArrayList<Wave> getWaves(){
        return waves;
    }

    public boolean isTimeForNewEnemy() {
        
        return enemySpawnTick >= enemySpawnTickLimit;
    }
    public boolean isThereMoreEnemiesInWave(){
        return enemyIndex < waves.get(waveIndex).getEnemyList().size();
    }

}
