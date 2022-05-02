package scene;
import main.MainGame;

public class SceneParent {
    private MainGame mainGame;
    public SceneParent(MainGame mainGame){
        this.mainGame = mainGame;
    }
    public MainGame getMainGame(){
        return this.mainGame;
    }
}
