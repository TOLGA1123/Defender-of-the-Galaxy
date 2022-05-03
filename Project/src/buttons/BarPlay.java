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
    private Button tile4;
    private Button tile5;
    private Button tile6;
    private Button tile7;
    private Button tile8;
    private Button tile9;
    private Button tile10;
    private Button tile11;
    private Button tile12;
    private TileHandler handler = new TileHandler();
    private Edit editGetHandler;
    private Tile selected;
    private Rectangle barSize;
    private ArrayList<Button> buttonTiles = new ArrayList<>();
    public BarPlay(int x, int y, int h, int w, Edit edit) {
        super(x, y, h, w);
        this.menu = new Button(10, 810, 64, 128, "/menu.png", "/menu_over.png", "/menu_pressed.png");
        this.save = new Button(10, 888, 64, 128, "/save.png", "/save_over.png", "/save_pressed.png");
        this.tile1 = new Button(158, 825, 32, 32, 0, handler.getTileWithId(0).getImage(), handler.getTileWithId(0).getImageOver(), handler.getTileWithId(0).getImagePressed());
        this.tile2 = new Button(158, 889, 32, 32, 1, handler.getTileWithId(1).getImage(), handler.getTileWithId(1).getImageOver(), handler.getTileWithId(1).getImagePressed());
        this.tile3 = new Button(158 + 1 * 96, 889, 32, 32, 2, handler.getTileWithId(2).getImage(), handler.getTileWithId(2).getImageOver(), handler.getTileWithId(2).getImagePressed());
        this.tile4 = new Button(158 + 2 * 96, 825, 32, 32, 3, handler.getTileWithId(3).getImage(), handler.getTileWithId(3).getImageOver(), handler.getTileWithId(3).getImagePressed());
        this.tile5 = new Button(158 + 3 * 96, 825, 32, 32, 4, handler.getTileWithId(4).getImage(), handler.getTileWithId(4).getImageOver(), handler.getTileWithId(4).getImagePressed());
        this.tile6 = new Button(158 + 4 * 96, 825, 32, 32, 5, handler.getTileWithId(5).getImage(), handler.getTileWithId(5).getImageOver(), handler.getTileWithId(5).getImagePressed());
        this.tile7 = new Button(158 + 5 * 96, 825, 32, 32, 6, handler.getTileWithId(6).getImage(), handler.getTileWithId(6).getImageOver(), handler.getTileWithId(6).getImagePressed());
        this.tile8 = new Button(158 + 5 * 96, 889, 32, 32, 7, handler.getTileWithId(7).getImage(), handler.getTileWithId(7).getImageOver(), handler.getTileWithId(7).getImagePressed());
        this.tile9 = new Button(158 + 2 * 96, 889, 32, 32, 8, handler.getTileWithId(8).getImage(), handler.getTileWithId(8).getImageOver(), handler.getTileWithId(8).getImagePressed());
        this.tile10 = new Button(158 + 3 * 96, 889, 32, 32, 9, handler.getTileWithId(9).getImage(), handler.getTileWithId(9).getImageOver(), handler.getTileWithId(9).getImagePressed());
        this.tile11 = new Button(158 + 4 * 96, 889, 32, 32, 10, handler.getTileWithId(10).getImage(), handler.getTileWithId(10).getImageOver(), handler.getTileWithId(10).getImagePressed());
        this.tile12 = new Button(158 + 1 * 96, 825, 32, 32, 11, handler.getTileWithId(11).getImage(), handler.getTileWithId(11).getImageOver(), handler.getTileWithId(11).getImagePressed());
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
        buttonTiles.add(tile4);
        buttonTiles.add(tile5);
        buttonTiles.add(tile6);
        buttonTiles.add(tile7);
        buttonTiles.add(tile8);
        buttonTiles.add(tile9);
        buttonTiles.add(tile10);
        buttonTiles.add(tile11);
        buttonTiles.add(tile12);
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
            g.drawImage(selected.getImage(), 158 + 11 * 64, 825, 32, 32,null);
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
