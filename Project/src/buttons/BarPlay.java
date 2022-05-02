package buttons;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import handlers.TileHandler;
import placeable.Tile;
import scene.Edit;
import static main.ConstantsForScenes.*;

public class BarPlay extends BarParent{

    private Button menu;
    private Button save;
    private Button tile1;
    private Button tile2;
    private Button tile3;
    private TileHandler handler = new TileHandler();
    private Edit editGetHandler;
    private Tile selected;
    private Rectangle barSize;
    private ArrayList<Button> buttonTiles = new ArrayList<>();
    public BarPlay(int x, int y, int h, int w, Edit edit) {
        super(x, y, h, w);
        this.menu = new Button(10, 810, 64, 128, "/play_button.png", "/play_button_over.png", "/play_button_pressed.png");
        this.save = new Button(10, 888, 64, 128, "/save.png", "/save_over.png", "/save_pressed.png");
        this.tile1 = new Button(158, 810, 32, 32, 0, handler.getTileWithId(0).getImage(), handler.getTileWithId(0).getImageOver(), handler.getTileWithId(0).getImagePressed());
        this.tile2 = new Button(158 + 1 * 128, 810, 32, 32, 1, handler.getTileWithId(1).getImage(), handler.getTileWithId(1).getImageOver(), handler.getTileWithId(1).getImagePressed());
        this.tile3 = new Button(158 + 2 * 128, 810, 32, 32, 2, handler.getTileWithId(2).getImage(), handler.getTileWithId(2).getImageOver(), handler.getTileWithId(2).getImagePressed());
        this.editGetHandler = edit;
        initTileButtons();
    }
    private void save(){
        editGetHandler.save();
    }
    public void initTileButtons(){
        buttonTiles.add(tile1);
        buttonTiles.add(tile2);
        buttonTiles.add(tile3);
    }
    public void paintAllButtons(Graphics g){
        menu.paintButton(g, menu.getButtonImage());
        save.paintButton(g, save.getButtonImage());
        for(Button button : buttonTiles){
            button.paintButton(g, button.getButtonImage());
        }
        drawSelectedTile(g);
    }
    private void drawSelectedTile(Graphics g){
        if (selected != null){
            g.drawImage(selected.getImage(), 158 + 5 * 128, 810, 32, 32,null);
        }
    }
    public void click(int mouseXLoc, int mouseYLoc) {
        if(menu.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            set(MAIN_MENU);
        }
        else if (save.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            save();
        }
        else{
            for(Button button : buttonTiles){
                if(button.getButtonSize().contains(mouseXLoc, mouseYLoc)){
                    selected = handler.getTileWithId(button.getId());
                    editGetHandler.setSelected(selected);
                    return;
                }
            }
        }       
    }
    public void move(int mouseXLoc, int mouseYLoc) {
        menu.setMouseOver(false);
        save.setMouseOver(false);
        for(Button button : buttonTiles){
            button.setMouseOver(false);
        }
        if(menu.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            menu.setMouseOver(true);
        }
        else if (save.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            save.setMouseOver(true);
        }
        else{
            for(Button button : buttonTiles){
                if(button.getButtonSize().contains(mouseXLoc, mouseYLoc)){
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
        else if (save.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
            save.setPressed(true);
        }
        else{
            for(Button button : buttonTiles){
                if(button.getButtonSize().contains(new Point(mouseXLoc, mouseYLoc))){
                    button.setPressed(true);
                    return;    
                }
            }
        } 
    }
    public void release(int mouseXLoc, int mouseYLoc) {
        menu.setPressed(false);
        menu.setMouseOver(false);
        save.setPressed(false);
        save.setMouseOver(false);
        for(Button button : buttonTiles){
            button.setMouseOver(false);
            button.setPressed(false);
        }
    }
    public void paintBar(Graphics g){
        g.setColor(Color.ORANGE);
        g.fillRect(x, y, w, h);

        paintAllButtons(g);
    }
}
