package scene;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import main.MainGame;

public class Settings extends SceneParent implements SceneInterface{
    Random rand = new Random();
    BufferedImage image;
    private ArrayList<BufferedImage> sprites = new ArrayList<>();

    public Settings(MainGame mainGame) {
        super(mainGame);
        this.imageLoad();
        this.addSpritestoArray();
    }
    private void addSpritestoArray(){//Change for 32 * 32 sprites
        for (int i = 0; i < 10; i++){
            for (int q = 0; q < 15; q++){
                sprites.add(image.getSubimage(64 * q, 54 * i, 64, 54)); //adds sprites to the array with measurements: 54 pixels heigth, 64 pixels width
            }
        }
    }
    public void imageLoad(){
        InputStream inputStream = getClass().getResourceAsStream("/background_02.png");
        try {
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void render(Graphics g) {
        for (int i = 0; i < 10; i++){
            for (int q = 0; q < 15; q++){
                g.drawImage(sprites.get(rand.nextInt(149)), 64 * q, 54 * i, null);
            }
        }
    }
    @Override
    public void click(int mouseXLoc, int mouseYLoc) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void move(int mouseXLoc, int mouseYLoc) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void press(int mouseXLoc, int mouseYLoc) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void release(int mouseXLoc, int mouseYLoc) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void drag(int mouseXLoc, int mouseYLoc) {
        // TODO Auto-generated method stub
        
    }
}
