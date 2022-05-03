package handlers;
import placeable.Tile;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import extras.SaveAndLoad;

public class TileHandler {
    public Tile TILE_1, TILE_2, TILE_3, TILE_4, TILE_5, TILE_6, TILE_7, TILE_8, TILE_9, TILE_10, TILE_11, TILE_12;
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
        allTiles.add(TILE_4 = new Tile(allSprites.getSubimage(64, 0, 32, 32), "road1", 3));
        allTiles.add(TILE_5 = new Tile(allSprites.getSubimage(96, 0, 32, 32), "road2", 4));
        allTiles.add(TILE_6 = new Tile(allSprites.getSubimage(32, 0, 32, 32), "road3", 5));
        allTiles.add(TILE_7 = new Tile(allSprites.getSubimage(0, 0, 32, 32), "road4", 6));
        allTiles.add(TILE_8 = new Tile(allSprites.getSubimage(7*32, 0, 32, 32), "space2", 7));
        allTiles.add(TILE_9 = new Tile(allSprites.getSubimage(8*32, 0, 32, 32), "space3", 8));
        allTiles.add(TILE_10 = new Tile(allSprites.getSubimage(9*32, 0, 32, 32), "space3", 9));
        allTiles.add(TILE_11 = new Tile(allSprites.getSubimage(0, 32, 32, 32), "space4", 10));
        allTiles.add(TILE_12 = new Tile(allSprites.getSubimage(128, 0, 32, 32), "road5", 11));
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
