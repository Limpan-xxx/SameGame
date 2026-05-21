package model;

public class Tile {
    private int colorID;

    public Tile(int colorID){
        this.colorID = colorID;
    }

    public int getColorID() {
        return this.colorID;
    }

    public void setColorID(int ID){
        colorID = ID;
    }
}
