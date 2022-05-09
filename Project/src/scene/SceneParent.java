package scene;
import main.MainGame;

public class SceneParent {
    protected MainGame mainGame;
    public SceneParent(MainGame mainGame){
        this.mainGame = mainGame;
    }
    public MainGame getMainGame(){
        return this.mainGame;
    }
}
