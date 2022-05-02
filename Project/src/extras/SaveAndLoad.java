package extras;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class SaveAndLoad{
    public static void initLevel(int[] a, String name){
        File levelText = new File(name + ".txt");
        if (levelText.exists()){
            return;
        }
        else{
            try {
                levelText.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            writeFile(a, levelText);
        }
    }
    public static int[][] levelData(String name){
        File level = new File(name + ".txt");
        if(level.exists()){
            ArrayList<Integer> levelInfo = readFile(level);
            return ArrayListTo2D(levelInfo, 30, 25);
        }else{
            return null;
        }
    }
    public static void save(int[][] level, String name){
        File levelF = new File(name + ".txt");
        if(levelF.exists()){
            writeFile(twoDimensionTo1D(level), levelF);    
        }
        else{
            return;
        }
    }
    public static int[][] ArrayListTo2D(ArrayList<Integer> levelInfo, int xLength, int yLength){
        int[][] result = new int[yLength][xLength];
        for(int i = 0; i < result.length; i++){
            for(int q = 0; q < result[i].length; q++){
                result[i][q] = levelInfo.get(i * yLength + q);
            }
        }
        return result;
    }
    public static int[] twoDimensionTo1D(int[][] arr){
        int[] result = new int[arr[0].length * arr.length];
        for(int i = 0; i < arr.length; i++){
            for(int q = 0; q < arr[0].length; q++){
                result[i * arr.length + q] = arr[i][q];
            }
        }
        return result;
    }
    public static ArrayList<Integer> readFile(File level){
        ArrayList<Integer> levelInfo = new ArrayList<>();
        try {
            Scanner in = new Scanner(level);
            while(in.hasNextLine()){
                levelInfo.add((Integer.parseInt(in.nextLine())));
            }
            in.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return levelInfo;
    }
    public static void writeFile(int[] a, File file){
        try {
            PrintWriter writer = new PrintWriter(file);
            for(int q = 0; q < a.length; q++){
                writer.println(a[q]);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void initFile(){
        File text = new File("test.txt");
        try{
            text.createNewFile();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static BufferedImage getAllSprites(){
        BufferedImage image = null;
        InputStream inputStream = SaveAndLoad.class.getClassLoader().getResourceAsStream("space_background.png");
        try {
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    
}
