package handlers;
import placeable.Tile;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import extras.SaveAndLoad;

public class TileHandler {
    public Tile TILE_1, TILE_2, TILE_3;
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
        allTiles.add(TILE_1 = new Tile(allSprites.getSubimage(160, 0, 32, 32), "roadTile", 0));
        allTiles.add(TILE_2 = new Tile(allSprites.getSubimage(32, 32, 32, 32), "starTile", 1));
        allTiles.add(TILE_3 = new Tile(allSprites.getSubimage(192, 0, 32, 32), "emptySpace", 2));
    }
    public ArrayList<Tile> getAllTiles() {
        return allTiles;
    }
    public BufferedImage getAllSprites() {
        return allSprites;
    }
    public BufferedImage getTile(int n){
        return getAllTiles().get(n).getImage();
    }
    public Tile getTileWithId(int n){
        return getAllTiles().get(n);
    }
}
