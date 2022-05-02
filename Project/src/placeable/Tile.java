package placeable;
import java.awt.image.BufferedImage;

public class Tile {
    private BufferedImage image;
    private BufferedImage imageOver;
    private BufferedImage imagePressed;
    private String name;
    private int id;
    public Tile(BufferedImage image, String name, int id){
        this.image = image;
        this.name = name;
        this.id = id;
        this.imageOver = image.getSubimage(2, 2, 30, 30);
        this.imagePressed = image.getSubimage(4, 4, 28, 28);
    }
    public BufferedImage getImage(){
        return image;
    } 
    public BufferedImage getImageOver() {
        return imageOver;
    }
    public BufferedImage getImagePressed() {
        return imagePressed;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
