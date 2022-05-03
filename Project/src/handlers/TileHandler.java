package handlers;
import placeable.Tile;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import extras.SaveAndLoad;

public class TileHandler {
    public Tile TILE_1, TILE_2, TILE_3;
    private BufferedImage allSprites = SaveAndLoad.getAllSprites();
    private BufferedImage starTile;
    public ArrayList<Tile> allTiles = new ArrayList<>();
    
    public TileHandler(){
        loadAllSprites();
        loadStarTile();
        initTiles();
    }
    private void loadAllSprites() {
        allSprites = SaveAndLoad.getAllSprites();
    }
    public void initTiles(){
        allTiles.add(TILE_1 = new Tile(allSprites.getSubimage(64, 64, 32, 32), "/tile1.png", 0));
        allTiles.add(TILE_2 = new Tile(starTile.getSubimage(0, 0, 32, 32), "starTile", 1));
        allTiles.add(TILE_3 = new Tile(allSprites.getSubimage(192, 64, 32, 32), "emptySpace", 2));
    }
    public void loadStarTile(){
        InputStream inputStream = getClass().getResourceAsStream("/star_tile.png");
        try {
            starTile = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
