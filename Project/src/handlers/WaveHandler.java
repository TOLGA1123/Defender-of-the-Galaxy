package handlers;

import java.util.ArrayList;
import java.util.Arrays;

import events.Wave;
import scene.Play;

public class WaveHandler {
    
    private Play play;
    public ArrayList<Wave> allWaves = new ArrayList<Wave>();
    public int enemyInitLimit = 60;
    public int enemyInit = enemyInitLimit;
    public int enemyIndex;
    public int waveIndex;
    public boolean initWaveTimer, isTimerOverWave;
    private int waveInitLimit = 60 * 5;
    public int waveInit = 0;
    public WaveHandler(Play play){
        this.play = play;
        initWaves();
    }
    public void updateGame(){
        if(enemyInit<enemyInitLimit){
            enemyInit++;
        }
        if(initWaveTimer){
            waveInit++;
            if(waveInit >= waveInitLimit){
                isTimerOverWave = true;
            }
        }
    }
    public int getNextEnemy(){
        enemyInit = 0;
        return allWaves.get(waveIndex).getCurrentEnemies().get(enemyIndex++);
    }
    public void initWaves() {
        allWaves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0,0,0,0,1,1,1,1))));
        allWaves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1,1,1,1,2,2,2,2))));
        allWaves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0,0,1,1,0,0,2,2))));
        allWaves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2,2,2,2,0,0,1,1))));  //these should be enemy types
    }

    public ArrayList<Wave> getWaves(){
        return allWaves;
    }

    public boolean newEnemyTime() {
        
        return enemyInit >= enemyInitLimit;
    }
    public boolean enemiesFinished(){
        return enemyIndex < allWaves.get(waveIndex).getCurrentEnemies().size();
    }
    public boolean allWavesFinished() {
        return waveIndex +1 < allWaves.size();
    }
    public void initWaveTimer() {
        initWaveTimer = true;
    }
    public boolean waveFinished() {
        return isTimerOverWave;
    }
    public void increase(){
        waveIndex++;
        waveInit = 0;
        isTimerOverWave = false;
        initWaveTimer = false;
    }
    public void enemyPlaceInit() {
        enemyIndex = 0;
    }
    public int returnIndex(){
        return waveIndex;
    }
    public double remainingTime(){
        double ticksRemaining = waveInitLimit - waveInit;
        return ticksRemaining/60.0;
    }
    public boolean waveStart(){
        return initWaveTimer;
    }

}
