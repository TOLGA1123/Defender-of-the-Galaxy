package buttons;
import java.awt.Rectangle;
import java.text.DecimalFormat;
import java.util.ArrayList;

import extras.SaveAndLoad;
import extras.Constant.Defenders;
import placeable.Defender;
import scene.Play;
import java.awt.Point;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import static main.ConstantsForScenes.*;
import java.awt.image.BufferedImage;
public class Bar extends BarParent{
    
    private Button menu;
    private Play playGetHandler;
    private Rectangle barSize;
    private ArrayList<Button> defenderButtons;
    private DecimalFormat format;
    private Defender selectedDefender;
    private Defender displayedDefender;
    public Bar(int x, int  y, int h, int w, Play play){
        super(x, y, h, w);
        this.barSize = super.barSize;
        this.menu = new Button(10, 810, 64, 128, "/menu.png", "/menu_over.png", "/menu_pressed.png");
        this.playGetHandler = play;
        this.format = new DecimalFormat("0.00");
        
        initButtons();
    }
    public void initButtons(){
        defenderButtons = new ArrayList<Button>();
        defenderButtons.add(new Button(160, 820, 35, 35,0,SaveAndLoad.getAllSprites().getSubimage(5*32,32,32,32),SaveAndLoad.getAllSprites().getSubimage(5*32,32,32,32),SaveAndLoad.getAllSprites().getSubimage(5*32,32,32,32)));
        defenderButtons.add(new Button(210, 820, 35, 35,1,SaveAndLoad.getAllSprites().getSubimage(4*32,32,32,32),SaveAndLoad.getAllSprites().getSubimage(4*32,32,32,32),SaveAndLoad.getAllSprites().getSubimage(4*32,32,32,32)));
        defenderButtons.add(new Button(260, 820, 35, 35,2,SaveAndLoad.getAllSprites().getSubimage(3*32,32,32,32),SaveAndLoad.getAllSprites().getSubimage(3*32,32,32,32),SaveAndLoad.getAllSprites().getSubimage(3*32,32,32,32)));
        defenderButtons.add(new Button(310, 820, 35, 35,3,SaveAndLoad.getAllSprites().getSubimage(2*32,32,32,32),SaveAndLoad.getAllSprites().getSubimage(2*32,32,32,32),SaveAndLoad.getAllSprites().getSubimage(2*32,32,32,32)));

    }
    public void click(int mouseXLoc, int mouseYLoc) {
        if(menu.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            set(MAIN_MENU);
        }
        else{
            for(Button button: defenderButtons){
                if(button.getButtonSize().contains(mouseXLoc,mouseYLoc)){
                    selectedDefender = new Defender(0,0,-1,button.getId());
                    playGetHandler.setSelectedDefender(selectedDefender);
                    return;
                }
            }
        }   
    }
    public void move(int mouseXLoc, int mouseYLoc) {
        menu.setMouseOver(false);
        if(menu.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            menu.setMouseOver(true);
        }
        else{
            for(Button button: defenderButtons){
                if(button.getButtonSize().contains(mouseXLoc,mouseYLoc)){
                    button.setMouseOver(true);
                    return;
                }
            }
        }
    }
    public void press(int mouseXLoc, int mouseYLoc) {
        if(menu.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            menu.setPressed(true);
        }
        else{
            for(Button button: defenderButtons){
                if(button.getButtonSize().contains(mouseXLoc,mouseYLoc)){
                    button.setPressed(true);
                    return;
                }
            }
        }
    }
    public void release(int mouseXLoc, int mouseYLoc) {
        menu.setPressed(false);
        menu.setMouseOver(false);
        for(Button button: defenderButtons){
            button.isMouseOver = false;
            button.isPressed = false;
        }
    }
    public void paintBar(Graphics g){
        g.setColor(Color.ORANGE);
        g.fillRect(x, y, w, h);

        menu.paintButton(g, menu.getButtonImage());
        for(Button button: defenderButtons){
            g.setColor(Color.GRAY);
            g.fillRect(button.getX(),button.getY(),button.getW(),button.getH());
            button.paintButton(g,button.getButtonImage());
        }
        //displayedDefender
        drawDisplayedDefender(g);
        //wave information
        drawWaveInfo(g);
    }
    private void drawDisplayedDefender(Graphics g) {
        if(displayedDefender != null){
            g.setColor(Color.GRAY);
            g.fillRect(500,850,220,85);
            g.setColor(Color.BLACK);
            g.drawRect(500,850,220,85);
            g.drawRect(510,850,50,50);
            g.drawImage(playGetHandler.getDefenderHandler().getDefenderImages().get(displayedDefender.getDefenderType()), 510, 850, 50, 50, null);
            g.setFont(new Font("LucidaSans",Font.BOLD, 15));
            g.drawString("" + Defenders.GetName(displayedDefender.getDefenderType()),580, 870 );
            g.drawString("ID: " + displayedDefender.getId(),580, 885 );

            drawDisplayedDefenderBorder(g);
            drawDisplayedDefenderRange(g);
        }
    }
    private void drawDisplayedDefenderRange(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawOval(displayedDefender.getX()+16-(int) displayedDefender.getRange()/2,displayedDefender.getY()+16-(int) displayedDefender.getRange()/2,(int) displayedDefender.getRange(),(int) displayedDefender.getRange());
    }
    private void drawDisplayedDefenderBorder(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect(displayedDefender.getX(),displayedDefender.getY(),32,32);

    }
    public void displayDefender(Defender def) {
        displayedDefender = def;
    }
    private void drawWaveInfo(Graphics g) {
        g.setFont(new Font("LucidaSans", Font.BOLD,20));
        g.setColor(Color.black);
        drawWaveTimer(g);
        drawEnemiesRemaining(g);
        drawWavesRemaining(g);
    }
    private void drawWavesRemaining(Graphics g) {
        g.drawString("Wave: " + (playGetHandler.getWaveHandler().getWaveIndex()+1) + "/" + playGetHandler.getWaveHandler().getWaves().size(), 750, 880);
    }
    private void drawEnemiesRemaining(Graphics g) {
        g.drawString("Enemies: " + playGetHandler.getEnemyHandler().getAliveEnemies(),750,920);
    }
    private void drawWaveTimer(Graphics g){
        if(playGetHandler.getWaveHandler().isWaveTimerStarted()){
            double timeRemaining = playGetHandler.getWaveHandler().getRemainingTime();
            String formatted = format.format(timeRemaining);
            g.drawString("Time remaining: " + formatted, 750, 850);
        }
    }
}
