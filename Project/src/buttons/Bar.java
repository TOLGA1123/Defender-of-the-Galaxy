package buttons;
import java.awt.Rectangle;
import java.text.DecimalFormat;
import java.util.ArrayList;
import extras.Constant;
import extras.SaveAndLoad;
import extras.Constant.Defenders;
import handlers.DefenderHandler;
import main.ConstantsForScenes;
import placeable.Defender;
import scene.Play;
import java.awt.Point;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import static main.ConstantsForScenes.*;
import java.awt.image.BufferedImage;
public class Bar extends BarParent{
    
    private BufferedImage barDesign = initBarDesign();
    private Button menu;
    private Button pause;
    private Button sellButton;
    private Button upgradeButton;
    private Play playGetHandler;
    private Rectangle barSize;
    private ArrayList<Button> defenderButtons;
    private DecimalFormat format;
    private Defender selectedDefender;
    private Defender displayedDefender;
    public int money = 100;
    private int MAX_DEFENDER_LEVEL = 3;
    private boolean showDefenderPrice = false;
    private int DefenderCostType;
    public final int LIVES = 8;
    public int leftLives = LIVES; 
    private boolean paused =  false;
    public Bar(int x, int  y, int h, int w, Play play){
        super(x, y, h, w);
        this.barSize = new Rectangle(x, y ,w ,h*2 + 20);
        this.menu = new Button(26, 810, 64, 128, "/menu.png", "/menu_over.png", "/menu_pressed.png");
        this.pause = new Button(26, 890, 64, 128, "/pause.png", "/pause_over.png", "/pause_pressed.png"); //PAUSE RESMI DEGISTIR
        this.playGetHandler = play;
        this.format = new DecimalFormat("0.00");
        initButtons();
    }
    public void initButtons(){
        defenderButtons = new ArrayList<Button>();
        defenderButtons.add(new Button(218, 857, 35, 35,0,SaveAndLoad.getAllSprites().getSubimage(5*32,32,32,32),SaveAndLoad.getAllSprites().getSubimage(5*32,32,32,32),SaveAndLoad.getAllSprites().getSubimage(5*32,32,32,32)));
        defenderButtons.add(new Button(282, 857, 35, 35,1,SaveAndLoad.getAllSprites().getSubimage(4*32,32,32,32),SaveAndLoad.getAllSprites().getSubimage(4*32,32,32,32),SaveAndLoad.getAllSprites().getSubimage(4*32,32,32,32)));
        defenderButtons.add(new Button(218, 914, 35, 35,2,SaveAndLoad.getAllSprites().getSubimage(3*32,32,32,32),SaveAndLoad.getAllSprites().getSubimage(3*32,32,32,32),SaveAndLoad.getAllSprites().getSubimage(3*32,32,32,32)));
        defenderButtons.add(new Button(282, 914, 35, 35,3,SaveAndLoad.getAllSprites().getSubimage(2*32,32,32,32),SaveAndLoad.getAllSprites().getSubimage(2*32,32,32,32),SaveAndLoad.getAllSprites().getSubimage(2*32,32,32,32)));
        upgradeButton = new Button(616, 912, 32, 95, "/upgrade.png", "/upgrade.png", "/upgrade.png");
        sellButton = new Button(500, 912, 32, 95, "/sell.png", "/sell.png", "/sell.png");
    }
    public void click(int mouseXLoc, int mouseYLoc) {
        if(menu.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            set(MAIN_MENU);
        }
        else if(pause.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            pauseGame();;
        }   
        else{
            if (displayedDefender != null)
            {
                if(sellButton.getButtonSize().contains(mouseXLoc, mouseYLoc))
                {
                    sellSelectedDefender();
                    return;
                }  
                    else if(upgradeButton.getButtonSize().contains(mouseXLoc, mouseYLoc) && displayedDefender.getLevel() < MAX_DEFENDER_LEVEL)
                    {
                        upgradeSelectedDefender();
                        return;
                    }   
            }

            for(Button button: defenderButtons){
                if(button.getButtonSize().contains(mouseXLoc,mouseYLoc)){
                    if (!isGoldEnoughForDefender(DefenderCostType))
                    {
                        return;
                    }
                    if(selectedDefender == null || button.getId() != selectedDefender.getDefenderType()){
                        selectedDefender = new Defender(0,0,-1,button.getId());
                        playGetHandler.setSelectedDefender(selectedDefender);
                        return;
                    }
                    else if(selectedDefender != null && button.getId() == selectedDefender.getDefenderType()){
                        selectedDefender = null;
                        playGetHandler.setSelectedDefender(selectedDefender);
                        return;
                    }
                }
            }
        }   
    }
    private void upgradeSelectedDefender() {
        if (displayedDefender.getLevel() < MAX_DEFENDER_LEVEL && money > 0)
        {
            playGetHandler.upgradeDefender(displayedDefender);
            money -= getDefenderUpgradePrice();
        }

    }
    private void sellSelectedDefender() {
        playGetHandler.removeDefender(displayedDefender);
        money+= getDefenderSellPrice();
        displayedDefender = null;
    }

