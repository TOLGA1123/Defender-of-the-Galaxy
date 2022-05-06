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
    private boolean startWaveTimer, waveTickTimerOver;
    private int waveTickLimit = 60 * 5;
    private int waveTick = 0;
    public WaveHandler(Play play){
        this.play = play;
        createWaves();
    }
    public void updateGame(){
        if(enemySpawnTick<enemySpawnTickLimit){
            enemySpawnTick++;
        }
        if(startWaveTimer){
            waveTick++;
            if(waveTick >= waveTickLimit){
                waveTickTimerOver = true;
            }
        }
    }
    public int getNextEnemy(){
        enemySpawnTick = 0;
        return waves.get(waveIndex).getEnemyList().get(enemyIndex++);
    }
    private void createWaves() {
        waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0,0,0,0,1,1,1,1))));
        waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1,1,1,1,2,2,2,2))));  //these should be enemy types
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
    public boolean isThereMoreWaves() {
        return waveIndex +1 < waves.size();
    }
    public void startWaveTimer() {
        startWaveTimer = true;
    }
    public boolean isWaveTimerOver() {
        return waveTickTimerOver;
    }
    public void increaseWaveIndex(){
        waveIndex++;
        waveTickTimerOver = false;
        startWaveTimer = false;
    }
    public void resetEnemyIndex() {
        enemyIndex = 0;
    }

}
