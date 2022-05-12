package main;
import java.awt.Dimension;
import java.awt.Graphics;
import java.net.URL;
import javax.swing.JApplet;
import javax.swing.JPanel;
import java.applet.Applet;
import java.applet.AudioClip;
public class MainGamePanel extends JPanel{
    
    private MainGame mainGame;
    private AudioClip[] music;
    private AudioClip current;
    
    public MainGamePanel(MainGame mainGame){
        this.setMinimumSize(new Dimension(960, 960));
        this.setMaximumSize(new Dimension(960, 960));
        this.setPreferredSize(new Dimension(960, 960));
        this.mainGame = mainGame;
        URL url1 = null;
        try {
            url1 = new URL("file:/C:/Users/Mert/Desktop/Defender-of-the-Galaxy/Project/src/main/music.wav");
        } catch (Exception e) {}
        music = new AudioClip[3];
        music[0] = null;
        music[1] = Applet.newAudioClip(url1);
        current = music[1];
        current.loop();
    }
    
    public MainGame getMainGame() {
        return mainGame;
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        mainGame.render.render(g);
    }
}
