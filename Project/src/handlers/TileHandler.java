package handlers;
import placeable.Tile;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import extras.SaveAndLoad;
import static extras.Constant.*;

public class TileHandler {
    public Tile TILE_1, TILE_2, TILE_3, TILE_4, TILE_5, TILE_6, TILE_7, TILE_8, TILE_9, TILE_10, TILE_11, TILE_12, ENTER, EXIT;
    private BufferedImage allSprites = SaveAndLoad.getAllSprites();
    public ArrayList<Tile> allTiles = new ArrayList<>();
    
    public TileHandler(){
        loadAllSprites();
        initTiles();
    }
    private void loadAllSprites() {
        allSprites = SaveAndLoad.getAllSprites();
    }
    public void initTiles(){
        allTiles.add(TILE_1 = new Tile(allSprites.getSubimage(160, 0, 32, 32), TileCheckConstants.PATH, 0));
        allTiles.add(TILE_2 = new Tile(allSprites.getSubimage(32, 32, 32, 32), TileCheckConstants.STAR, 1));
        allTiles.add(TILE_3 = new Tile(allSprites.getSubimage(192, 0, 32, 32), TileCheckConstants.EMPTY_SPACE, 2));
        allTiles.add(TILE_4 = new Tile(allSprites.getSubimage(64, 0, 32, 32), TileCheckConstants.PATH, 3));
        allTiles.add(TILE_5 = new Tile(allSprites.getSubimage(96, 0, 32, 32), TileCheckConstants.PATH, 4));
        allTiles.add(TILE_6 = new Tile(allSprites.getSubimage(32, 0, 32, 32), TileCheckConstants.PATH, 5));
        allTiles.add(TILE_7 = new Tile(allSprites.getSubimage(0, 0, 32, 32), TileCheckConstants.PATH, 6));
        allTiles.add(TILE_8 = new Tile(allSprites.getSubimage(7*32, 0, 32, 32), TileCheckConstants.EMPTY_SPACE, 7));
        allTiles.add(TILE_9 = new Tile(allSprites.getSubimage(8*32, 0, 32, 32), TileCheckConstants.STAR, 8));
        allTiles.add(TILE_10 = new Tile(allSprites.getSubimage(9*32, 0, 32, 32), TileCheckConstants.STAR, 9));
        allTiles.add(TILE_11 = new Tile(allSprites.getSubimage(0, 32, 32, 32), TileCheckConstants.EMPTY_SPACE, 10));
        allTiles.add(TILE_12 = new Tile(allSprites.getSubimage(128, 0, 32, 32), TileCheckConstants.PATH, 11));
        allTiles.add(ENTER = new Tile(allSprites.getSubimage(32, 96, 32, 32), TileCheckConstants.PATH, -1));
        allTiles.add(EXIT = new Tile(allSprites.getSubimage(64, 96, 32, 32), TileCheckConstants.PATH, -2));

    }
    public ArrayList<Tile> getAllTiles() {
        return allTiles;
    }
    public BufferedImage getAllSprites() {
        return allSprites;
    }
    public BufferedImage getTile(int n){
        if(n == -1){
            n = n + 13;
        }
        else if(n == -2){
            n = n + 15;
        }
        return getAllTiles().get(n).getImage();
    }
    public Tile getTileWithId(int n){
        if(n == -1){
            n = n + 13;
        }
        else if(n == -2){
            n = n + 15;
        }
        return getAllTiles().get(n);
    }
}
