package handlers;

import java.util.ArrayList;
import java.util.Arrays;

import events.Wave;
import scene.Play;

public class WaveHandler {
    
    private Play play;
    private ArrayList<Wave> waves = new ArrayList<Wave>();
    public WaveHandler(Play play){
        this.play = play;
        createWaves();
    }

    private void createWaves() {
        waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0,0,0,0,1,1,1,1))));  //these should be enemy types
    }

    public ArrayList<Wave> getWaves(){
        return waves;
    }
}
