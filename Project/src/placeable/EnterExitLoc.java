package placeable;

public class EnterExitLoc {
    private int locX;
    private int locY;
    public EnterExitLoc(int locX, int locY) {
        this.locX = locX;
        this.locY = locY;
    }
    public int getLocY() {
        return locY;
    }
    public int getLocX() {
        return locX;
    }
    public void setLocY(int locY) {
        this.locY = locY;
    }
    public void setLocX(int locX) {
        this.locX = locX;
    }
}
