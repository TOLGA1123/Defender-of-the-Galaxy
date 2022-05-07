package handlers;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Graphics;
import extras.SaveAndLoad;
import placeable.Defender;
import scene.Play;
import static extras.Constant.Defenders.*;
public class DefenderHandler {
    private Play play;
    private ArrayList<BufferedImage> defenderImages = new ArrayList<>();
    private ArrayList<Defender> defenders = new ArrayList<>();
    private int towerAmount = 0;
    public DefenderHandler(Play play){
        this.play = play;
        loadDefenderSprites();
    }
   
    private void loadDefenderSprites() {
        defenderImages.add(0,SaveAndLoad.getAllSprites().getSubimage(2*32, 32, 32, 32));
        defenderImages.add(0,SaveAndLoad.getAllSprites().getSubimage(3*32, 32, 32, 32));
        defenderImages.add(0,SaveAndLoad.getAllSprites().getSubimage(4*32, 32, 32, 32));
        defenderImages.add(0,SaveAndLoad.getAllSprites().getSubimage(5*32, 32, 32, 32));
    }
    public void render(Graphics g){
        for(Defender defender: defenders){
            g.drawImage(defenderImages.get(defender.getDefenderType()),defender.getX(),defender.getY(),null);
        }
    }
    public void updateGame(){

    }
    public ArrayList<BufferedImage> getDefenderImages(){
        return defenderImages;
    }
    public void addDefender(Defender selectedDefender, int xPos, int yPos) {
        defenders.add(new Defender(xPos,yPos,towerAmount++,selectedDefender.getDefenderType()));
    }
}

