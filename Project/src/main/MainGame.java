package main;

import javax.swing.JFrame;
import extras.SaveAndLoad;
import handlers.TileHandler;
import listener.InputKeyListener;
import listener.InputMouseListener;
import scene.Edit;
import scene.Finish;
import scene.MainMenu;
import scene.Play;
import scene.Settings;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
public class MainGame extends JFrame implements Runnable{

    public Render render = new Render(this);
    public MainMenu mainMenu = new MainMenu(this);
    public Settings settings = new Settings(this);
    public Play play = new Play(this);
    public Edit edit = new Edit(this);
    public Finish finish = new Finish(this);
    public static TileHandler handler = new TileHandler();
    private MainGamePanel panel;
    private double frameTime = 1000000000.0 / 144.0; //FPS is set to 144
    private double updateTime = 1000000000.0 / 60.0; //UPS is set to 60
    private double lastDisplayTime;
    private double lastUpdateTime;
    private double updateCheck;
    private double timeCheck;
    private int fps;
    private int ups;
    private boolean loopRunner = true;
    private Thread thread;
    public static Clip clip;
    public MainGame(){
        try {
            String soundName = "music.wav";    
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(10);
        } catch (Exception e) {}
        startingLevel();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Defender of the Galaxy");
        this.setLocationRelativeTo(null);
        this.panel = new MainGamePanel(this);
        InputKeyListener inputKeyListener = new InputKeyListener(this);
        panel.addKeyListener(inputKeyListener);
        InputMouseListener inputMouseListener = new InputMouseListener(this);
        panel.addMouseMotionListener(inputMouseListener);
        panel.addMouseListener(inputMouseListener);
        this.setVisible(true);
        panel.requestFocus();
        this.add(panel);
        pack();
        this.setVisible(true);
    }
    private void startingLevel(){
        int[] res = new int[750];
        for(int i = 0; i < res.length; i++){
            res[i] = 0;
        }
        SaveAndLoad.initLevel(res, "idle");
    }
    
    public MainMenu getMainMenu() {
        return mainMenu;
    }
    public Settings getSettings() {
        return settings;
    }
    public Edit getEdit() {
        return edit;
    }
    public Play getPlay() {
        return play;
    }
    public Finish getFinish() {
        return finish;
    }
    @Override
    public void run() {
        while (loopRunner){
            if (System.nanoTime() - lastDisplayTime >= frameTime){//Rendering -> FPS
                lastDisplayTime = System.nanoTime();
                fps++;
                if (System.currentTimeMillis() - timeCheck >= 1000){
                    System.out.println("FPS = "+fps);
                    timeCheck = System.currentTimeMillis();
                    fps = 0;
                }
                panel.repaint();
            }
            if (System.nanoTime() - lastUpdateTime >= updateTime) {//Updates -> UPS
                gameUpdate();
                if(System.currentTimeMillis() - updateCheck >= 1000){
                    System.out.println("UPS = "+ups);
                    updateCheck = System.currentTimeMillis();
                    ups = 0;
                }
            }
        }
    }
    private void gameUpdate(){
        lastUpdateTime = System.nanoTime();
        ups++;
        if(ConstantsForScenes.constantsForScenes == ConstantsForScenes.PLAY){
            this.play.updateGame();
        }
        else if(ConstantsForScenes.constantsForScenes == ConstantsForScenes.MAIN_MENU){

        }
        else if(ConstantsForScenes.constantsForScenes == ConstantsForScenes.EDIT){
            
        }
        else if(ConstantsForScenes.constantsForScenes == ConstantsForScenes.SETTINGS){

        }
    }
    public TileHandler getTileHandler(){
        return handler;
    }
    private void gameStart(){
        thread = new Thread(this){};
        thread.start();
    }
    public static void main(String[] args) {
        MainGame gameStart = new MainGame();
        gameStart.gameStart();
    }
}