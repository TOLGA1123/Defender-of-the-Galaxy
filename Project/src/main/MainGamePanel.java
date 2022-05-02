package main;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class MainGamePanel extends JPanel{
    
    private MainGame mainGame;

    public MainGamePanel(MainGame mainGame){
        this.setMinimumSize(new Dimension(960, 960));
        this.setMaximumSize(new Dimension(960, 960));
        this.setPreferredSize(new Dimension(960, 960));
        this.mainGame = mainGame;
    }
    public MainGame getMainGame() {
        return mainGame;
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        mainGame.render.render(g);
    }
}