    public void move(int mouseXLoc, int mouseYLoc) {
        menu.setMouseOver(false);
        pause.setMouseOver(false);
        sellButton.setMouseOver(false);
        upgradeButton.setMouseOver(false);
        showDefenderPrice = false;
        if(menu.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            menu.setMouseOver(true);
            return;
        }
        else if(pause.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            pause.setMouseOver(true);
            return;
        }
        else{
                if ( displayedDefender != null )
                {
                    if(sellButton.getButtonSize().contains(mouseXLoc, mouseYLoc ))
                {
                    sellButton.setMouseOver(true);
                    return;
                }  
                    else if(upgradeButton.getButtonSize().contains(mouseXLoc, mouseYLoc))
                    {
                        upgradeButton.setMouseOver(true);
                        return;
                    }                 
                }

            for(Button button: defenderButtons){
                if(button.getButtonSize().contains(mouseXLoc,mouseYLoc)){
                    button.setMouseOver(true);
                    showDefenderPrice = true;
                    DefenderCostType = button.getId();
                    return;
                }
            }
        }
    }
    public void press(int mouseXLoc, int mouseYLoc) {
        if(menu.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            menu.setPressed(true);
        }
        else if(pause.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            pause.setPressed(true);
        }
        else{
            if ( displayedDefender != null)
            {
                if(sellButton.getButtonSize().contains(mouseXLoc, mouseYLoc))
            {
                sellButton.setPressed(true);
                return;
            }  
                else if(upgradeButton.getButtonSize().contains(mouseXLoc, mouseYLoc) && displayedDefender.getLevel() < MAX_DEFENDER_LEVEL)
                {
                    upgradeButton.setPressed(true);
                    return;
                }                 
            }
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
        pause.setPressed(false);
        pause.setMouseOver(false); 
        sellButton.setPressed(false);
        sellButton.setMouseOver(false); 
        upgradeButton.setPressed(false);
        upgradeButton.setMouseOver(false); 
        for(Button button: defenderButtons){
            button.isMouseOver = false;
            button.isPressed = false;
        }
    }
    public void paintBar(Graphics g){
        // g.setColor(Color.ORANGE);
        // g.fillRect(x, y, w, h);
        g.drawImage(barDesign, 0, 800, null);

        menu.paintButton(g, menu.getButtonImage());
        pause.paintButton(g, pause.getButtonImage());
        for(Button button: defenderButtons){
            button.paintButton(g,button.getButtonImage());
        }
        //displayedDefender
        drawDisplayedDefender(g);
        //wave information
        drawWaveInfo(g);
        drawEconomy(g);
        // show sell cost
        if (sellButton.isMouseOver)
        {
            g.setFont(new Font("LucidaSans",Font.BOLD, 15));
            g.setColor(Color.WHITE);
            g.drawString(""+getDefenderSellPrice(), 573, 933);
        }
        else if (upgradeButton.isMouseOver)
        {
            if (displayedDefender.getLevel() < MAX_DEFENDER_LEVEL)
            {
                g.setFont(new Font("LucidaSans",Font.BOLD, 15));
                g.setColor(Color.WHITE);
                g.drawString(""+getDefenderUpgradePrice(), 688, 933);
            }

        }
        
    }
    private void drawDisplayedDefender(Graphics g) {
        if(displayedDefender != null){
            g.drawImage(playGetHandler.getDefenderHandler().getDefenderImages().get(displayedDefender.getDefenderType()), 475, 845, 50, 50, null);
            g.setFont(new Font("LucidaSans",Font.BOLD, 15));
            g.setColor(Color.WHITE);
            g.drawString("" + Defenders.GetName(displayedDefender.getDefenderType()),630, 862);
            g.drawString("" + displayedDefender.getId(),605, 892 );
            g.drawString("" + displayedDefender.getLevel(),713, 892 );
            drawDisplayedDefenderBorder(g);
            drawDisplayedDefenderRange(g);
            //sell
            sellButton.paintButton(g, sellButton.getButtonImage());
          //  draw
            if (displayedDefender.getLevel() < MAX_DEFENDER_LEVEL)
            {
                upgradeButton.paintButton(g, upgradeButton.getButtonImage());
            }
            else
            {

            }
        }
    }
    private void drawDisplayedDefenderRange(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawOval(displayedDefender.getX()+16-((int) displayedDefender.getRange()*2)/2,displayedDefender.getY()+16-((int) displayedDefender.getRange()*2)/2,(int) displayedDefender.getRange()*2,(int) displayedDefender.getRange()*2);
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
        g.setColor(Color.WHITE);
        drawWaveTimer(g);
        drawEnemiesRemaining(g);
        drawWavesRemaining(g);
    }
    private void drawEconomy(Graphics g)
    {
        g.setFont(new Font("LucidaSans", Font.BOLD,22));
        g.setColor(Color.WHITE);
        drawMoneyAmount(g);
        if (showDefenderPrice)
        {
            drawDefenderCost(g);
        }
        if (playGetHandler.getPause() == true)
        {
            g.setColor(Color.WHITE);
            g.drawString("Game is paused",380,790);
        }
    }
    private void drawWavesRemaining(Graphics g) {
        g.drawString("" + (playGetHandler.getWaveHandler().getWaveIndex()+1) + "/" + playGetHandler.getWaveHandler().getWaves().size(), 896, 945);
    }
    private void drawEnemiesRemaining(Graphics g) {
        g.drawString("" + leftLives, 928, 869);
        g.drawString("" + playGetHandler.getEnemyHandler().getAliveEnemies(),913,907);
    }
    private void drawWaveTimer(Graphics g){
        if(playGetHandler.getWaveHandler().isWaveTimerStarted()){
            double timeRemaining = playGetHandler.getWaveHandler().getRemainingTime();
            String formatted = format.format(timeRemaining);
            g.drawString("" + formatted, 912, 832);
        }
    }
    private void drawMoneyAmount(Graphics   g)
    {
        g.drawString(""+money, 248, 832);
    }     
    private void drawDefenderCost(Graphics   g) 
    {
        g.setColor(Color.WHITE);
        g.setFont(new Font("LucidaSans", Font.BOLD,16));
        g.drawString(getDefenderShopName(), 363, 893);
        g.setFont(new Font("LucidaSans", Font.BOLD,22));
        g.drawString(getDefenderShopPrice() + "$", 392, 952);
        if (!isGoldEnoughForDefender(DefenderCostType))
        {
            g.setColor(Color.RED);
            g.fillRect(350, 930, 115, 25);
            g.setFont(new Font("LucidaSans", Font.BOLD,16));
            g.setColor(Color.WHITE);
            g.drawString("Can't Afford" , 355, 950);
        }

    } 
    private String getDefenderShopName()
    {
        return extras.Constant.Defenders.GetName(DefenderCostType);
    }
    private int getDefenderShopPrice()
    {

        return extras.Constant.Defenders.GetPrice(DefenderCostType);
    }
    private int getDefenderSellPrice()
    {
        if (displayedDefender!= null)
        {
            int upgradeCostIncrease = (int) ((displayedDefender.getLevel() -1) *getDefenderUpgradePrice()*0.5f);
            return upgradeCostIncrease + extras.Constant.Defenders.GetPrice(DefenderCostType)/2;
        }
        return extras.Constant.Defenders.GetPrice(DefenderCostType)/2;
    }
    private int getDefenderUpgradePrice()
    {
        return (int )(extras.Constant.Defenders.GetPrice(DefenderCostType)*0.5); // 0.5 kule normal fiyatının 0.5 katı = upgrade fiyatı
    }
    public void payForDefender(int DefenderCostType)
    {
        this.money -= extras.Constant.Defenders.GetPrice(DefenderCostType);
    }
    private boolean isGoldEnoughForDefender(int DefenderCostType)
    {
        return (money >= extras.Constant.Defenders.GetPrice(DefenderCostType));
    }
    private void pauseGame()
    {
        if (playGetHandler.getPause() == true)
        {
            playGetHandler.pause(false);
        }
        else
        {
            playGetHandler.pause(true);
        }
    }
	public void addMoney(int reward)
    {
        this.money += reward;
	}
    public void enemyPassedDefenders(){
        leftLives--;
        if(leftLives <= 0){
            ConstantsForScenes.constantsForScenes = FINISH;
        }
    }
    public void resetGameState() {
        DefenderCostType = 0;
        showDefenderPrice = false;
        displayedDefender = null;
        selectedDefender = null;
        leftLives = LIVES;
        money = 100;
    }
}
