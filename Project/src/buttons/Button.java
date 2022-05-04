package buttons;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import java.awt.Graphics;

public class Button {
    
    private BufferedImage image;
    private BufferedImage imageOver;
    private BufferedImage imagePressed;
    private int x;
    private int y;
    private int h;
    private int w;
    private int id;
    private Rectangle buttonSize;
    private boolean isMouseOver;
    private boolean isPressed;

    public Button(int x, int y, int h, int w, String image, String imageOver, String imagePressed){
        playImageLoad(image);
        playImageOverLoad(imageOver);
        playImagePressedLoad(imagePressed);
        this.w = w;
        this.h = h;
        this.y = y;
        this.x = x;
        this.id = -1;
        this.buttonSize = new Rectangle(x,y,w,h);
    }
    public Button(int x, int y, int h, int w, int id, String image, String imageOver, String imagePressed){
        playImageLoad(image);
        playImageOverLoad(imageOver);
        playImagePressedLoad(imagePressed);
        this.w = w;
        this.h = h;
        this.y = y;
        this.x = x;
        this.id = id;
        this.buttonSize = new Rectangle(x,y,w,h);
    }
    public Button(int x, int y, int h, int w, BufferedImage image, BufferedImage imageOver, BufferedImage imagePressed){
        this.image = image;
        this.imageOver = imageOver;
        this.imagePressed = imagePressed;
        this.w = w;
        this.h = h;
        this.y = y;
        this.x = x;
        this.id = -1;
        this.buttonSize = new Rectangle(x,y,w,h);
    }
    public Button(int x, int y, int h, int w, int id, BufferedImage image, BufferedImage imageOver, BufferedImage imagePressed){
        this.image = image;
        this.imageOver = imageOver;
        this.imagePressed = imagePressed;
        this.w = w;
        this.h = h;
        this.y = y;
        this.x = x;
        this.id = id;
        this.buttonSize = new Rectangle(x,y,w,h);
    }
    public void setMouseOver(boolean isMouseOver) {
        this.isMouseOver = isMouseOver;
    }
    public void setPressed(boolean isPressed) {
        this.isPressed = isPressed;
    }
    public int getId() {
        return id;
    }
    public int getH() {
        return h;
    }
    public int getW() {
        return w;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public Rectangle getButtonSize() {
        return buttonSize;
    }
    public boolean getIsMouseOver(){
        return this.isMouseOver;
    }
    public boolean getIsPressed(){
        return this.isPressed;
    }
    public BufferedImage getButtonImage(){
        if (isMouseOver && !isPressed){
            return imageOver;
        }
        else if (isPressed){
            return imagePressed;
        }
        else{
            return image;  
        }
    }
    public void playImageLoad(String name1){
        if (name1 == (null) || name1.equals("")){
            return;
        }
        InputStream inputStream = getClass().getResourceAsStream(name1);
        try {
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void playImageOverLoad(String name2){
        if (name2 == (null) || name2.equals("")){
            return;
        }
        InputStream inputStream = getClass().getResourceAsStream(name2);
        try {
            imageOver = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void playImagePressedLoad(String name3){
        if (name3 == (null) || name3.equals("")){
            return;
        }
        InputStream inputStream = getClass().getResourceAsStream(name3);
        try {
            imagePressed = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void paintButton(Graphics g, BufferedImage button){
        g.drawImage(this.getButtonImage(), x, y, null);
    }
}
